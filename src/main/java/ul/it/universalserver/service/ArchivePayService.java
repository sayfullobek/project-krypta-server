package ul.it.universalserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ul.it.universalserver.entity.ArchivePay;
import ul.it.universalserver.logic.ServiceAbs;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.ArchivePayDto;
import ul.it.universalserver.repository.ArchivePayRepository;
import ul.it.universalserver.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ArchivePayService extends ServiceAbs {
    private final ArchivePayRepository archivePayRepository;
    private final UserRepository userRepository;

    @Override
    public Apiresponse sendArchiveDto(ArchivePayDto archivePayDto) {
        ArchivePay getArchivePay = ArchivePay.builder()
                .photoId(archivePayDto.getPhotoId())
                .user(Collections.singletonList(userRepository.findById(archivePayDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("getArchivePay"))))
                .pulTushdimi(false)
                .build();
        archivePayRepository.save(getArchivePay);
        return new Apiresponse("saved successfully", true);
    }
}
