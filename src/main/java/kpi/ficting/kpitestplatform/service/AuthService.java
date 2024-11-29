package kpi.ficting.kpitestplatform.service;

import kpi.ficting.kpitestplatform.dto.LoginDto;

public interface AuthService {

  String login(LoginDto loginDto);
}
