package com.henry.jwt.controller;

import com.henry.jwt.entity.User;
import com.henry.jwt.entity.requests.JwtRequest;
import com.henry.jwt.entity.responses.ApiResponse;
import com.henry.jwt.entity.responses.JwtResponse;
import com.henry.jwt.security.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class JwtAuthenticationController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    /*
    JSON Body
        {
            "email" : "email",
            "password" : "password"
        }
    */

    @PostMapping("/login")
    public ApiResponse loginHandler(@RequestBody JwtRequest body) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            authenticationManager.authenticate(authInputToken);
            String jwtToken = jwtUtil.generateToken(body.getEmail());

            return new ApiResponse(200, LocalDateTime.now().toString(), "Logged in successfully.",
                    new JwtResponse(jwtToken, new User(1, "email", "password")));
        } catch (AuthenticationException authExc) {
            return new ApiResponse(500, LocalDateTime.now().toString(), authExc.getMessage(), null);
        }
    }

}
