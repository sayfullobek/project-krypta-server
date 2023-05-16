package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.template.AbsNameEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Pools extends AbsNameEntity {
    @Column(nullable = false, unique = true)
    private UUID photoId;

    @Column(nullable = false)
    private double annualizedInterest; //ushbu hovuzning yillik foizi

    private String stakingMinimum; //minimal stavka
}
