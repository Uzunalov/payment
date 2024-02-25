package com.azericard.user.service;

import com.azericard.commonlib.exception.AuthException;
import com.azericard.commonlib.exception.ResourceAlreadyExistsException;
import com.azericard.commonlib.exception.ResourceNotFoundException;
import com.azericard.user.dao.entity.User;
import com.azericard.user.dao.repository.UserRepository;
import com.azericard.user.mapper.UserMapper;
import com.azericard.user.model.request.UserSignInRequest;
import com.azericard.user.model.request.UserSignUpRequest;
import com.azericard.user.model.UserAccessTokenDto;
import com.azericard.user.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;
    private final TokenProvider tokenProvider;
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserAccessTokenDto signIn(UserSignInRequest request) {
        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthException("The email address you entered is incorrect. Please try again."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthException("The password you entered is incorrect. Please try again.");
        }

        UserAccessTokenDto accessToken = tokenProvider.generateToken(user.getId().toString());
        redisService.save(user.getId().toString(), accessToken);
        return accessToken;
    }

    public UserAccessTokenDto signUp(UserSignUpRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("User already exists");
        }
        User user = repository.save(mapper.toEntity(request));
        UserAccessTokenDto accessToken = tokenProvider.generateToken(user.getId().toString());
        redisService.save(user.getId().toString(), accessToken);
        return accessToken;
    }

    public void signOut(String userId) {
        redisService.delete(userId);
    }
}
