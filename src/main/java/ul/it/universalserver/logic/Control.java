package ul.it.universalserver.logic;

import org.springframework.http.HttpEntity;
import ul.it.universalserver.payload.*;

import java.util.UUID;

public abstract class Control {
    public HttpEntity<?> getCoins() {
        return null;
    }

    public HttpEntity<?> addCoin(CoinDto coinDto) {
        return null;
    }

    public HttpEntity<?> editCoin(UUID id, CoinDto coinDto) {
        return null;
    }

    public HttpEntity<?> deleteCoin(UUID id) {
        return null;
    }

    //start asl control VIPS

    public HttpEntity<?> getVips() {
        return null;
    }

    public HttpEntity<?> addVips(VipsDto vipsDto) {
        return null;
    }

    public HttpEntity<?> editVips(UUID id, VipsDto vipsDto) {
        return null;
    }

    public HttpEntity<?> deleteVips(UUID id) {
        return null;
    }

    public HttpEntity<?> getOneVips(UUID id) {
        return null;
    }


    public HttpEntity<?> getNotification() {
        return null;
    }

    public HttpEntity<?> addNotification(NotificationDto notificationDto) {
        return null;
    }

    public HttpEntity<?> editNotification(Integer id, NotificationDto notificationDto) {
        return null;
    }

    public HttpEntity<?> editNotificationPhoto(Integer id, NotificationDto notificationDto) {
        return null;
    }

    public HttpEntity<?> deleteNotification(Integer id) {
        return null;
    }

    public HttpEntity<?> getOneNotification(Integer id) {
        return null;
    }


    public HttpEntity<?> getMessage(Integer id) {
        return null;
    }

    public HttpEntity<?> addMessage(MessageDto messageDto) {
        return null;
    }

    public HttpEntity<?> editMessage(Integer id, MessageDto messageDto) {
        return null;
    }

    public HttpEntity<?> deleteMessage(Integer id) {
        return null;
    }

    public HttpEntity<?> getOneMessage(Integer id) {
        return null;
    }


    public HttpEntity<?> getArchivePay() {
        return null;
    }

    public HttpEntity<?> addArchivePay(ArchivePayDto archivePayDto) {
        return null;
    }

    public HttpEntity<?> getOneArchivePayByUserId(UUID id) {
        return null;
    }

    public HttpEntity<?> getOneArchivePay(UUID id) {
        return null;
    }


    //end asl control VIPS

    //sayfullo okeng boshladi tvar
    public HttpEntity<?> getPools() {
        return null;
    }

    public HttpEntity<?> addPools(PoolsDto poolsDto) {
        return null;
    }

    public HttpEntity<?> editPools(Integer id, PoolsDto poolsDto) {
        return null;
    }

    public HttpEntity<?> editPoolsPhoto(Integer id, PoolsDto poolsDto) {
        return null;
    }

    public HttpEntity<?> deletePools(Integer id) {
        return null;
    }

    public HttpEntity<?> getOnePools(Integer id) {
        return null;
    }
    //sayfullo okeng tugatdi tvar


    public HttpEntity<?> getInvestment(Integer id) {
        return null;
    }

    public HttpEntity<?> addInvestment(InvestmentDetailsDto investmentDetailsDto) {
        return null;
    }

    public HttpEntity<?> editInvestment(UUID id, InvestmentDetailsDto investmentDetailsDto) {
        return null;
    }

    public HttpEntity<?> deleteInvestment(UUID id) {
        return null;
    }

    public HttpEntity<?> getOneInvestment(UUID id) {
        return null;
    }

    public HttpEntity<?> sendFeedback(FeedBackDto feedBackDto) {
        return null;
    }

    public HttpEntity<?> getFeedback() {
        return null;
    }

    public HttpEntity<?> addWithdrawal(WithdrawalDto withdrawalDto) {
        return null;
    }

    public HttpEntity<?> getWithdrawalByUserId(UUID id) {
        return null;
    }

    public HttpEntity<?> getAllWih() {
        return null;
    }

    public HttpEntity<?> deleteWithdrawal(UUID id) {
        return null;
    }
}
