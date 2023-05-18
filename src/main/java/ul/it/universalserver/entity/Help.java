package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.enums.HelpName;
import ul.it.universalserver.entity.template.AbsEntity;
import ul.it.universalserver.entity.template.AbsNameEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Help extends AbsNameEntity {
    @Enumerated(EnumType.STRING)
    private HelpName helpName;

    @Column(nullable = false, length = 1000000)
    private String info;
}
