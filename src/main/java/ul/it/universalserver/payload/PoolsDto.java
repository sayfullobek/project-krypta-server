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
public class PoolsDto {
    private Integer id;
    private String uzName;
    private String enName;
    private String ruName;
    private UUID photoId;
    private double annualizedInterest; //ushbu hovuzning yillik foizi
    private String stakingMinimum; //minimal stavka
}
