package ul.it.universalserver.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ul.it.universalserver.entity.enums.StackingCoin;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvestmentDetailsDto {
    private UUID id;
    private double monthly; //oyiga qancha foiz bo'lishi
    private StackingCoin stakingPool; //qaysi pul turida investitsiya qilishi
    private Integer howManyDays; //necha kunda ushbu hovuzga invistitsiya kirita olishi
    private String description; //ushbu hovuz haqida ma'lumot
    private String financialAmount; //ushbu hovuzga invistitsiya kiritish uchun qancha mab'la'g kerak
    private String vipsId;
    private boolean active;
    private Integer pools;
}
