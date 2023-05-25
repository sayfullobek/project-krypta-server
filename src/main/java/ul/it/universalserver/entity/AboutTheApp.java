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
public class AboutTheApp extends AbsEntity {//application haqidagi ma'lumot entity
    @Column(nullable = false, unique = true, length = 900000)
    private String uzAbout;//app haqidagi ma'lumot uzbekcha

    @Column(nullable = false, unique = true, length = 900000)
    private String enAbout;//app haqidagi ma'lumot inglizcha

    @Column(nullable = false, unique = true, length = 900000)
    private String ruAbout;//app haqidagi ma'lumot ruscha

    @Column(nullable = false)
    private Integer dayAppLaunched; //ushbu app necha kundan beri ishlayapti

    @Column(nullable = false)
    private double howMuchMoneyApp; //ushbu progremmada qancha pul aylangan

    @Column(nullable = false, unique = true, length = 20000)
    private String appContactLink; //app ning telegram kontacti linki

}

