package ul.it.universalserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ul.it.universalserver.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLogin {
    private User user;
    private ResToken resToken;
}
