package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class StakingPool extends AbsEntity {
    @Column(nullable = false)
    private String uzAboutPool; //ushbu page haqida ma'lumot

    @Column(nullable = false)
    private String enAboutPool; //ushbu page haqida ma'lumot

    @Column(nullable = false)
    private String ruAboutPool; //ushbu page haqida ma'lumot

    @OneToMany
    private List<Pools> pools;

    @Column(nullable = false, length = 9000000)
    private String studyForPermit; //ushbu hovuzga invistitsiya kiritishi uchun o'qishga ma'lumot
}
