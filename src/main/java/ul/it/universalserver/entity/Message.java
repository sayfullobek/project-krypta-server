package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.template.AbsNameEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Message extends AbsNameEntity {
    private Date whenWrite; //ushbu sms qachon yozildi

    @Column(nullable = false, length = 1000000)
    private String description; //ushbu sms haqida ma'lumot

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Notification notification;
}
