package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.template.AbsEntity;

import javax.persistence.Entity;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Coins extends AbsEntity {
    private String name;
    private UUID photoId; //coinning rasmi
    private double percentage; //coinning fozi
    private double dollar; //coinning dollari
    private boolean active; //ushbu coin activmi yoqmi
}
