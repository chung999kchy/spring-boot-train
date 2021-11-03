package com.example.ex9.controller;

import com.example.ex9.model.jwt.request.LoginForm;
import com.example.ex9.model.jwt.request.SignUpForm;
import com.example.ex9.model.jwt.response.JwtResponse;
import com.example.ex9.model.entity.impl.User;
import com.example.ex9.model.responseapi.MsgSuccess;
import com.example.ex9.security.jwt.JwtProvider;
import com.example.ex9.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ApplicationAuthApi {
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final PasswordEncoder encoder;

    private final JwtProvider jwtProvider;

    public ApplicationAuthApi(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder encoder, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
    }


//    {
//        "username": "admin",
//        "password": "admin"
//    }
//        => Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYzMzg3NTc1MCwiZXhwIjoxNjMzODc1ODM3fQ.-Bs7ho_D58QQcbtBnBhmBjRTXQtu4Kl-QEMQ24haMCUGHmGYwKvqFCJUJ2NEHmFvUd-zg5pUtPTLDZC1xpbRNg
    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<MsgSuccess> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        return new ResponseEntity<>(new MsgSuccess(new JwtResponse(jwt)), HttpStatus.OK);
    }




//    {
//        "name": "chung",
//            "username": "admin",
//            "email": "admin@gmail.com",
//            "role": [ "admin"],
//        "password": "admin"
//    }
//
    @PostMapping("/register")
    public ResponseEntity<MsgSuccess> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        userService.addRolesToUser(user, strRoles);
        userService.saveUser(user);

        return new ResponseEntity<>(new MsgSuccess("Success sign up"), HttpStatus.CREATED);
    }
}