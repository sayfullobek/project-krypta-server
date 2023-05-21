package ul.it.universalserver.entity;

import lombok.*;
import ul.it.universalserver.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ArchivePay extends AbsEntity {
    @Column(nullable = false)
    private UUID photoId;

    @ManyToMany
    private List<User> user;

    private boolean pulTushdimi;
}
