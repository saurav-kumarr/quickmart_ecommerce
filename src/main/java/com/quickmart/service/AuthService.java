package com.quickmart.service;

import com.quickmart.payload.AuthenticationResult;
import com.quickmart.payload.UserResponse;
import com.quickmart.security.request.LoginRequest;
import com.quickmart.security.request.SignupRequest;
import com.quickmart.security.response.MessageResponse;
import com.quickmart.security.response.UserInfoResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface AuthService {
    AuthenticationResult login(LoginRequest loginRequest);

    ResponseEntity<MessageResponse> userRegister(@Valid SignupRequest signupRequest);


    UserInfoResponse getCurrentUserDetails(Authentication authentication);

    ResponseCookie logoutUser();

    UserResponse getAllSellers(Pageable pageDetails);
}
