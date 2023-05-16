package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.template.AbsNameEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Notification extends AbsNameEntity {
    private UUID photoId; //messenger rasmi

    @Column(nullable = false)
    private String description; //messenger haqida

    private Date systemInformationDate;
}
