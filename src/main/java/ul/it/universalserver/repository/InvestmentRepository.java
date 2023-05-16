package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ul.it.universalserver.entity.InvestmentDetails;

import java.util.List;
import java.util.UUID;

public interface InvestmentRepository extends JpaRepository<InvestmentDetails, UUID> {
    void deleteAllByPools_Id(Integer pools_id);

    List<InvestmentDetails> findAllByPools_Id(Integer pools_id);
}
