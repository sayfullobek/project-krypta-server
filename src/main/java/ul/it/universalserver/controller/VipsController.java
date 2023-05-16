package ul.it.universalserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ul.it.universalserver.logic.Control;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.VipsDto;
import ul.it.universalserver.repository.VipsRepository;
import ul.it.universalserver.service.VipsServise;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/vips")
@RequiredArgsConstructor
public class VipsController extends Control {

    private final VipsServise vipsServise;
    private final VipsRepository vipsRepository;

    @Override
    @GetMapping
    public HttpEntity<?> getVips() {
        return ResponseEntity.ok(vipsRepository.findAll());
    }

    @Override
    @PostMapping
    public HttpEntity<?> addVips(@RequestBody VipsDto vipsDto) {
        Apiresponse apiresponse = vipsServise.addVips(vipsDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @PutMapping("/{id}")
    public HttpEntity<?> editVips(@PathVariable UUID id, @RequestBody VipsDto vipsDto) {
        Apiresponse apiresponse = vipsServise.editVips(id, vipsDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteVips(@PathVariable UUID id) {
        Apiresponse apiresponse = vipsServise.deleteVips(id);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }

    @Override
    @GetMapping("/{id}")
    public HttpEntity<?> getOneVips(@PathVariable UUID id) {
        return ResponseEntity.ok(vipsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getVips")));
    }
}