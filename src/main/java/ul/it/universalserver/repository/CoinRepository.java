package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ul.it.universalserver.entity.Coins;

import java.util.UUID;

@CrossOrigin
public interface CoinRepository extends JpaRepository<Coins, UUID> {
    boolean existsCoinsByNameEqualsIgnoreCase(String name);
    boolean existsCoinsByNameEqualsIgnoreCaseAndIdNot(String name, UUID id);
}
