package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.enums.FeedbackName;
import ul.it.universalserver.entity.template.AbsEntity;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Feedback extends AbsEntity {
    @Enumerated(EnumType.STRING)
    private FeedbackName feedbackName;

    @Column(nullable = false)
    private String information; //foydalanuvhining ma'lumoi yoki shikoyati
}
