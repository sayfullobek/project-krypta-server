package ul.it.universalserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ul.it.universalserver.entity.Deposit;
import ul.it.universalserver.entity.InvestmentDetails;
import ul.it.universalserver.entity.User;
import ul.it.universalserver.logic.ServiceAbs;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.DepositDto;
import ul.it.universalserver.repository.DepositRepository;
import ul.it.universalserver.repository.InvestmentRepository;
import ul.it.universalserver.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepositService extends ServiceAbs {
    private final UserRepository userRepository;
    private final DepositRepository depositRepository;
    private final InvestmentRepository investmentRepository;

    @Override
    public Apiresponse addDeposit(DepositDto depositDto) {
        InvestmentDetails getInvest = investmentRepository.findById(depositDto.getInvestId()).orElseThrow(() -> new ResourceNotFoundException("getInvestUser"));
        User getUser = userRepository.findById(depositDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("getUser"));
        double money = depositDto.getMoney();
        double monthly = getInvest.getMonthly();
        double sum = (((money * monthly) / 100) / 30) * getInvest.getHowManyDays();
        getUser.getWallet().setNowMoney(getUser.getWallet().getNowMoney() - money);
        Date date = new Date();
        Deposit build = Deposit.builder()
                .investmentDetails(getInvest)
                .user(getUser)
                .money(depositDto.getMoney())
                .backMoney(sum + depositDto.getMoney())
                .backTime(new Date(date.getYear(), date.getMonth(), date.getDate() + getInvest.getHowManyDays(), date.getHours(), date.getMinutes(), date.getSeconds()))
                .build();
        depositRepository.save(build);
        userRepository.save(getUser);
        return new Apiresponse("successfully", true);
    }

    @Override
    public List<Deposit> getUserByDeposit(UUID id) {
        return depositRepository.findAllByUser_id(id);
    }
}
