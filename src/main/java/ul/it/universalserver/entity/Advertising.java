package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Advertising extends AbsEntity {//ushbu appning carousel reklamasi
    @Column(nullable = false, unique = true)
    private UUID id; //reklama rasmi
}
