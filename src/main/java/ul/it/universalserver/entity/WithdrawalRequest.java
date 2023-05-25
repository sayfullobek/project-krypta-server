package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class WithdrawalRequest extends AbsEntity {//userlar pul chiqarish uchun so'rov yuborsa ushbu tablega saqlanadi va bu table hech qachon o'chmaydi va taxrirlanmaydi
    @ManyToMany
    private List<WithDrawalAddress> withDrawalAddress; //qaysi address orqali pul yechilyapti

    private Date whenDidHeSendTheRequest; //pul yechish uchun qachon so'rov yubordi

    private boolean wasTheMoneyThrownAway; //pul tashlab berildimi

    private double money; //qancha pul chiqarmoqchi
}
