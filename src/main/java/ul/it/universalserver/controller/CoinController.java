package ul.it.universalserver.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ul.it.universalserver.logic.Control;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.CoinDto;
import ul.it.universalserver.repository.CoinRepository;
import ul.it.universalserver.service.CoinService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/coin")
@RequiredArgsConstructor
public class CoinController extends Control {
    private final CoinService coinService;
    private final CoinRepository coinRepository;

    @Override
    @GetMapping
    public HttpEntity<?> getCoins() {
        return ResponseEntity.ok(coinRepository.findAll());
    }

    @Override
    @PostMapping
    public HttpEntity<?> addCoin(@RequestBody CoinDto coinDto) {
        Apiresponse apiresponse = coinService.addCoin(coinDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @PutMapping("/{id}")
    public HttpEntity<?> editCoin(@PathVariable UUID id, @RequestBody CoinDto coinDto) {
        Apiresponse apiresponse = coinService.editCoin(id, coinDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCoin(@PathVariable UUID id) {
        Apiresponse apiresponse = coinService.deleteCoin(id);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }
}
