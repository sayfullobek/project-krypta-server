package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ul.it.universalserver.entity.ArchivePay;

import java.util.List;
import java.util.UUID;

@CrossOrigin
public interface ArchivePayRepository extends JpaRepository<ArchivePay, UUID> {
    List<ArchivePay> findAllByUser_id(UUID user_id);
}
