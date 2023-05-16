package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ul.it.universalserver.entity.StakingPool;

public interface StakingPoolsRepository extends JpaRepository<StakingPool, Integer> {

}
