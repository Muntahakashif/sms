package com.sms.controller;

import com.sms.entities.User;
import com.sms.services.User.UserService;
import com.sms.utils.Jwtutil;
import com.sms.dto.AuthenticationRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private Jwtutil jwtutil;

    @Autowired
    private HttpServletResponse httpServletResponse;
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        System.out.println("Received authentication request for email: " + authenticationRequest.getEmail());  // Debugging email in the authentication request
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
            System.out.println("Authentication successful for email: " + authenticationRequest.getEmail());  // Debugging successful authentication
        } catch (BadCredentialsException e) {
            System.out.println("Invalid username or password");  // Debugging invalid credentials
            throw new BadCredentialsException("Invalid username or password");
        } catch (DisabledException disabledException) {
            try {
                System.out.println("User not created. Returning error response.");  // Debugging user not found
                httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND, "User not created");
                return null;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        Optional<User> user = userService.findByUserEmail(authenticationRequest.getEmail());
        if(user.isEmpty()) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        System.out.println("UserDetails loaded for email: " + authenticationRequest.getEmail());  // Debugging loaded user details

        final String jwt = jwtutil.generateToken(user.get().getName());
        System.out.println("Generated JWT: " + jwt);

        response.setHeader("Authorization", jwt);

        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

}
