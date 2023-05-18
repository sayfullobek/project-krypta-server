package ul.it.universalserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ul.it.universalserver.entity.Feedback;
import ul.it.universalserver.logic.Control;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.FeedBackDto;
import ul.it.universalserver.repository.FeedBackRepository;
import ul.it.universalserver.service.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
public class FeedBackController extends Control {
    private final FeedbackService feedbackService;
    private final FeedBackRepository feedBackRepository;

    @Override
    @PostMapping
    public HttpEntity<?> sendFeedback(@RequestBody FeedBackDto feedBackDto) {
        Apiresponse apiresponse = feedbackService.sendFeedback(feedBackDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @GetMapping
    public HttpEntity<?> getFeedback() {
        List<Feedback> all = feedBackRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
