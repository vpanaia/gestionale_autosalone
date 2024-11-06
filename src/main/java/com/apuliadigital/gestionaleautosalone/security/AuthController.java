package com.apuliadigital.gestionaleautosalone.security;

import com.apuliadigital.gestionaleautosalone.common.AuthUtil;
import com.apuliadigital.gestionaleautosalone.common.JwtUtil;
import com.apuliadigital.gestionaleautosalone.common.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<?> authenticate(@RequestHeader("Authorization") String basicAuthString) {

        try {
            String[] credentials = AuthUtil.extractCredentials(basicAuthString);

            String username = credentials[0];
            String password = credentials[1];

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            if (authentication.isAuthenticated()) {
                return ResponseBuilder.authSuccess(jwtUtil.generateToken(username));
            } else {
                throw new IllegalArgumentException("Invalid username or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.errorMessage(e.getMessage());

        }



    }
}
