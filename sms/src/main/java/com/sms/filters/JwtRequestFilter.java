package com.sms.filters;

import com.sms.entities.User;
import com.sms.services.User.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import com.sms.utils.Jwtutil;
import com.sms.services.jwt.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {


    private final UserService userService;
    private final Jwtutil jwtUtil;

    public JwtRequestFilter(UserService userService,Jwtutil jwtUtil)
    {
        this.userService=userService;
        this.jwtUtil=jwtUtil;
    }
    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = request.getReader();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("in do filter Internal");
    }
//
//        String authorizationHeader = request.getHeader("Authorization");
//        System.out.println("Authorization Header: " + authorizationHeader);  // Debugging the authorization header
//        String username = null;
//        String token = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            token = authorizationHeader.substring(7);
//            System.out.println("Extracted Token from Header: " + token);  // Debugging the extracted token
//            try {
//                username = jwtUtil.extractAllClaims(token).getSubject();
//                System.out.println("Username extracted from JWT: " + username);  // Debugging the extracted username
//            } catch (Exception e) {
//                logger.error("JWT processing failed: " + e.getMessage());
//                System.out.println("Error extracting claims: " + e.getMessage());  // Debugging error in JWT processing
//            }
//        }
//
//        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            System.out.println("Username is not null and authentication context is empty.");  // Debugging flow
//            Optional<User> user = userService.findByName(username);
//            System.out.println("UserDetails loaded for username: " + username);  // Debugging user details loading
//
//            if(jwtUtil.validateToken(token, username)) {
//                System.out.println("Token is valid. Authenticating user.");  // Debugging token validation
////                UsernamePasswordAuthenticationToken authenticationToken =
////                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
////                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            } else {
//                System.out.println("Token is invalid.");  // Debugging invalid token case
//            }
//        }
//
 //       filterChain.doFilter(request, response);
//    }
}

