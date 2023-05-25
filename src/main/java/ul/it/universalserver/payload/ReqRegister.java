package ul.it.universalserver.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReqRegister {
    private String firstName; //ismi

    private String lastName; //familiyasi

    private String email; //emaili

    private String phoneNumber; //nomeri

    private String password; //paroli

    private String gander;

    private String referralCode;

    private MultipartHttpServletRequest formData;

    private boolean agree; //foydalanuvchi bizning qoidalarimizga rosi yoki yo'qligi

    private String status;

    private String cantForgetPassword;
}
