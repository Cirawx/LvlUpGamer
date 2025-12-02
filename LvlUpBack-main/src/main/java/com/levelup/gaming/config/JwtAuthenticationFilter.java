package com.levelup.gaming.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final com.levelup.gaming.repositories.UserRepository userRepository;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService,
            com.levelup.gaming.repositories.UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = authHeader.substring(7);
        try {
            String userEmail = jwtService.extractUsername(jwt);
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                var userOpt = userRepository.findByEmail(userEmail);
                if (userOpt.isPresent()) {
                    var user = userOpt.get();
                    UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                            .username(user.getEmail())
                            .password(user.getPassword())
                            .roles(user.getRole().toUpperCase())
                            .build();
                    if (jwtService.isTokenValid(jwt, userDetails.getUsername())) {
                        Date issuedAt = jwtService.extractIssuedAt(jwt);
                        System.out.println("DEBUG: Token validation - User: " + user.getEmail());
                        System.out.println("DEBUG: Token IssuedAt: " + issuedAt);
                        System.out.println("DEBUG: User LastLogout: " + user.getLastLogout());

                        if (user.getLastLogout() != null && issuedAt.before(user.getLastLogout())) {
                            System.out.println("DEBUG: Token invalid - Issued before last logout");
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            return;
                        }
                        System.out.println("DEBUG: Token valid");

                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
        } catch (Exception e) {

        }
        filterChain.doFilter(request, response);
    }
}
