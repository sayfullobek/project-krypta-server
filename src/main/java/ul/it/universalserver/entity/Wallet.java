package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.template.AbsEntity;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Wallet extends AbsEntity {
    private double nowMoney; //hozirgi xisobidagi pul
    private double AllInCome; //ja'mi daromad
    private double takeOff; //yechib olinga pullar hisobi
    private double offer; //necha kishini taklif qilgan
    private double howManyTimesDidHeWithdrawMoney; //necha marta pul yechib olgan
    private Integer nechaMartaPulKiritgan;
    private double theMoneyHeInvested; //o'zi kiritgan pul
    private double friendsProfit; //do'stlaridan kelgan foiz
    private double vipMoney; //vipdagi qurilmadagi pul
}
