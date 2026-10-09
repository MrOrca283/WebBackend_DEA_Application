package ru.rutmiit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.rutmiit.models.entities.Role;
import ru.rutmiit.models.entities.User;
import ru.rutmiit.models.enums.UserRoles;
import ru.rutmiit.repositories.UserRepository;
import ru.rutmiit.repositories.UserRoleRepository;

import java.util.List;

/**
 * Инициализация начальных данных при запуске приложения.
 */
@Slf4j
@Component
public class Init implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final String defaultPassword;

    public Init(UserRepository userRepository, 
                UserRoleRepository userRoleRepository, 
                PasswordEncoder passwordEncoder, 
                @Value("${app.default.password}") String defaultPassword) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultPassword = defaultPassword;
        log.info("Init компонент инициализирован");
    }

    @Override
    public void run(String... args) {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            userRoleRepository.saveAll(List.of(
                new Role(UserRoles.ADMIN),
                new Role(UserRoles.MEDIC),
                new Role(UserRoles.POLICEMAN),
                new Role(UserRoles.USER)
            ));
        } else {
            log.debug("Роли уже существуют, пропуск инициализации");
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            log.info("Создание пользователей по умолчанию...");
            initAdmin();
            initPoliceman();
            initMedic();
            initNormalUser();
            log.info("Пользователи по умолчанию созданы");
        } else {
            log.debug("Пользователи уже существуют, пропуск инициализации");
        }
    }

    private void initAdmin() {
        var adminRole = userRoleRepository
                .findRoleByName(UserRoles.ADMIN)
                .orElseThrow();

        var adminUser = new User(
            "MainAdminych",
            passwordEncoder.encode("1234"),
            "admin@example.com", 
            "Админыч Дамир Админов",
                "5432978056"
        );
        adminUser.setRoles(List.of(adminRole));
        userRepository.save(adminUser);
        log.info("Создан администратор: admin");
    }

    private void initPoliceman() {
        var policemanRole = userRoleRepository
                .findRoleByName(UserRoles.POLICEMAN)
                .orElseThrow();

        var policemanUser = new User(
            "LeitIvanAkula",
            passwordEncoder.encode("1234"),
            "coolMiliciai@example.com",
            "Акулов Иван Петрович",
                "7777000000"
        );
        policemanUser.setRoles(List.of(policemanRole));
        userRepository.save(policemanUser);
    }

    private void initMedic() {
        var medicRole = userRoleRepository
                .findRoleByName(UserRoles.MEDIC)
                .orElseThrow();

        var medicUser = new User(
                "HarisKasatkinHealAnyone",
                passwordEncoder.encode("1234"),
                "ProStoHealer@example.com",
                "Касаткин Харис Евгеньевич",
                "2285098678"
        );
        medicUser.setRoles(List.of(medicRole));
        userRepository.save(medicUser);
    }

    private void initNormalUser() {
        var userRole = userRoleRepository
                .findRoleByName(UserRoles.USER)
                .orElseThrow();

        var normalUser = new User(
            "userBroser",
            passwordEncoder.encode("1234"),
            "user@example.com", 
            "User Userovich",
                "4576897765"
        );
        normalUser.setRoles(List.of(userRole));
        userRepository.save(normalUser);
        log.info("Создан обычный пользователь: user");
    }
}
