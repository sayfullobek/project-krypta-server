package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ul.it.universalserver.entity.StakingPool;
@CrossOrigin
public interface StakingPoolsRepository extends JpaRepository<StakingPool, Integer> {

}
