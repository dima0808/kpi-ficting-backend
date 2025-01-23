package kpi.ficting.kpitestplatform.domain;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import kpi.ficting.kpitestplatform.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

  private UUID userReference;
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());
    return new UserDetailsImpl(
        user.getUserReference(),
        user.getEmail(),
        user.getPassword(),
        authorities);
  }

  @Override
  public String getUsername() {
    return email;
  }
}
