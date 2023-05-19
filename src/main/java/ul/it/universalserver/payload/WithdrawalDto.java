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
public class WithdrawalDto {
    private UUID id;
    private UUID userId;
    private String valyutaType; //valyuta turi
    private String primaryTarmoq; //asosiy tarmoq
    private String nickName; //taxallus
    private String password; //paroli
    private String withAddress; //withdrawal address
}
