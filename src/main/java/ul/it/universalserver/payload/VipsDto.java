package ul.it.universalserver.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VipsDto {
    private UUID id;
    private String name;

    private UUID photoId;

    private double minQuantifyAmount;

    private double maxQuantifyAmount;

    private double shareRatio;

    private Integer alfaRobotsAvailablePerDay;

    private boolean active;
}