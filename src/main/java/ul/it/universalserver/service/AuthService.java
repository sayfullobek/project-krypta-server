package ul.it.universalserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ul.it.universalserver.entity.*;
import ul.it.universalserver.entity.enums.Gander;
import ul.it.universalserver.entity.enums.HistoryName;
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
    private final HistoryRepository historyRepository;

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
                return new Apiresponse("The new password and the confirmation password must be the same", false);
            }
            user.setPassword(passwordEncoder().encode(changePassword.getNewPassword()));
            userRepository.save(user);
            return new Apiresponse("Your password has been successfully changed", true);
        }
        return new Apiresponse("No such accaount exists", false);
    }

    public ResRegister register(ReqRegister reqRegister) {
        if (reqRegister.getStatus().equals("phone")) {
            if (userRepository.existsUserByPhoneNumber(reqRegister.getPhoneNumber())) {
                return new ResRegister(null, new Apiresponse("this phone number already exists", false));
            }
        } else {
            if (userRepository.existsUserByEmail(reqRegister.getEmail())) {
                return new ResRegister(null, new Apiresponse("This email already exists", false));
            }
        }
        if (userRepository.existsUserByReferralCode(reqRegister.getReferralCode())) {
            Wallet getAppSettings = new Wallet(0, 0, 0, 0, 0, 0, 0, 30, 0);
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
            return new ResRegister(login, new Apiresponse("you have successfully registered", true));
        }
        return new ResRegister(null, new Apiresponse("There is an error in your referral code", false));
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
            return new Apiresponse("replaced", true);
        }
        return new Apiresponse("no such user exists", false);
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
            return new Apiresponse("added", false);
        }
        return new Apiresponse("you cannot access", false);
    }

    public Apiresponse updateAbout(UUID id, ReqRegister reqRegister) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            user.setFirstName(reqRegister.getFirstName());
            user.setLastName(reqRegister.getLastName());
            user.setGander(Gander.valueOf(reqRegister.getGander()));
            userRepository.save(user);
            return new Apiresponse("successful", true);
        }
        return new Apiresponse("you cannot access", false);
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
                user.getWallet().setTheMoneyHeInvested(user.getWallet().getTheMoneyHeInvested() + myMoneyDto.getMoney());
                User getUser = userRepository.findUserByReferralCode(user.getWhoseReferralCode()).orElseThrow(() -> new ResourceNotFoundException("getUser"));
                getUser.getWallet().setNowMoney(getUser.getWallet().getNowMoney() + ((myMoneyDto.getMoney() * appSettingsRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("getAppSettings")).getInterestByReferral()) / 100));
                getUser.getWallet().setFriendsProfit(getUser.getWallet().getFriendsProfit() + ((myMoneyDto.getMoney() * appSettingsRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("getAppSettings")).getInterestByReferral()) / 100));
                userRepository.save(getUser);
                archivePayRepository.save(archivePay);
                User save = userRepository.save(user);
                historyRepository.save(
                        History.builder()
                                .money(myMoneyDto.getMoney())
                                .status(HistoryName.ENTER)
                                .when(new Date())
                                .user(user)
                                .build()
                );
                return new Apiresponse("was successfully conducted", true);
            }
            return new Apiresponse("No such transfer is available", false);
        }
        return new Apiresponse("No such user exists", false);
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
                    User save = userRepository.save(user);
                    withdrawalRequestRepository.save(withdrawalRequest);
                    historyRepository.save(
                            History.builder()
                                    .money(moneyExitDto.getMoney())
                                    .status(HistoryName.EXIT)
                                    .when(new Date())
                                    .user(save)
                                    .build()
                    );
                    return new Apiresponse("request sent please wait", true);
                }
                return new Apiresponse("You don't have enough funds", false);
            }
            return new Apiresponse("There is an error in the address, please try again", false);
        }
        return new Apiresponse("No such user exists", false);
    }

    public Apiresponse confirmationWithReq(UUID id, MoneyExitDto moneyExitDto) {
        Optional<WithdrawalRequest> byId = withdrawalRequestRepository.findById(id);
        if (byId.isPresent()) {
            WithdrawalRequest withdrawalRequest = byId.get();
            withdrawalRequest.setWasTheMoneyThrownAway(true);
            withdrawalRequestRepository.save(withdrawalRequest);
            return new Apiresponse("You have successfully verified", true);
        }
        return new Apiresponse("such withdrawal is not available", false);
    }

    public Apiresponse cantForgetPasswordAdd(UUID id, ReqRegister reqRegister) {
        User getUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
        getUser.setAPasswordThatCannotBeForgotten(reqRegister.getCantForgetPassword());
        userRepository.save(getUser);
        return new Apiresponse("successlly saved", true);
    }

    public List<History> GetHistoryByUser(UUID id) {
        return historyRepository.findAllByUser_id(id);
    }

    public Apiresponse updateMoneyFriendsProfit(@PathVariable UUID id, @RequestBody FriendsProfitDto friendsProfitDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
        double friend = 0.01 / 28813;
        user.getWallet().setFriendsProfit(user.getWallet().getFriendsProfit() + friend);
        double money = user.getVips().getShareRatio() / 28813;
        user.getWallet().setTheMoneyHeInvested(user.getWallet().getTheMoneyHeInvested() + money);
        user.getWallet().setNowMoney(user.getWallet().getNowMoney() + friend + money);
        userRepository.save(user);
        return new Apiresponse("success", true);
    }
}
