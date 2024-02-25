package com.azericard.user.model;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"token"})
public class UserAccessTokenDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6545598440318328169L;

    private String token;
}
