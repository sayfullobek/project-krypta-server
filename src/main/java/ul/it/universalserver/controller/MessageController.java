package ul.it.universalserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ul.it.universalserver.entity.Message;
import ul.it.universalserver.logic.Control;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.MessageDto;
import ul.it.universalserver.repository.MessageRepository;
import ul.it.universalserver.service.MessageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notification/message")
@RequiredArgsConstructor
public class MessageController extends Control {
    private final MessageService messageService;
    private final MessageRepository messageRepository;

    @Override
    @GetMapping("/{id}")
    public HttpEntity<?> getMessage(@PathVariable Integer id) {
        List<Message> allByNotification_id = messageRepository.findAllByNotification_id(id);
        return ResponseEntity.ok(allByNotification_id);
    }

    @Override
    @PostMapping
    public HttpEntity<?> addMessage(@RequestBody MessageDto messageDto) {
        Apiresponse apiresponse = messageService.addMessage(messageDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @PutMapping("/{id}")
    public HttpEntity<?> editMessage(@PathVariable Integer id, @RequestBody MessageDto messageDto) {
        Apiresponse apiresponse = messageService.editMessage(id, messageDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteMessage(@PathVariable Integer id) {
        Apiresponse apiresponse = messageService.deleteMessage(id);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @GetMapping("/one/{id}")
    public HttpEntity<?> getOneMessage(@PathVariable Integer id) {
        Optional<Message> byId = messageRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.ok(byId);
    }
}
