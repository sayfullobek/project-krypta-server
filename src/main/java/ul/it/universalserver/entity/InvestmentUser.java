package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class InvestmentUser extends AbsEntity {
    @ManyToMany
    private List<VIPS> vips;

    private double money; //ushbu user qancha pulni abarot qilmoqchi

    private String info; //qaysi birjadan qaysi birjaga pul aylantirildi

    @ManyToMany
    private List<User> users;

    private double howManyBack; //ushbu userga qancha pul qaytdi
}
