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
public class CoinDto {
    private UUID id;
    private String name;
    private UUID photoId; //coinning rasmi
    private double percentage; //coinning fozi
    private double dollar; //coinning dollari
    private boolean active; //ushbu coin activmi yoqmi
}
