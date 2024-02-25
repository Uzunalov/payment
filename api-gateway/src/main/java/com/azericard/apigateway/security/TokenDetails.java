package com.azericard.apigateway.security;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDetails {

    private String userId;
    private Date expireDate;
}
