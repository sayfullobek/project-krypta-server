package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ul.it.universalserver.entity.Deposit;

import java.util.List;
import java.util.UUID;

public interface DepositRepository extends JpaRepository<Deposit, UUID> {
    List<Deposit> findAllByUser_id(UUID user_id);
}
