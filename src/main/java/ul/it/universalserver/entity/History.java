package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.enums.HistoryName;
import ul.it.universalserver.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "histories")
public class History extends AbsEntity {
    private Date when; //qachon kirim yoki chiqim boldi
    private double money;
    @Enumerated(EnumType.STRING)
    private HistoryName status; //bu kirim yoki chiqmni saqlaydi
    @ManyToOne
    private User user;
}
