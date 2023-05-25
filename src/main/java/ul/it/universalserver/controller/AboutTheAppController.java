package ul.it.universalserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ul.it.universalserver.entity.AboutTheApp;
import ul.it.universalserver.logic.Control;
import ul.it.universalserver.payload.AboutTheAppDto;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.repository.AboutTheAppRepository;
import ul.it.universalserver.service.AboutTheAppService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/about-the-app")
@RequiredArgsConstructor
public class AboutTheAppController extends Control {
    private final AboutTheAppService aboutTheAppService;
    private final AboutTheAppRepository aboutTheAppRepository;

    @Override
    @GetMapping
    public HttpEntity<?> getAboutApp() {
        List<AboutTheApp> all = aboutTheAppRepository.findAll();
        AboutTheApp aboutTheApp = all.get(0);
        return ResponseEntity.ok(aboutTheApp);
    }

    @Override
    @PutMapping("/about-the-app-update/{id}")
    public HttpEntity<?> updateAboutApp(@PathVariable UUID id, @RequestBody AboutTheAppDto aboutTheAppDto) {
        Apiresponse apiresponse = aboutTheAppService.updateAboutApp(UUID.fromString("727e1c66-3a6c-429e-8332-e12b9a1de422"), aboutTheAppDto);
        return ResponseEntity.status(apiresponse.isSuccess() ? 200 : 409).body(apiresponse);
    }
}
