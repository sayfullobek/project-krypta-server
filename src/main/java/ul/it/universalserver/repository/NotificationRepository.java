package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ul.it.universalserver.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
