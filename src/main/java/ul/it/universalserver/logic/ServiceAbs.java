package ul.it.universalserver.logic;

import ul.it.universalserver.entity.Deposit;
import ul.it.universalserver.entity.InvestmentUser;
import ul.it.universalserver.payload.*;

import java.util.List;
import java.util.UUID;

public abstract class ServiceAbs {
    public Apiresponse addCoin(CoinDto coinDto) {
        return null;
    }

    public Apiresponse editCoin(UUID id, CoinDto coinDto) {
        return null;
    }

    public Apiresponse deleteCoin(UUID id) {
        return null;
    }

    public Apiresponse addPools(PoolsDto coinDto) {
        return null;
    }

    public Apiresponse editPools(Integer id, PoolsDto coinDto) {
        return null;
    }

    public Apiresponse editPhotoPools(Integer id, PoolsDto coinDto) {
        return null;
    }

    public Apiresponse deletePools(Integer id) {
        return null;
    }


    public Apiresponse addNotification(NotificationDto notificationDto) {
        return null;
    }

    public Apiresponse editNotification(Integer id, NotificationDto notificationDto) {
        return null;
    }

    public Apiresponse editPhotoNotification(Integer id, NotificationDto notificationDto) {
        return null;
    }

    public Apiresponse deleteNotification(Integer id) {
        return null;
    }

    public Apiresponse addMessage(MessageDto messageDto) {
        return null;
    }

    public Apiresponse editMessage(Integer id, MessageDto messageDto) {
        return null;
    }

    public Apiresponse deleteMessage(Integer id) {
        return null;
    }

    public Apiresponse sendArchiveDto(ArchivePayDto archivePayDto) {
        return null;
    }

    //Asl Okang
    public Apiresponse addVips(VipsDto coinDto) {
        return null;
    }

    public Apiresponse editVips(UUID id, VipsDto coinDto) {
        return null;
    }

    public Apiresponse deleteVips(UUID id) {
        return null;
    }
    //end


    public Apiresponse addInvestment(InvestmentDetailsDto coinDto) {
        return null;
    }

    public Apiresponse editInvestment(UUID id, InvestmentDetailsDto coinDto) {
        return null;
    }

    public Apiresponse deleteInvestment(UUID id) {
        return null;
    }

    public Apiresponse sendFeedback(FeedBackDto feedBackDto) {
        return null;
    }

    public Apiresponse addWithdrawal(WithdrawalDto withdrawalDto) {
        return null;
    }

    public Apiresponse deleteWithdrawal(UUID id) {
        return null;
    }

    public Apiresponse updateAboutApp(UUID id, AboutTheAppDto aboutTheAppDto) {
        return null;
    }

    public Apiresponse addInvestmentUser(InvestmentUserDto investmentUserDto) {
        return null;
    }

    public List<InvestmentUser> getUserByInvestment(UUID id) {
        return null;
    }

    public Apiresponse addDeposit(DepositDto depositDto) {
        return null;
    }

    public List<Deposit> getUserByDeposit(UUID id) {
        return null;
    }
}
