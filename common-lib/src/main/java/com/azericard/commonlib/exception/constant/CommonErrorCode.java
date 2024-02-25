package com.azericard.commonlib.exception.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonErrorCode {

    public static final String UNAUTHORIZED = "unauthorized";
    public static final String ALREADY_EXIST = "already_exist";
    public static final String NOT_FOUND = "data_not_found";
    public static final String INSUFFICIENT_FUNDS = "insufficient_funds";
}
