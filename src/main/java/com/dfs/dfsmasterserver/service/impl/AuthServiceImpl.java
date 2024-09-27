package com.dfs.dfsmasterserver.service.impl;

import com.dfs.dfsmasterserver.dto.LoginReqDTO;
import com.dfs.dfsmasterserver.exceptions.AuthenticationFailedException;
import com.dfs.dfsmasterserver.exceptions.DataAccessException;
import com.dfs.dfsmasterserver.model.AppUser;
import com.dfs.dfsmasterserver.repo.UserRepo;
import com.dfs.dfsmasterserver.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final JWTServiceImpl jwtService;
    private final AuthenticationManager authManager;
    private final BCryptPasswordEncoder bcryptEncoder;

    public AuthServiceImpl(UserRepo userRepo, JWTServiceImpl jwtService, AuthenticationManager authManager) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.bcryptEncoder = new BCryptPasswordEncoder(12);
    }

    @Override
    public String login(LoginReqDTO user) {
        log.info("User {} logging in", user.getUsername());
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(user.getUsername());
            }

            throw new AuthenticationFailedException("Authentication failed");

        } catch (Exception e) {
            log.error("Authentication failed for user {}", user.getUsername());
            throw new AuthenticationFailedException("Authentication failed");
        }
    }

    @Override
    public AppUser register(AppUser user) {
        log.info("Saving new user {} to database", user.getUsername());
        try {
            if (!sanitizeUser(user)) {
                log.error("User {} failed to pass sanitization", user.getUsername());
                throw new IllegalArgumentException("Failed to save user to database");
            }
            user.setPassword(bcryptEncoder.encode(user.getPassword()));
            return userRepo.save(user);
        } catch (IllegalArgumentException e) {
            log.error("Failed to save user {} to database", user.getUsername());
            throw new IllegalArgumentException("Failed to save user to database");
        } catch (Exception e) {
            log.error("Failed to save user {} to database", user.getUsername());
            throw new DataAccessException("Failed to save user to database");
        }

    }

    private boolean sanitizeUser(AppUser user) {
        String EMAIL_REGEX = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        boolean validUsername = user.getUsername().matches(EMAIL_REGEX);

        String NAME_REGEX = "^[a-zA-Z][a-zA-Z0-9-_]{3,23}$";
        boolean validFirstName = user.getFirstName().matches(NAME_REGEX);

        boolean validLastName = user.getLastName().matches(NAME_REGEX);
        String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$";

        boolean validPassword = user.getPassword().matches(PASSWORD_REGEX);

        return validUsername && validFirstName && validLastName && validPassword;
    }
}
