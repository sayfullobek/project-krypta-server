package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ul.it.universalserver.entity.InvestmentUser;

import java.util.UUID;

public interface InvestmentUserRepository extends JpaRepository<InvestmentUser, UUID> {
}
