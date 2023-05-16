package ul.it.universalserver.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ul.it.universalserver.entity.enums.Gander;
import ul.it.universalserver.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@Builder
public class User extends AbsEntity implements UserDetails {
    @Column(nullable = false)
    private String firstName; //ismi

    private String lastName; //familiyasi

    private String email; //emaili

    private String phoneNumber; //nomeri

    private String password; //paroli

    @ManyToMany
    private Set<Role> role; //roli

    @Enumerated(value = EnumType.STRING)
    private Gander gander; //jinsi

    @Column(nullable = false, unique = true)
    private String referralCode; //referral code

    private UUID photoId; //rasmi

    @Column(nullable = false)
    private boolean agree; //foydalanuvchi bizning qoidalarimizga rosi yoki yo'qligi

    private String status;

    private boolean enabled = true; //ushbu account ishlayaptimi yoki yo'q

    private boolean credentialsNonExpired = true; //ushbu accountning ma'lumotlarining muddati tugaganmi yoki yo'qmi

    private boolean accountNonLocked = true; //ushbu account ochiq yoki yo'qligi
    private boolean accountNonExpired = true; //ushbu accountning muddati tugaganmi yoki yo'qmi

    public User(String firstName, String lastName, String email, String phoneNumber, String password, Gander gander, String referralCode, boolean agree, Set<Role> role, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.gander = gander;
        this.referralCode = referralCode;
        this.agree = agree;
        this.role = role;
        this.status = status;
    }

    public String whatUsername() {
        return status.equals("phone") ? phoneNumber : email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getUsername() {
        return whatUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
