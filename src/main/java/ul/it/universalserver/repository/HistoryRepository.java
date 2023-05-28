package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ul.it.universalserver.entity.History;

import java.util.List;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<History, UUID> {
    List<History> findAllByUser_id(UUID user_id);
}
