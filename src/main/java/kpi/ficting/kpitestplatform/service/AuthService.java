package kpi.ficting.kpitestplatform.service;

import kpi.ficting.kpitestplatform.dto.LoginDto;
import kpi.ficting.kpitestplatform.dto.RegisterDto;

public interface AuthService {

  String register(RegisterDto registerDto);

  String login(LoginDto loginDto);
}
