package ul.it.universalserver.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private Integer id;
    private String uzName;
    private String enName;
    private String ruName;
    private Date whenWrite; //ushbu sms qachon yozildi
    private String uzDescription; //ushbu sms haqida ma'lumot
    private String enDescription; //ushbu sms haqida ma'lumot
    private String ruDescription; //ushbu sms haqida ma'lumot
    private Integer notificationId;
}
