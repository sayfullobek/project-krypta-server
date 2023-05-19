package ul.it.universalserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ul.it.universalserver.entity.User;
import ul.it.universalserver.entity.WithDrawalAddress;
import ul.it.universalserver.logic.ServiceAbs;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.WithdrawalDto;
import ul.it.universalserver.repository.UserRepository;
import ul.it.universalserver.repository.WithdrawalAddressRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WithdrawalService extends ServiceAbs {
    private final WithdrawalAddressRepository withRepo;
    private final UserRepository userRepository;

    @Override
    public Apiresponse addWithdrawal(WithdrawalDto withdrawalDto) {
        Optional<User> byId = userRepository.findById(withdrawalDto.getUserId());
        if (byId.isPresent()) {
            User user = byId.get();
            WithDrawalAddress build = WithDrawalAddress.builder()
                    .user(user)
                    .valyutaType(withdrawalDto.getValyutaType())
                    .primaryTarmoq(withdrawalDto.getPrimaryTarmoq())
                    .nickName(withdrawalDto.getNickName())
                    .withAddress(withdrawalDto.getWithAddress())
                    .build();
            withRepo.save(build);
            user.setWithAddressSize(user.getWithAddressSize() + 1);
            userRepository.save(user);
            return new Apiresponse("muvaffaqiyatli saqlandi", true);
        }
        return new Apiresponse("sizga address ochish mumkin emas", false);
    }

    @Override
    public Apiresponse deleteWithdrawal(UUID id) {
        Optional<WithDrawalAddress> byId = withRepo.findById(id);
        if (byId.isPresent()) {
            WithDrawalAddress withDrawalAddress = byId.get();
            withRepo.delete(withDrawalAddress);
            return new Apiresponse("Muvaffaqiyatli o'chirildi", true);
        }
        return new Apiresponse("bunday address mavjud emas", false);
    }
}
