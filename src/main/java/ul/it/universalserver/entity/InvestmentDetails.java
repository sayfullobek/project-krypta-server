package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.enums.StackingCoin;
import ul.it.universalserver.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class InvestmentDetails extends AbsEntity {
    private double monthly; //oyiga qancha foiz bo'lishi

    @Enumerated(value = EnumType.STRING)
    private StackingCoin stakingPool; //qaysi pul turida investitsiya qilishi

    @Column(nullable = false)
    private Integer howManyDays; //necha kunda ushbu hovuzga invistitsiya kirita olishi

    @Column(nullable = false, length = 1000000)
    private String description; //ushbu hovuz haqida ma'lumot

    @Column(nullable = false)
    private String financialAmount; //ushbu hovuzga invistitsiya kiritish uchun qancha mab'la'g kk

    @ManyToMany
    private List<VIPS> vips;

    private boolean active;

    @ManyToOne
    private Pools pools;
}
