package ul.it.universalserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ul.it.universalserver.entity.History;
import ul.it.universalserver.entity.User;
import ul.it.universalserver.entity.WithdrawalRequest;
import ul.it.universalserver.payload.*;
import ul.it.universalserver.repository.UserRepository;
import ul.it.universalserver.repository.WithdrawalRequestRepository;
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
    private final WithdrawalRequestRepository withdrawalRequestRepository;

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
        return null;
    }

    @PutMapping("/me-money-send/{id}")
    public HttpEntity<?> updateMyMoney(@PathVariable UUID id, @RequestBody MyMoneyDto myMoneyDto) {
        Apiresponse apiresponse = authService.updateMyMoney(id, myMoneyDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @PutMapping("/me-withdrawal-of-money-from-the-account/{id}")
    public HttpEntity<?> withdrawalOfMoneyFromTheAccount(@PathVariable UUID id, @RequestBody MoneyExitDto moneyExitDto) { //pulni xisobidan yechib olish
        Apiresponse apiresponse = authService.meWithdrawalOfMoneyFromTheAccount(id, moneyExitDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @PutMapping("/me-withdrawal-of-money-from-the-account/confirmation/{id}")
    public HttpEntity<?> confirmation(@PathVariable UUID id, @RequestBody MoneyExitDto moneyExitDto) { //pulni otkazilganini tasdiqlash
        Apiresponse apiresponse = authService.confirmationWithReq(id, moneyExitDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }


    @GetMapping("/list-of-funds-to-be-withdrawn")
    public HttpEntity<?> getListWithdrawalExit() {
        List<WithdrawalRequest> all = withdrawalRequestRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/list-of-funds-to-be-withdrawn/{id}")
    public HttpEntity<?> getListWithdrawalExitGetOne(@PathVariable UUID id) {
        Optional<WithdrawalRequest> byId = withdrawalRequestRepository.findById(id);
        if (byId.isPresent()) return ResponseEntity.ok(byId.get());
        return ResponseEntity.ok(null);
    }

    @PutMapping("/a-password-that-cannot-be-forgotten/{id}")
    public HttpEntity<?> cannotForgetPasswordAdd(@PathVariable UUID id, @RequestBody ReqRegister reqRegister) {
        Apiresponse apiresponse = authService.cantForgetPasswordAdd(id, reqRegister);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @GetMapping("/get-me-history/{id}")
    public HttpEntity<?> getMeHistory(@PathVariable UUID id) {
        List<History> histories = authService.GetHistoryByUser(id);
        return ResponseEntity.ok(histories);
    }

    @PutMapping("/profit-by-vip/{id}")
    public HttpEntity<?> updateMoneyFriendsProfit(@PathVariable UUID id, @RequestBody FriendsProfitDto friendsProfitDto) {
        Apiresponse apiresponse = authService.updateMoneyFriendsProfit(id, friendsProfitDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }
}
