package kpi.ficting.kpitestplatform.web;

import jakarta.validation.Valid;
import kpi.ficting.kpitestplatform.dto.LoginDto;
import kpi.ficting.kpitestplatform.dto.RegisterDto;
import kpi.ficting.kpitestplatform.service.AuthService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<Map<String, String>> register(@RequestBody @Valid RegisterDto registerDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(Map.of("token", authService.register(registerDto)));
  }

  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginDto loginDto) {
    return ResponseEntity.ok(Map.of("token", authService.login(loginDto)));
  }
}
