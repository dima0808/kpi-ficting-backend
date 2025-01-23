package kpi.ficting.kpitestplatform.config;

import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
import kpi.ficting.kpitestplatform.repository.RoleRepository;
import kpi.ficting.kpitestplatform.repository.UserRepository;
import kpi.ficting.kpitestplatform.repository.entity.Role;
import kpi.ficting.kpitestplatform.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${kpi-test-platform.jwt.admin-email}")
  private String adminEmail;

  @Value("${kpi-test-platform.jwt.admin-password}")
  private String adminPassword;

  @Override
  @Transactional
  public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
    Role userRole = createRoleIfNotFound("ROLE_USER");
    Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
    if (!userRepository.existsByEmail(adminEmail)) {
      User user = User.builder()
          .email(adminEmail)
          .password(passwordEncoder.encode(adminPassword))
          .balance(1_000_000)
          .isActive(true)
          .roles(Set.of(userRole, adminRole))
          .build();
      userRepository.save(user);
    }
  }

  @Transactional
  Role createRoleIfNotFound(String name) {
    return roleRepository.findByName(name).orElseGet(() -> {
      Role role = Role.builder().name(name).build();
      return roleRepository.save(role);
    });
  }
}
