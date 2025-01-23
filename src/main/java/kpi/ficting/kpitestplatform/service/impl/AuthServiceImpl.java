package kpi.ficting.kpitestplatform.service.impl;

import java.util.Set;
import kpi.ficting.kpitestplatform.dto.LoginDto;
import kpi.ficting.kpitestplatform.dto.RegisterDto;
import kpi.ficting.kpitestplatform.repository.RoleRepository;
import kpi.ficting.kpitestplatform.repository.UserRepository;
import kpi.ficting.kpitestplatform.repository.entity.Role;
import kpi.ficting.kpitestplatform.repository.entity.User;
import kpi.ficting.kpitestplatform.service.AuthService;
import kpi.ficting.kpitestplatform.service.exception.EmailAlreadyExistsException;
import kpi.ficting.kpitestplatform.service.exception.RoleNotFoundException;
import kpi.ficting.kpitestplatform.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final JwtUtils jwtUtils;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Override
  public String register(RegisterDto registerDto) {
    if (userRepository.existsByEmail(registerDto.getEmail())) {
      throw new EmailAlreadyExistsException(registerDto.getEmail());
    }
    User user = User.builder()
        .email(registerDto.getEmail())
        .firstName(registerDto.getFirstName())
        .lastName(registerDto.getLastName())
        .balance(0)
        .isActive(true)
        .password(passwordEncoder.encode(registerDto.getPassword()))
        .build();
    Role userRole = roleRepository.findByName("ROLE_USER")
        .orElseThrow(() -> new RoleNotFoundException("ROLE_USER"));
    Set<Role> roles = Set.of(userRole);
    user.setRoles(roles);
    userRepository.save(user);
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(registerDto.getEmail(), registerDto.getPassword()));
    return jwtUtils.generateToken(authentication);
  }

  @Override
  public String login(LoginDto loginDto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
    return jwtUtils.generateToken(authentication);
  }
}
