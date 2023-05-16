package ul.it.universalserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ul.it.universalserver.entity.VIPS;

import java.util.UUID;

public interface VipsRepository extends JpaRepository<VIPS, UUID> {
    boolean existsVIPSByNameEqualsIgnoreCase(String name);

    boolean existsVIPSByNameEqualsIgnoreCaseAndIdNot(String name, UUID id);

}