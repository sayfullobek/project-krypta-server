package ul.it.universalserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ul.it.universalserver.entity.User;
import ul.it.universalserver.payload.*;
import ul.it.universalserver.repository.UserRepository;
import ul.it.universalserver.service.AuthService;

import java.util.List;
import java.util.Optional;
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

    @PutMapping("/password-change/{id}")
    public HttpEntity<?> passwordChange(@PathVariable UUID id, @RequestBody ChangePassword changePassword) {
        Apiresponse apiresponse = authService.changePassword(id, changePassword);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
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

    @GetMapping("/get-me/{id}")
    public HttpEntity<?> getMe(@PathVariable UUID id) {
        Optional<User> byId = authRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.ok(new Apiresponse("kirish mumkin emas", false));
    }

    @PutMapping("/me-money-send/{id}")
    public HttpEntity<?> updateMyMoney(@PathVariable UUID id, @RequestBody MyMoneyDto myMoneyDto) {
        Apiresponse apiresponse = authService.updateMyMoney(id, myMoneyDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }
}
