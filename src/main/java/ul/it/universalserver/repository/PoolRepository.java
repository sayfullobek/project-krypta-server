package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ul.it.universalserver.entity.Pools;
@CrossOrigin
public interface PoolRepository extends JpaRepository<Pools, Integer> {
    boolean existsPoolsByUzNameEqualsIgnoreCaseAndEnNameEqualsIgnoreCaseAndRuNameEqualsIgnoreCase(String uzName, String enName, String ruName);

    boolean existsPoolsByUzNameEqualsIgnoreCaseAndEnNameEqualsIgnoreCaseAndRuNameEqualsIgnoreCaseAndIdNot(String uzName, String enName, String ruName, Integer id);
}
