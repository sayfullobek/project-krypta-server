package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ul.it.universalserver.entity.AttachmentContent;

import java.util.UUID;

@CrossOrigin
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {
    AttachmentContent findByAttachmentId(UUID attachment_id);
}
