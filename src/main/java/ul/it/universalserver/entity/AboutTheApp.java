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
    private String about;//app haqidagi ma'lumot

    @Column(nullable = false)
    private Integer DayAppLaunched; //ushbu app necha kundan beri ishlayapti

    @Column(nullable = false)
    private double HowMuchMoneyApp; //ushbu progremmada qancha pul aylangan

    @Column(nullable = false, unique = false, length = 20000)
    private String appContactLink; //app ning telegram kontacti linki

    @Column(nullable = false)
    private UUID photoId; //ushbu appning rasmi

    @Column(nullable = false, unique = true)
    private UUID logoId; //ushbu appning logo rasmi
}

