package com.azericard.user.controller;

import static com.azericard.commonlib.constants.CommonConstants.USER_ID_HEADER;
import static com.azericard.commonlib.util.ResponseBuilder.buildResponse;

import com.azericard.user.model.request.UserSignInRequest;
import com.azericard.user.model.request.UserSignUpRequest;
import com.azericard.user.model.UserAccessTokenDto;
import com.azericard.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @PostMapping("/public/users/sign-up")
    public ResponseEntity<UserAccessTokenDto> signUp(@Valid @RequestBody UserSignUpRequest request) {
        return buildResponse(userService.signUp(request), HttpStatus.CREATED);
    }

    @PostMapping("/public/users/sign-in")
    public ResponseEntity<UserAccessTokenDto> signIn(@Valid @RequestBody UserSignInRequest request) {
        return buildResponse(userService.signIn(request));
    }

    @PostMapping("/users/sign-out")
    public ResponseEntity<HttpStatus> signOut(@RequestHeader(USER_ID_HEADER) String userId) {
        userService.signOut(userId);
        return buildResponse();
    }
}
