package ul.it.universalserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ul.it.universalserver.entity.WithDrawalAddress;
import ul.it.universalserver.logic.Control;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.WithdrawalDto;
import ul.it.universalserver.repository.WithdrawalAddressRepository;
import ul.it.universalserver.service.WithdrawalService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/withdrawal")
@RequiredArgsConstructor
public class WithdrawalController extends Control {
    private final WithdrawalService withdrawalService;
    private final WithdrawalAddressRepository withdrawalAddressRepository;

    @Override
    @PostMapping
    public HttpEntity<?> addWithdrawal(@RequestBody WithdrawalDto withdrawalDto) {
        Apiresponse apiresponse = withdrawalService.addWithdrawal(withdrawalDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @GetMapping("/{id}")
    public HttpEntity<?> getWithdrawalByUserId(@PathVariable UUID id) {
        List<WithDrawalAddress> allByUser_id = withdrawalAddressRepository.findAllByUser_Id(id);
        return ResponseEntity.ok(allByUser_id);
    }

    @Override
    @GetMapping
    public HttpEntity<?> getAllWih() {
        List<WithDrawalAddress> all = withdrawalAddressRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @Override
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteWithdrawal(@PathVariable UUID id) {
        Apiresponse apiresponse = withdrawalService.deleteWithdrawal(id);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }
}
