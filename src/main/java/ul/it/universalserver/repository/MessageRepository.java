package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ul.it.universalserver.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    void deleteAllByNotification_Id(Integer notification_id);

}
