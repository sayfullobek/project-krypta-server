package ul.it.universalserver.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResRegister {
    private GetLogin getLogin;
    private Apiresponse apiresponse;
}
