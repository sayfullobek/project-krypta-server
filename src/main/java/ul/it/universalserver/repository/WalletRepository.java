package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ul.it.universalserver.entity.Wallet;

import java.util.UUID;

@CrossOrigin
public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
