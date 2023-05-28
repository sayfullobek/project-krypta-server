package ul.it.universalserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ul.it.universalserver.entity.Feedback;
import ul.it.universalserver.entity.User;
import ul.it.universalserver.entity.enums.FeedbackName;
import ul.it.universalserver.logic.ServiceAbs;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.FeedBackDto;
import ul.it.universalserver.repository.FeedBackRepository;
import ul.it.universalserver.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackService extends ServiceAbs {
    private final FeedBackRepository feedBackRepository;
    private final UserRepository userRepository;

    @Override
    public Apiresponse sendFeedback(FeedBackDto feedBackDto) {
        Optional<User> byId = userRepository.findById(feedBackDto.getUserId());
        if (byId.isPresent()) {
            User getUser = byId.get();
            Feedback build = Feedback.builder()
                    .feedbackName(FeedbackName.valueOf(feedBackDto.getFeedbackName()))
                    .information(feedBackDto.getInformation())
                    .firstName(getUser.getFirstName())
                    .lastName(getUser.getLastName())
                    .phoneNumber(getUser.getPhoneNumber())
                    .email(getUser.getEmail())
                    .build();
            Feedback save = feedBackRepository.save(build);
            getUser.getFeedbacks().add(save);
            userRepository.save(getUser);
            return new Apiresponse("muvaffaqiyatli yuborildi", true);
        }
        return new Apiresponse("Sizning ma'lumotingizda xatolik mavjud", false);
    }
}
