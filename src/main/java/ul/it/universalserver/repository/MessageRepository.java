package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ul.it.universalserver.entity.Message;

import java.util.List;

@CrossOrigin
public interface MessageRepository extends JpaRepository<Message, Integer> {
    void deleteAllByNotification_Id(Integer notification_id);

    List<Message> findAllByNotification_id(Integer notification_id);
}