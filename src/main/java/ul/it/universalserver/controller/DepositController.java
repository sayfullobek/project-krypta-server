package ul.it.universalserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ul.it.universalserver.entity.Deposit;
import ul.it.universalserver.logic.Control;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.DepositDto;
import ul.it.universalserver.repository.DepositRepository;
import ul.it.universalserver.service.DepositService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/deposit")
@RequiredArgsConstructor
public class DepositController extends Control {
    private final DepositService depositService;
    private final DepositRepository depositRepository;

    @Override
    @PostMapping
    public HttpEntity<?> addDeposit(@RequestBody DepositDto depositDto) {
        Apiresponse apiresponse = depositService.addDeposit(depositDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @GetMapping
    public HttpEntity<?> getAllDeposit() {
        List<Deposit> all = depositRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @Override
    @GetMapping("/by-user/{id}")
    public HttpEntity<?> getDepositByUser(@PathVariable UUID id) {
        List<Deposit> userByDeposit = depositService.getUserByDeposit(id);
        return ResponseEntity.ok(userByDeposit);
    }
}
