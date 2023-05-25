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
public class InvestmentUserDto {
    private UUID id;
    private UUID vipsId;
    private double money;
    private UUID usersId;
    private String info;
}
