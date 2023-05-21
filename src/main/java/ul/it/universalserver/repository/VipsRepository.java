package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ul.it.universalserver.entity.VIPS;

import java.util.UUID;
@CrossOrigin
public interface VipsRepository extends JpaRepository<VIPS, UUID> {
    boolean existsVIPSByNameEqualsIgnoreCase(String name);

    boolean existsVIPSByNameEqualsIgnoreCaseAndIdNot(String name, UUID id);

}