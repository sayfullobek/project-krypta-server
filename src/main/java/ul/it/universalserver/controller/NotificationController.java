package ul.it.universalserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ul.it.universalserver.logic.Control;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.NotificationDto;
import ul.it.universalserver.repository.NotificationRepository;
import ul.it.universalserver.service.NotificationService;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationController extends Control {
    private final NotificationRepository notificationRepository;
    private final NotificationService notificationService;

    @Override
    @GetMapping
    public HttpEntity<?> getNotification() {
        return ResponseEntity.ok(notificationRepository.findAll());
    }

    @Override
    @PostMapping
    public HttpEntity<?> addNotification(@RequestBody NotificationDto notificationDto) {
        Apiresponse apiresponse = notificationService.addNotification(notificationDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @PutMapping("/{id}")
    public HttpEntity<?> editNotification(@PathVariable Integer id, @RequestBody NotificationDto notificationDto) {
        Apiresponse apiresponse = notificationService.editNotification(id, notificationDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteNotification(@PathVariable Integer id) {
        Apiresponse apiresponse = notificationService.deleteNotification(id);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @GetMapping("/{id}")
    public HttpEntity<?> getOneNotification(@PathVariable Integer id) {
        return ResponseEntity.ok(notificationRepository.findById(id));
    }

    @Override
    @PutMapping("/photo/{id}")
    public HttpEntity<?> editNotificationPhoto(@PathVariable Integer id, @RequestBody NotificationDto notificationDto) {
        Apiresponse apiresponse = notificationService.editPhotoNotification(id, notificationDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }
}
