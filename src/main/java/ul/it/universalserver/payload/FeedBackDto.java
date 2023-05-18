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
public class FeedBackDto {
    private UUID id;
    private String feedbackName;
    private String information; //foydalanuvhining ma'lumoi yoki shikoyati
    private UUID userId;
}
