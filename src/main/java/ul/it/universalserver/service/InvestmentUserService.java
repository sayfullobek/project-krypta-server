package ul.it.universalserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ul.it.universalserver.entity.InvestmentDetails;
import ul.it.universalserver.entity.InvestmentUser;
import ul.it.universalserver.entity.User;
import ul.it.universalserver.entity.VIPS;
import ul.it.universalserver.logic.ServiceAbs;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.InvestmentUserDto;
import ul.it.universalserver.repository.InvestmentRepository;
import ul.it.universalserver.repository.InvestmentUserRepository;
import ul.it.universalserver.repository.UserRepository;
import ul.it.universalserver.repository.VipsRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvestmentUserService extends ServiceAbs {
    private final InvestmentUserRepository investmentUserRepository;
    private final UserRepository userRepository;
    private final VipsRepository vipsRepository;

    @Override
    public Apiresponse addInvestmentUser(InvestmentUserDto investmentUserDto) {
        User getUser = userRepository.findById(investmentUserDto.getUsersId()).orElseThrow(() -> new ResourceNotFoundException("getUser"));
        VIPS vips = vipsRepository.findById(investmentUserDto.getVipsId()).orElseThrow(() -> new ResourceNotFoundException("getVips"));
        double foiz = (investmentUserDto.getMoney() * 0.015) + investmentUserDto.getMoney();
        getUser.getWallet().setNowMoney((getUser.getWallet().getNowMoney() - investmentUserDto.getMoney()) + foiz);

        String[] infoMe = investmentUserDto.getInfo().split(" - ");
        String by = infoMe[0];
        String to = infoMe[1];
        String about = "Your money has been converted from Exchange " + by + " to Exchange " + to + " and deposited into your account";
        InvestmentUser build = InvestmentUser.builder()
                .vips(Collections.singletonList(vips))
                .money(investmentUserDto.getMoney())
                .info(about)
                .users(Collections.singletonList(getUser))
                .howManyBack(foiz)
                .build();
        investmentUserRepository.save(build);
        return new Apiresponse("The money has been transferred to your account", true);
    }

    @Override
    public List<InvestmentUser> getUserByInvestment(UUID id) {
        return super.getUserByInvestment(id);
    }
}
