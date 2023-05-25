package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Deposit extends AbsEntity {
    private double money; //userning qancha pul qoygani
    private double backMoney; //biz unga qancha qaytaramiz

    @ManyToOne
    private InvestmentDetails investmentDetails;

    private Date backTime;//qaytarish vaqti

    @ManyToOne
    private User user;

    private boolean getIs; //ushbu user pulni oldimi
}
