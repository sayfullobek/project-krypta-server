package ul.it.universalserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ul.it.universalserver.logic.Control;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.InvestmentUserDto;
import ul.it.universalserver.service.InvestmentUserService;

@RestController
@RequestMapping("/api/v1/investment-user")
@RequiredArgsConstructor
public class InvestmentUserController extends Control {
    private final InvestmentUserService investmentUserService;

    @Override
    @PostMapping
    public HttpEntity<?> addInvestmentUser(@RequestBody InvestmentUserDto investmentUserDto) {
        Apiresponse apiresponse = investmentUserService.addInvestmentUser(investmentUserDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    public HttpEntity<?> getAllInvestmentUser() {
        return super.getAllInvestmentUser();
    }

    @Override
    public HttpEntity<?> getOneInvestByUser() {
        return super.getOneInvestByUser();
    }
}
