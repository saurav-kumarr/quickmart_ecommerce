package com.quickmart.controller;


import com.quickmart.config.AppConstants;
import com.quickmart.payload.AuthenticationResult;
import com.quickmart.security.request.LoginRequest;
import com.quickmart.security.request.SignupRequest;
import com.quickmart.security.response.MessageResponse;
import com.quickmart.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService  authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){

        AuthenticationResult result = authService.login(loginRequest);


        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,
                result.getJwtCookie().toString())
                .body(result.getResponse());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){

      return  authService.userRegister(signupRequest);

    }

    @GetMapping("/username")
    public String currentUserName(Authentication authentication){
        if(authentication != null)
            return authentication.getName();
        else
            return "";
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetail(Authentication authentication){

        return ResponseEntity.ok().body(authService.getCurrentUserDetails(authentication));

    }

    @PostMapping("/signout")
    public ResponseEntity<?> signoutUser(){
        ResponseCookie cookie = authService.logoutUser();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,
                cookie.toString())
                .body(new MessageResponse("You've been signed out!"));

    }

    @GetMapping("/sellers")
    public ResponseEntity<?> getAllSellers(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber
    ) {
            Sort sortByAndOrder = Sort.by(AppConstants.SORT_USERS_BY).descending();
            Pageable pageDetails =  PageRequest.of(pageNumber, Integer.parseInt(AppConstants.PAGE_SIZE), sortByAndOrder);

            return ResponseEntity.ok(authService.getAllSellers(pageDetails));
    }


}
