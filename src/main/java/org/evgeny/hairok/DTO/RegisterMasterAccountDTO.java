package org.evgeny.hairok.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.evgeny.hairok.Validation.Annotation.EmailMatcher;
import org.evgeny.hairok.Validation.Annotation.PasswordMatcherMasterAccount;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PasswordMatcherMasterAccount
@EmailMatcher
public class RegisterMasterAccountDTO {

    @NotBlank
    @Size(min = 3, max = 200, message = "Почта короткая или слишком длинная")
    String email;

    @NotBlank
    @Size(min = 6, message = "Пароль должен быть от 6 символов")
    String password;

    @NotBlank
    String passwordRep;
}
