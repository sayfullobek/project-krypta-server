package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ul.it.universalserver.entity.InvestmentDetails;

import java.util.List;
import java.util.UUID;
@CrossOrigin
public interface InvestmentRepository extends JpaRepository<InvestmentDetails, UUID> {
    void deleteAllByPools_Id(Integer pools_id);

    List<InvestmentDetails> findAllByPools_Id(Integer pools_id);
}
