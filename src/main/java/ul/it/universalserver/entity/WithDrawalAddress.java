package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class WithDrawalAddress extends AbsEntity {
    @ManyToOne(optional = false)
    private User user;
    private String valyutaType; //balyuta turi
    private String primaryTarmoq; //asosiy tarmoq
    private String nickName; //taxallus
    private String password; //paroli
    @Column(nullable = false)
    private String withAddress; //withdrawal address
}
