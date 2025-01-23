package kpi.ficting.kpitestplatform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDto {

  @NotBlank(message = "Email is mandatory")
  @Email(message = "Email should be valid")
  private String email;

  @NotBlank(message = "First name is mandatory")
  private String firstName;

  @NotBlank(message = "Last name is mandatory")
  private String lastName;

  @NotBlank(message = "Password is mandatory")
  private String password;
}
