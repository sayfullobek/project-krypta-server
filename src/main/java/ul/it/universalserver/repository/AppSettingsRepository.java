package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ul.it.universalserver.entity.AppSettings;

@CrossOrigin
public interface AppSettingsRepository extends JpaRepository<AppSettings, Integer> {
}
