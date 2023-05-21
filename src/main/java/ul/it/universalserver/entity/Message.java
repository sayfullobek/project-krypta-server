package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.template.AbsNameEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Message extends AbsNameEntity {
    @Column(nullable = false, length = 1000000)
    private String uzDescription; //ushbu sms haqida ma'lumot
    @Column(nullable = false, length = 1000000)
    private String enDescription; //ushbu sms haqida ma'lumot
    @Column(nullable = false, length = 1000000)
    private String ruDescription; //ushbu sms haqida ma'lumot
    private Date whenWrite; //ushbu sms qachon yozildi
    @ManyToOne
    private Notification notification;
}
