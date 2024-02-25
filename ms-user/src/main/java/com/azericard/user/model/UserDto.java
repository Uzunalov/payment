package com.azericard.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID id;
    private String email;
    private String firstName;
    private String lastName;

    @JsonIgnore
    @ToString.Exclude
    private String password;

    @JsonIgnore
    private String phoneNumber;
}
