package com.posavjetujme.demo.controllers;
import com.posavjetujme.demo.dto.UserLoginDTO;
import com.posavjetujme.demo.security.jwt.JwtToken;
import com.posavjetujme.demo.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginDTO userLoginDTO)
    {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginDTO.getUsername(), userLoginDTO.getPassword()
        );
        try
        {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtTokenProvider.createToken(authentication); // formiran token
            return new ResponseEntity<>(new JwtToken(jwt), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
}