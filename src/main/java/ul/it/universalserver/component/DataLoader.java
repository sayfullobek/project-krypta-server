package ul.it.universalserver.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import ul.it.universalserver.entity.*;
import ul.it.universalserver.entity.enums.Gander;
import ul.it.universalserver.entity.enums.RoleName;
import ul.it.universalserver.repository.*;

import java.util.Collections;
import java.util.UUID;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AttachmentRepository attachmentRepository;

    private final StakingPoolsRepository stakingPoolsRepository;
    private final AppSettingsRepository appSettingsRepository;
    private final WalletRepository walletRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String initMode;

    @Override
    public void run(String... args) {
        if (initMode.equals("create-drop") || initMode.equals("create")) {
            for (RoleName value : RoleName.values()) {
                roleRepository.save(
                        new Role(
                                value
                        )
                );
            }
            StakingPool stakingPool = new StakingPool(
                    "salom ushbu hovuzga hush kelibsiz",
                    "salom ushbu hovuzga hush kelibsiz",
                    "salom ushbu hovuzga hush kelibsiz",
                    null,
                    "tvarbachcha"
            );
            stakingPool.setId(UUID.fromString("45897e1e-6b9a-4850-927c-7ffcf2aaa22c"));
            stakingPoolsRepository.save(
                    stakingPool
            );
            appSettingsRepository.save(new AppSettings(
                    1, 10
            ));
            Wallet wallet = walletRepository.save(new Wallet(0, 0, 0, 0));
            User save = userRepository.save(
                    new User(
                            "Sayfullo",
                            "To'xtayev",
                            "sayfullo@gmail.com",
                            "+998990763246",
                            passwordEncoder.encode("admin123"),
                            Gander.MALE,
                            "?qozi",
                            true,
                            Collections.singletonList(roleRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("getRole"))),
                            "phone",
                            wallet
                    )
            );
        }
    }
}