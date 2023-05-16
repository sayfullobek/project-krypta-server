package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ul.it.universalserver.entity.Pools;

public interface PoolRepository extends JpaRepository<Pools, Integer> {
    boolean existsPoolsByUzNameEqualsIgnoreCaseAndEnNameEqualsIgnoreCaseAndRuNameEqualsIgnoreCase(String uzName, String enName, String ruName);

    boolean existsPoolsByUzNameEqualsIgnoreCaseAndEnNameEqualsIgnoreCaseAndRuNameEqualsIgnoreCaseAndIdNot(String uzName, String enName, String ruName, Integer id);
}
