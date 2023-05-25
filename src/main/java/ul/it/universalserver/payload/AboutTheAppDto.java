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
public class AboutTheAppDto {
    private UUID id;
    private String uzAbout;//app haqidagi ma'lumot uzbekcha
    private String enAbout;//app haqidagi ma'lumot inglizcha
    private String ruAbout;//app haqidagi ma'lumot ruscha
    private Integer dayAppLaunched; //ushbu app necha kundan beri ishlayapti
    private double howMuchMoneyApp; //ushbu progremmada qancha pul aylangan
    private String appContactLink; //app ning telegram kontacti linki
    private String status;
}
