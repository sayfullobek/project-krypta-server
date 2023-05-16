package ul.it.universalserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ul.it.universalserver.entity.InvestmentDetails;
import ul.it.universalserver.entity.Pools;
import ul.it.universalserver.entity.enums.StackingCoin;
import ul.it.universalserver.logic.ServiceAbs;
import ul.it.universalserver.payload.Apiresponse;
import ul.it.universalserver.payload.InvestmentDetailsDto;
import ul.it.universalserver.payload.PoolsDto;
import ul.it.universalserver.repository.InvestmentRepository;
import ul.it.universalserver.repository.PoolRepository;
import ul.it.universalserver.repository.VipsRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PoolService extends ServiceAbs {
    private final PoolRepository poolRepository;
    private final InvestmentRepository investmentRepository;
    private final VipsRepository vipsRepository;

    @Override
    public Apiresponse addPools(PoolsDto poolDto) {
        if (!poolRepository.existsPoolsByUzNameEqualsIgnoreCaseAndEnNameEqualsIgnoreCaseAndRuNameEqualsIgnoreCase(poolDto.getUzName(), poolDto.getEnName(), poolDto.getRuName())) {
            Pools build = Pools.builder()
                    .photoId(poolDto.getPhotoId())
                    .annualizedInterest(poolDto.getAnnualizedInterest())
                    .stakingMinimum(poolDto.getStakingMinimum())
                    .build();
            build.setUzName(poolDto.getUzName());
            build.setEnName(poolDto.getEnName());
            build.setRuName(poolDto.getRuName());
            poolRepository.save(
                    build
            );
            return new Apiresponse(poolDto.getUzName() + " nomli hovuz saqlandi", true);
        }
        return new Apiresponse("bunday nomli hovuz mavjud qayta urinib ko'ring", false);
    }

    @Override
    public Apiresponse editPools(Integer id, PoolsDto coinDto) {
        Pools getPools = poolRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getPools"));
        if (!poolRepository.existsPoolsByUzNameEqualsIgnoreCaseAndEnNameEqualsIgnoreCaseAndRuNameEqualsIgnoreCaseAndIdNot(coinDto.getUzName(), coinDto.getEnName(), coinDto.getRuName(), id)) {
            getPools.setUzName(getPools.getUzName());
            getPools.setEnName(getPools.getEnName());
            getPools.setRuName(getPools.getRuName());
            getPools.setAnnualizedInterest(getPools.getAnnualizedInterest());
            getPools.setStakingMinimum(getPools.getStakingMinimum());
            poolRepository.save(getPools);
            return new Apiresponse("ushbu hovuz taxrirlandi", true);
        }
        return new Apiresponse("ushbu hovuz avvaldan mavjud", false);
    }

    @Override
    public Apiresponse deletePools(Integer id) {
        Pools getPools = poolRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getPools"));
        investmentRepository.deleteAllByPools_Id(getPools.getId());
        poolRepository.delete(getPools);
        return new Apiresponse("ushbu hovuz o'chirildi", true);
    }

    @Override
    public Apiresponse editPhotoPools(Integer id, PoolsDto coinDto) {
        Pools getPools = poolRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getPools"));
        getPools.setPhotoId(getPools.getPhotoId());
        poolRepository.save(getPools);
        return new Apiresponse("ushbu hovuzning rasmi almashtirildi", true);
    }

    @Override
    public Apiresponse addInvestment(InvestmentDetailsDto invDto) {
        investmentRepository.save(
                InvestmentDetails.builder()
                        .monthly(invDto.getMonthly())
                        .stakingPool(StackingCoin.USDT)
                        .howManyDays(invDto.getHowManyDays())
                        .description(invDto.getDescription())
                        .financialAmount(invDto.getFinancialAmount())
                        .vips(invDto.getVipsId().equals("HAMMA") ? vipsRepository.findAll() : Collections.singletonList(vipsRepository.findById(UUID.fromString(invDto.getVipsId())).orElseThrow(() -> new ResourceNotFoundException("getVips"))))
                        .active(true)
                        .pools(poolRepository.findById(invDto.getPools()).orElseThrow(() -> new ResourceNotFoundException("getPools")))
                        .build()
        );
        return new Apiresponse("muvaffaqtiyarli saqlandi", true);
    }

    @Override
    public Apiresponse editInvestment(UUID id, InvestmentDetailsDto invDto) {
        InvestmentDetails getInv = investmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getInv"));
        getInv.setMonthly(invDto.getMonthly());
        getInv.setHowManyDays(invDto.getHowManyDays());
        getInv.setDescription(invDto.getDescription());
        getInv.setFinancialAmount(invDto.getFinancialAmount());
        getInv.setActive(invDto.isActive());
        investmentRepository.save(getInv);
        return new Apiresponse("muvaffaqaiyatli taxrirlandi", true);
    }

    @Override
    public Apiresponse deleteInvestment(UUID id) {
        Optional<InvestmentDetails> byId = investmentRepository.findById(id);
        byId.ifPresent(investmentRepository::delete);
        return new Apiresponse("muvaffaqiyatli o'chirildi", true);
    }
}
