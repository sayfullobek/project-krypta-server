package ul.it.universalserver.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePassword {
    private String oldPassword;
    private String newPassword;
    private String newPrePassword;
}
