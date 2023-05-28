package ul.it.universalserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ul.it.universalserver.entity.Notification;
import ul.it.universalserver.logic.ServiceAbs;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.NotificationDto;
import ul.it.universalserver.repository.MessageRepository;
import ul.it.universalserver.repository.NotificationRepository;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService extends ServiceAbs {
    private final NotificationRepository notificationRepository;
    private final AttachmentService attachmentService;
    private final MessageRepository messageRepository;

    @Override
    public Apiresponse addNotification(NotificationDto notificationDto) {
        Notification build = Notification.builder()
                .photoId(notificationDto.getPhotoId())
                .description(notificationDto.getDescription())
                .systemInformationDate(new Date())
                .build();
        build.setUzName(notificationDto.getUzName());
        build.setEnName(notificationDto.getEnName());
        build.setRuName(notificationDto.getRuName());
        notificationRepository.save(build);
        return new Apiresponse(notificationDto.getUzName() + " message has been sent to all", true);
    }

    @Override
    public Apiresponse editNotification(Integer id, NotificationDto notificationDto) {
        Optional<Notification> byId = notificationRepository.findById(id);
        if (byId.isPresent()) {
            Notification notification = byId.get();
            notification.setUzName(notificationDto.getUzName());
            notification.setEnName(notificationDto.getEnName());
            notification.setRuName(notificationDto.getRuName());
            notification.setDescription(notificationDto.getDescription());
            notificationRepository.save(byId.get());
            return new Apiresponse("xabar taxrirlandi", true);
        }
        return new Apiresponse("bunday xabar mavjud emas", false);
    }

    @Override
    public Apiresponse editPhotoNotification(Integer id, NotificationDto notificationDto) {
        Optional<Notification> byId = notificationRepository.findById(id);
        if (byId.isPresent()) {
            Notification notification = byId.get();
            attachmentService.deletePhoto(notification.getPhotoId());
            notification.setPhotoId(notificationDto.getPhotoId());
            notificationRepository.save(byId.get());
            return new Apiresponse("xabar taxrirlandi", true);
        }
        return new Apiresponse("bunday xabar mavjud emas", false);
    }

    @Override
    public Apiresponse deleteNotification(Integer id) {
        Optional<Notification> byId = notificationRepository.findById(id);
        if (byId.isPresent()) {
            Notification notification = byId.get();
            messageRepository.deleteAllByNotification_Id(notification.getId());
            attachmentService.deletePhoto(notification.getPhotoId());
            notificationRepository.delete(notification);
            return new Apiresponse("ushbu xabar o'chirildi", true);
        }
        return new Apiresponse("bunday xabar mavjud emas", false);
    }
}
