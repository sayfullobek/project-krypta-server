package ul.it.universalserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ul.it.universalserver.entity.User;
import ul.it.universalserver.entity.VIPS;
import ul.it.universalserver.logic.ServiceAbs;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.VipInUserDto;
import ul.it.universalserver.payload.VipsDto;
import ul.it.universalserver.repository.UserRepository;
import ul.it.universalserver.repository.VipsRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VipsServise extends ServiceAbs {
    private final VipsRepository vipsRepository;
    private final UserRepository userRepository;

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
                .alfaRobotsAvailablePerDay(vipsDto.getAlfaRobotsAvailablePerDay())
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
        vips.setShareRatio(vipsDto.getShareRatio());
        vipsRepository.save(vips);
        return new Apiresponse(vips.getName() + " nomli vips " + vipsDto.getName() + " ga Taxrirlandi", true);
    }

    public Apiresponse editVipPhoto(UUID id, VipsDto vipsDto) {
        VIPS vips = vipsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getVips"));
        vips.setPhotoId(vipsDto.getPhotoId());
        vipsRepository.save(vips);
        return new Apiresponse("Successfull upload photo", true);
    }

    @Override
    public Apiresponse deleteVips(UUID id) {
        VIPS vips = vipsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getVIPS"));
        vipsRepository.delete(vips);
        return new Apiresponse(vips.getName() + " nomli vips o'chirildi", true);
    }

    public Apiresponse updateVipInUser(UUID id, UUID vipId) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            Optional<VIPS> byId1 = vipsRepository.findById(vipId);
            if (byId1.isPresent()) {
                User user = byId.get();
                VIPS vips = byId1.get();
                user.setVips(vips);
                userRepository.save(user);
                return new Apiresponse("Successfully updated vip", true);
            }
        }
        return new Apiresponse("This is not found", false);
    }
}
