package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ul.it.universalserver.entity.WithDrawalAddress;

import java.util.List;
import java.util.UUID;

@CrossOrigin
public interface WithdrawalAddressRepository extends JpaRepository<WithDrawalAddress, UUID> {
    List<WithDrawalAddress> findAllByUser_Id(UUID user_id);
}
