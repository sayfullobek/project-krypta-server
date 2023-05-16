package ul.it.universalserver.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VipsDto {
    private UUID id;
    private String name;

    private UUID photoId;//ushbu vipning rasmi

    private double minQuantifyAmount; //vip uchun minimum pul miqdori

    private double maxQuantifyAmount; //vip uchun maximal pul miqdori

    private double shareRatio; //vip uchun ulush miqdori

    private double effectiveEmount; //samarali miqdor

    private Integer directlyPromoteMembers; //a'zolarni bevosita targib qilish

    private Integer secondThridGenerationMembers; //ikkinchi va uchinchi avlod vakillari

    private double profits; //foyda

    private Integer metaGORobotsAvailablePerDay; //metaGO robotlari kuniga mavjud

    private double teamAward; //jamoa mukofoti foizi

    private boolean active; //ushbu vip faolmi yoki yo'q

}