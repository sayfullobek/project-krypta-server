package ul.it.universalserver.projection;

import org.springframework.data.rest.core.config.Projection;
import ul.it.universalserver.entity.Help;
import ul.it.universalserver.entity.enums.HelpName;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Projection(types = Help.class, name = "customHelp")
public interface CustomHelp {
    Integer getId();

    String getUzName();

    String getEnName();

    String getRuName();

    HelpName getHelpName();

    String getInfo();
}
