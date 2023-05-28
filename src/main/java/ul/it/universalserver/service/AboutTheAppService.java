package ul.it.universalserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ul.it.universalserver.entity.AboutTheApp;
import ul.it.universalserver.logic.ServiceAbs;
import ul.it.universalserver.payload.AboutTheAppDto;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.repository.AboutTheAppRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AboutTheAppService extends ServiceAbs {
    private final AboutTheAppRepository aboutTheAppRepository;

    @Override
    public Apiresponse updateAboutApp(UUID id, AboutTheAppDto aboutTheAppDto) {
        List<AboutTheApp> all = aboutTheAppRepository.findAll();
        AboutTheApp getApp = all.get(0);
        switch (aboutTheAppDto.getStatus()) {
            case "name":
                getApp.setUzAbout(aboutTheAppDto.getUzAbout());
                getApp.setEnAbout(aboutTheAppDto.getEnAbout());
                getApp.setRuAbout(aboutTheAppDto.getRuAbout());
                break;
            case "appLaunched":
                getApp.setDayAppLaunched(aboutTheAppDto.getDayAppLaunched());
                break;
            case "moneyApp":
                getApp.setHowMuchMoneyApp(aboutTheAppDto.getHowMuchMoneyApp());
                break;
            case "link":
                getApp.setAppContactLink(aboutTheAppDto.getAppContactLink());
                break;
        }
        aboutTheAppRepository.save(getApp);
        return new Apiresponse("muvaffaqiyatli saqlandi", true);
    }
}
