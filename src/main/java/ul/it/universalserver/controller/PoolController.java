package ul.it.universalserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ul.it.universalserver.entity.InvestmentDetails;
import ul.it.universalserver.entity.Pools;
import ul.it.universalserver.logic.Control;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.InvestmentDetailsDto;
import ul.it.universalserver.payload.PoolsDto;
import ul.it.universalserver.repository.InvestmentRepository;
import ul.it.universalserver.repository.PoolRepository;
import ul.it.universalserver.service.PoolService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pools")
@RequiredArgsConstructor
public class PoolController extends Control {
    private final PoolService poolService;
    private final PoolRepository poolRepository;
    private final InvestmentRepository investmentRepository;

    @Override
    @GetMapping
    public HttpEntity<?> getPools() {
        return ResponseEntity.ok(poolRepository.findAll());
    }

    @Override
    @PostMapping
    public HttpEntity<?> addPools(@RequestBody PoolsDto poolsDto) {
        Apiresponse apiresponse = poolService.addPools(poolsDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @PutMapping("/{id}")
    public HttpEntity<?> editPools(@PathVariable Integer id, @RequestBody PoolsDto poolsDto) {
        Apiresponse apiresponse = poolService.editPhotoPools(id, poolsDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePools(@PathVariable Integer id) {
        Apiresponse apiresponse = poolService.deletePools(id);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @GetMapping("/{id}")
    public HttpEntity<?> getOnePools(@PathVariable Integer id) {
        Pools getPools = poolRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getPools"));
        return ResponseEntity.ok(getPools);
    }

    @Override
    @PutMapping("/photo/{id}")
    public HttpEntity<?> editPoolsPhoto(@PathVariable Integer id, @RequestBody PoolsDto poolsDto) {
        Apiresponse apiresponse = poolService.editPhotoPools(id, poolsDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }


    @Override
    @GetMapping("/inv/pool/{id}")
    public HttpEntity<?> getInvestment(@PathVariable Integer id) {
        return ResponseEntity.ok(investmentRepository.findAllByPools_Id(id));
    }

    @Override
    @PostMapping("/inv")
    public HttpEntity<?> addInvestment(@RequestBody InvestmentDetailsDto investmentDetailsDto) {
        Apiresponse apiresponse = poolService.addInvestment(investmentDetailsDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @PutMapping("/inv/{id}")
    public HttpEntity<?> editInvestment(@PathVariable UUID id, @RequestBody InvestmentDetailsDto investmentDetailsDto) {
        Apiresponse apiresponse = poolService.editInvestment(id, investmentDetailsDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @DeleteMapping("/inv/{id}")
    public HttpEntity<?> deleteInvestment(@PathVariable UUID id) {
        Apiresponse apiresponse = poolService.deleteInvestment(id);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @GetMapping("/inv/{id}")
    public HttpEntity<?> getOneInvestment(@PathVariable UUID id) {
        InvestmentDetails getInv = investmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getInv"));
        return ResponseEntity.ok(getInv);
    }
}
