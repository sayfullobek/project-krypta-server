package ul.it.universalserver.repository.rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import ul.it.universalserver.entity.Help;
import ul.it.universalserver.projection.CustomHelp;


@CrossOrigin
@RepositoryRestResource(path = "help", collectionResourceRel = "list", excerptProjection = CustomHelp.class)
public interface HelpRepository extends JpaRepository<Help, Integer> {
}
