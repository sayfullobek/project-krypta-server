package ul.it.universalserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ul.it.universalserver.entity.VIPS;
import ul.it.universalserver.logic.ServiceAbs;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.VipsDto;
import ul.it.universalserver.repository.VipsRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VipsServise extends ServiceAbs {
    private final VipsRepository vipsRepository;

    @Override
    public Apiresponse addVips(VipsDto vipsDto) {
        boolean existName = vipsRepository.existsVIPSByNameEqualsIgnoreCase(vipsDto.getName());
        if (existName) return new Apiresponse("Bunday vip avvaldan mavjud", false);
        vipsRepository.save(VIPS.builder()
                .name(vipsDto.getName())
                .photoId(vipsDto.getPhotoId())
                .minQuantifyAmount(vipsDto.getMinQuantifyAmount())
                .maxQuantifyAmount(vipsDto.getMaxQuantifyAmount())
                .shareRatio(vipsDto.getShareRatio())
                .effectiveEmount(vipsDto.getEffectiveEmount())
                .directlyPromoteMembers(vipsDto.getDirectlyPromoteMembers())
                .secondThridGenerationMembers(vipsDto.getSecondThridGenerationMembers())
                .profits(vipsDto.getProfits())
                .metaGORobotsAvailablePerDay(vipsDto.getMetaGORobotsAvailablePerDay())
                .teamAward(vipsDto.getTeamAward())
                .active(true)
                .build()
        );
        return new Apiresponse(vipsDto.getName() + "nomli VIp saqlandi", true);
    }

    @Override
    public Apiresponse editVips(UUID id, VipsDto vipsDto) {
        boolean existName = vipsRepository.existsVIPSByNameEqualsIgnoreCaseAndIdNot(vipsDto.getName(), id);
        if (existName) return new Apiresponse("Bunday vip avvaldan mavjud", false);
        VIPS vips = vipsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getVIPS"));
        vips.setName(vipsDto.getName());
        vipsRepository.save(vips);
        return new Apiresponse(vips.getName() + " nomli vips " + vipsDto.getName() + " ga Taxrirlandi", true);
    }

    @Override
    public Apiresponse deleteVips(UUID id) {
        VIPS vips = vipsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getVIPS"));
        vipsRepository.delete(vips);
        return new Apiresponse(vips.getName() + " nomli vips o'chirildi", true);
    }
}