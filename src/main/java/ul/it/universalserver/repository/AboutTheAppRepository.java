package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ul.it.universalserver.entity.AboutTheApp;

import java.util.UUID;

public interface AboutTheAppRepository extends JpaRepository<AboutTheApp, UUID> {
}
