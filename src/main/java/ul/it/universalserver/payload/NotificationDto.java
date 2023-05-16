package ul.it.universalserver.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDto {
    private Integer id;
    private String uzName;
    private String enName;
    private String ruName;
    private UUID photoId; //messenger rasmi
    private String description;
    private Date whenWrite; //ushbu sms qachon yozildi
    private Integer notificationId;
}
