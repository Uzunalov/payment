package com.azericard.user.model.request;

import com.azericard.user.validation.constraint.Email;
import com.azericard.user.validation.constraint.Password;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequest {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Password
    private String password;
    @NotBlank
    private String phoneNumber;
}
