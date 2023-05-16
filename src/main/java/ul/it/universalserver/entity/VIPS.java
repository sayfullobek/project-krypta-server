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

    @Column(nullable = false, unique = true)
    private UUID photoId;//ushbu vipning rasmi

    @Column(nullable = false)
    private double minQuantifyAmount; //vip uchun minimum pul miqdori

    @Column(nullable = false)
    private double maxQuantifyAmount; //vip uchun maximal pul miqdori

    @Column(nullable = false)
    private double shareRatio; //vip uchun ulush miqdori

    @Column(nullable = false)
    private double effectiveEmount; //samarali miqdor

    @Column(nullable = false)
    private Integer directlyPromoteMembers; //a'zolarni bevosita targib qilish

    @Column(nullable = false)
    private Integer secondThridGenerationMembers; //ikkinchi va uchinchi avlod vakillari

    @Column(nullable = false)
    private double profits; //foyda

    @Column(nullable = false)
    private Integer metaGORobotsAvailablePerDay; //metaGO robotlari kuniga mavjud

    @Column(nullable = false)
    private double teamAward; //jamoa mukofoti foizi

    @Column(nullable = false)
    private boolean active; //ushbu vip faolmi yoki yo'q
}
