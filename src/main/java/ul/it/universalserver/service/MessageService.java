package ul.it.universalserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ul.it.universalserver.entity.Message;
import ul.it.universalserver.entity.Notification;
import ul.it.universalserver.logic.ServiceAbs;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.MessageDto;
import ul.it.universalserver.repository.MessageRepository;
import ul.it.universalserver.repository.NotificationRepository;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService extends ServiceAbs {
    private final MessageRepository messageRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public Apiresponse addMessage(MessageDto messageDto) {
        Optional<Notification> byId = notificationRepository.findById(messageDto.getNotificationId());
        if (byId.isPresent()) {
            Notification notification = byId.get();
            Message build = Message.builder()
                    .uzDescription(messageDto.getUzDescription())
                    .enDescription(messageDto.getEnDescription())
                    .ruDescription(messageDto.getRuDescription())
                    .whenWrite(new Date())
                    .notification(notification)
                    .build();
            build.setUzName(messageDto.getUzName());
            build.setEnName(messageDto.getEnName());
            build.setRuName(messageDto.getRuName());
            messageRepository.save(build);
            return new Apiresponse("muvaffaqiyatli saqlandi", true);
        }
        return new Apiresponse("bunday xabarnoma mavjud emas", false);
    }

    @Override
    public Apiresponse editMessage(Integer id, MessageDto messageDto) {
        Optional<Message> byId = messageRepository.findById(id);
        if (byId.isPresent()) {
            Message message = messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getMessage"));
            message.setUzName(messageDto.getUzName());
            message.setEnName(messageDto.getEnName());
            message.setRuName(messageDto.getRuName());
            message.setUzDescription(message.getUzDescription());
            message.setEnDescription(message.getEnDescription());
            message.setRuDescription(message.getRuDescription());
            messageRepository.save(message);
            return new Apiresponse("muvaffaqiyatli taxrirlandi", true);
        }
        return new Apiresponse("bunday xabar mavjud emas", false);
    }

    @Override
    public Apiresponse deleteMessage(Integer id) {
        Optional<Message> byId = messageRepository.findById(id);
        if (byId.isPresent()) {
            Message message = byId.get();
            messageRepository.delete(message);
            return new Apiresponse("muvaffaqiyatli o'chirildi", true);
        }
        return new Apiresponse("bunday xabar mavjud emas", false);
    }
}
