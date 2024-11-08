package com.apuliadigital.gestionaleautosalone.security;

import com.apuliadigital.gestionaleautosalone.common.AuthUtil;
import com.apuliadigital.gestionaleautosalone.common.JwtUtil;
import com.apuliadigital.gestionaleautosalone.common.Logger;
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

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestHeader("Authorization") String basicAuthString) {

        Logger.info("Authentication in progress...");

        String username;
        String password;

        try {
            String[] credentials = AuthUtil.extractCredentials(basicAuthString);

            username = credentials[0];
            password = credentials[1];

            Logger.info(String.format("%s is trying to authenticate.", username));

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            if (authentication.isAuthenticated()) {
                Logger.info(String.format("%s successfully authenticated.", username));
                return ResponseBuilder.authSuccess(jwtUtil.generateToken(username));
            } else {
                throw new BadCredentialsException("Invalid credentials");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Logger.warning("Bad request");
            return ResponseBuilder.badRequest(e.getMessage());
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            Logger.warning("Bad credentials");
            return ResponseBuilder.badCredentials();
        }


    }
}
