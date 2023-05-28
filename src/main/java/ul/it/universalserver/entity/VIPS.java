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
@Entity(name = "vips")
public class VIPS extends AbsEntity {
    private String name;

    @Column(nullable = false)
    private UUID photoId;//ushbu vipning qurilmasining rasmi

    @Column(nullable = false)
    private double minQuantifyAmount; //vip uchun minimum pul miqdori

    @Column(nullable = false)
    private double maxQuantifyAmount; //vip uchun maximal pul miqdori

    @Column(nullable = false)
    private double shareRatio; //vip uchun foiz miqdori

    @Column(nullable = false)
    private Integer alfaRobotsAvailablePerDay; //alfa coinda necha marta menet qilsa boladi

    @Column(nullable = false)
    private boolean active; //ushbu vip faolmi yoki yo'q
}
