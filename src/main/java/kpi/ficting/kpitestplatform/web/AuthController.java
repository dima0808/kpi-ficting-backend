package kpi.ficting.kpitestplatform.web;

import kpi.ficting.kpitestplatform.dto.LoginDto;
import kpi.ficting.kpitestplatform.service.AuthService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {


  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> login(@RequestBody LoginDto loginDto) {
    return ResponseEntity.ok(Map.of("token", authService.login(loginDto)));
  }
}
