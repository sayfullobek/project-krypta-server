package ul.it.universalserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ul.it.universalserver.entity.*;
import ul.it.universalserver.entity.enums.Gander;
import ul.it.universalserver.payload.*;
import ul.it.universalserver.repository.*;
import ul.it.universalserver.security.JwtTokenProvider;

import java.security.SecureRandom;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AttachmentService attachmentService;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AppSettingsRepository appSettingsRepository;
    private final QrCodeService qrCodeService;
    private final WalletRepository walletRepository;
    private final VipsRepository vipsRepository;
    private final ArchivePayRepository archivePayRepository;
    private final WithdrawalAddressRepository withdrawalAddressRepository;
    private final WithdrawalRequestRepository withdrawalRequestRepository;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final
    AuthenticationManager authenticationManager;

    public GetLogin login(@RequestBody ReqLogin request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        User user = new User();
        if (request.getStatus().equals("phone")) {
            user = userRepository.findUserByPhoneNumber(request.getUsername()).orElseThrow(() -> new ResourceNotFoundException("getUser"));
        } else {
            user = userRepository.findUserByEmail(request.getUsername()).orElseThrow(() -> new ResourceNotFoundException("getUser"));
        }
        ResToken resToken = new ResToken(generateToken(request.getUsername(), request.getStatus()));
        System.out.println(ResponseEntity.ok(getMal(user, resToken)));
        return getMal(user, resToken);
    }

    public Apiresponse changePassword(UUID id, ChangePassword changePassword) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getStatus().equals("phone") ? user.getPhoneNumber() : user.getEmail(), changePassword.getOldPassword())
            );
            if (!changePassword.getNewPassword().equals(changePassword.getNewPrePassword())) {
                return new Apiresponse("Yangi parol va tasdiqlash paroli bir xil bo'lishi shart", false);
            }
            user.setPassword(passwordEncoder().encode(changePassword.getNewPassword()));
            userRepository.save(user);
            return new Apiresponse("Parolingiz muvaffaqiyatli almashtirildi", true);
        }
        return new Apiresponse("Bunday accaount mavjud emas", false);
    }

    public ResRegister register(ReqRegister reqRegister) {
        if (reqRegister.getStatus().equals("phone")) {
            if (userRepository.existsUserByPhoneNumber(reqRegister.getPhoneNumber())) {
                return new ResRegister(null, new Apiresponse("bunday telefon raqam avvaldan mavjud", false));
            }
        } else {
            if (userRepository.existsUserByEmail(reqRegister.getEmail())) {
                return new ResRegister(null, new Apiresponse("bunday email avvaldan mavjud", false));
            }
        }
        if (userRepository.existsUserByReferralCode(reqRegister.getReferralCode())) {
            Wallet getAppSettings = new Wallet(appSettingsRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("getAppSettings")).getFirstPersonProfit(), 0, 0, 0, 0, 0);
            Wallet save1 = walletRepository.save(getAppSettings);
            User user = User.builder()
                    .password(passwordEncoder().encode(reqRegister.getPassword()))
                    .referralCode(createRandomCode(6))
                    .roles(Collections.singletonList(roleRepository.findById(2).orElseThrow(() -> new ResourceNotFoundException("getRole"))))
                    .status(reqRegister.getStatus())
                    .firstName(createRandomCode(8))
                    .lastName(createRandomCode(8))
                    .wallet(save1) //birinchi marta kirgan odamlarga beriladigan pul
                    .agree(true)
                    .withAddressSize(0)
                    .gander(Gander.valueOf("MALE"))
                    .photoId(UUID.fromString("be9ae603-64ca-4562-856e-947801d5d9f7"))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();
            if (reqRegister.getStatus().equals("phone")) {
                user.setPhoneNumber(reqRegister.getPhoneNumber());
            } else {
                user.setEmail(reqRegister.getEmail());
            }
            VIPS vips = vipsRepository.findById(vipsRepository.findAll().get(0).getId()).orElseThrow(() -> new ResourceNotFoundException("getVips"));
            user.setVips(vips);
            user.setWhoseReferralCode(reqRegister.getReferralCode());
            User save = userRepository.save(user);
            ReqLogin build = ReqLogin.builder()
                    .username(save.getStatus().equals("phone") ? save.getPhoneNumber() : save.getEmail())
                    .password(reqRegister.getPassword())
                    .status(reqRegister.getStatus())
                    .build();
            GetLogin login = login(build);
            referralCodeNewStep(reqRegister.getReferralCode());
//            BitMatrix bitMatrix = qrCodeService.generateQrCodeimage(save.getReferralCode(), 100, 100, "");
            return new ResRegister(login, new Apiresponse("muvaffaqiyatli ro'yxatdan o'tdingiz", true));
        }
        return new ResRegister(null, new Apiresponse("referral kodingizda xatolik", false));
    }

    public void referralCodeNewStep(String referralCode) {
        User user = userRepository.findUserByReferralCode(referralCode).orElseThrow(() -> new ResourceNotFoundException("getUser"));
        user.getWallet().setOffer(user.getWallet().getOffer() + 1);
        userRepository.save(user);
    }

    public String createRandomCode(int codeLength) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < codeLength; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException("getUser"));
    }

    public UserDetails getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
    }

    public Apiresponse change(UUID id, ReqLogin reqLogin) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            user.setEmail(reqLogin.getUsername());
            user.setPassword(passwordEncoder().encode(reqLogin.getPassword()));
            userRepository.save(user);
            return new Apiresponse("almashtirildi", true);
        }
        return new Apiresponse("bunday user mavjud emas", false);
    }


    private String generateToken(String username, String status) {
        User user = new User();
        if (status.equals("phone")) {
            user = userRepository.findUserByPhoneNumber(username).orElseThrow(() -> new UsernameNotFoundException("getUser"));
        } else {
            user = userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("getUser"));
        }
        return jwtTokenProvider.generateToken(user.getId());
    }

    public GetLogin getMal(User user, ResToken resToken) {
        return new GetLogin(user, resToken);
    }

    public Apiresponse uploadPhoto(UUID id, ReqRegister reqRegister) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            UUID upload = attachmentService.upload(reqRegister.getFormData());
            User user = byId.get();
            user.setPhotoId(upload);
            userRepository.save(user);
            return new Apiresponse("qo'shildi", false);
        }
        return new Apiresponse("oka sizga kirish mumkin emas", false);
    }

    public Apiresponse updateAbout(UUID id, ReqRegister reqRegister) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            user.setFirstName(reqRegister.getFirstName());
            user.setLastName(reqRegister.getLastName());
            user.setGander(Gander.valueOf(reqRegister.getGander()));
            userRepository.save(user);
            return new Apiresponse("muvaffaqiyatli", true);
        }
        return new Apiresponse("sizga kirish mumkin emas", false);
    }

    public Apiresponse updateMyMoney(UUID id, MyMoneyDto myMoneyDto) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            Optional<ArchivePay> archiBy = archivePayRepository.findById(myMoneyDto.getArchiveId());
            if (archiBy.isPresent()) {
                User user = byId.get();
                ArchivePay archivePay = archiBy.get();
                archivePay.setPulTushdimi(true);
                user.getWallet().setNowMoney(user.getWallet().getNowMoney() + myMoneyDto.getMoney());
                user.getWallet().setAllInCome(user.getWallet().getAllInCome() + myMoneyDto.getMoney());
                user.getWallet().setNechaMartaPulKiritgan(user.getWallet().getNechaMartaPulKiritgan() + 1);
                User getUser = userRepository.findUserByReferralCode(user.getWhoseReferralCode()).orElseThrow(() -> new ResourceNotFoundException("getUser"));
                getUser.getWallet().setNowMoney(getUser.getWallet().getNowMoney() + ((myMoneyDto.getMoney() * appSettingsRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("getAppSettings")).getInterestByReferral()) / 100));
                userRepository.save(user);
                userRepository.save(getUser);
                archivePayRepository.save(archivePay);
                return new Apiresponse("muvaffaqiyatli o'tkazildi", true);
            }
            return new Apiresponse("Bunday pul o'tkazmasi mavjud emas", false);
        }
        return new Apiresponse("Bunday foydalanuvchi mavjud emas", false);
    }

    public Apiresponse meWithdrawalOfMoneyFromTheAccount(UUID id, MoneyExitDto moneyExitDto) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            Optional<WithDrawalAddress> withById = withdrawalAddressRepository.findById(moneyExitDto.getWithdrawalId());
            if (withById.isPresent()) {
                User user = byId.get();
                if (user.getWallet().getNowMoney() >= moneyExitDto.getMoney()) {
                    WithDrawalAddress withDrawalAddress = withById.get();
                    user.getWallet().setNowMoney(user.getWallet().getNowMoney() - moneyExitDto.getMoney());
                    user.getWallet().setTakeOff(user.getWallet().getTakeOff() + moneyExitDto.getMoney());
                    WithdrawalRequest withdrawalRequest = WithdrawalRequest.builder()
                            .money(moneyExitDto.getMoney())
                            .withDrawalAddress(Collections.singletonList(withDrawalAddress))
                            .whenDidHeSendTheRequest(new Date())
                            .wasTheMoneyThrownAway(false)
                            .build();
                    userRepository.save(user);
                    withdrawalRequestRepository.save(withdrawalRequest);
                    return new Apiresponse("so'rov yuborildi iltimos kuting", true);
                }
                return new Apiresponse("Sizda mablag' yetarli emas", false);
            }
            return new Apiresponse("Addressda xatolik bor iltimos qayta urinib ko'ring", false);
        }
        return new Apiresponse("Bunday foydalanuvchi mavjud emas", false);
    }

    public Apiresponse confirmationWithReq(UUID id, MoneyExitDto moneyExitDto) {
        Optional<WithdrawalRequest> byId = withdrawalRequestRepository.findById(id);
        if (byId.isPresent()) {
            WithdrawalRequest withdrawalRequest = byId.get();
            withdrawalRequest.setWasTheMoneyThrownAway(true);
            withdrawalRequestRepository.save(withdrawalRequest);
            return new Apiresponse("muvaffaqiyatli tasdiqladingiz", true);
        }
        return new Apiresponse("bunday pul yechilishi mavju emas", false);
    }

    public Apiresponse cantForgetPasswordAdd(UUID id, ReqRegister reqRegister) {
        User getUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
        getUser.setAPasswordThatCannotBeForgotten(reqRegister.getCantForgetPassword());
        userRepository.save(getUser);
        return new Apiresponse("successlly saved", true);
    }
}
