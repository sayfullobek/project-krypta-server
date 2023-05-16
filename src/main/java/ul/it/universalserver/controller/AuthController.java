package ul.it.universalserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ul.it.universalserver.entity.User;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.ReqLogin;
import ul.it.universalserver.payload.ReqRegister;
import ul.it.universalserver.payload.ResRegister;
import ul.it.universalserver.repository.UserRepository;
import ul.it.universalserver.service.AuthService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final
    AuthService authService;

    private final
    UserRepository authRepository;


    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody ReqLogin request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody ReqRegister reqRegister) {
        ResRegister register = authService.register(reqRegister);
        return ResponseEntity.status(register.getApiresponse().isSuccess() ? 200 : 409).body(register);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> changeAbout(@PathVariable UUID id, @RequestBody ReqLogin reqLogin) {
        Apiresponse change = authService.change(id, reqLogin);
        return ResponseEntity.status(change.isSuccess() ? 200 : 409).body(change);
    }

    @GetMapping
    public HttpEntity<?> getEmployeList() {
        List<User> all = authRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @PutMapping("/photoUpload/{id}")
    public HttpEntity<?> uploadPhoto(@PathVariable UUID id, @RequestBody ReqRegister reqRegister) {
        return ResponseEntity.ok(authService.uploadPhoto(id, reqRegister));
    }

    @PutMapping("/update/{id}")
    public HttpEntity<?> updateAbout(@PathVariable UUID id, @RequestBody ReqRegister reqRegister) {
        return ResponseEntity.ok(authService.updateAbout(id, reqRegister));
    }
}
