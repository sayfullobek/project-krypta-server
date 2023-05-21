package ul.it.universalserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ul.it.universalserver.entity.ArchivePay;
import ul.it.universalserver.logic.Control;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.ArchivePayDto;
import ul.it.universalserver.repository.ArchivePayRepository;
import ul.it.universalserver.service.ArchivePayService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/archive-pay")
@RequiredArgsConstructor
public class ArchivePayController extends Control {
    private final ArchivePayRepository archivePayRepository;
    private final ArchivePayService archivePayService;

    @Override
    @GetMapping
    public HttpEntity<?> getArchivePay() {
        List<ArchivePay> all = archivePayRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @Override
    @PostMapping
    public HttpEntity<?> addArchivePay(@RequestBody ArchivePayDto archivePayDto) {
        Apiresponse apiresponse = archivePayService.sendArchiveDto(archivePayDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @GetMapping("/{id}")
    public HttpEntity<?> getOneArchivePay(@PathVariable UUID id) {
        ArchivePay archivePay = archivePayRepository.findById(id).get();
        return ResponseEntity.ok(archivePay);
    }

    @Override
    @GetMapping("/user-by/{id}")
    public HttpEntity<?> getOneArchivePayByUserId(@PathVariable UUID id) {
        List<ArchivePay> allByUser_id = archivePayRepository.findAllByUser_id(id);
        return ResponseEntity.ok(allByUser_id);
    }
}
