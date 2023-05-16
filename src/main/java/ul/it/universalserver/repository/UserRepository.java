package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ul.it.universalserver.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByPhoneNumber(String username);

    Optional<User> findUserByEmail(String email);

    List<User> findUsersByRoleId(Integer role_id);

    boolean existsUserByEmail(String email);

    boolean existsUserByPhoneNumber(String phoneNumber);

    boolean existsUserByReferralCode(String referralCode);
}
