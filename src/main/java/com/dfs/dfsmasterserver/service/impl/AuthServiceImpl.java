package com.dfs.dfsmasterserver.service.impl;

import com.dfs.dfsmasterserver.dto.LoginReqDTO;
import com.dfs.dfsmasterserver.model.AppUser;
import com.dfs.dfsmasterserver.repo.UserRepo;
import com.dfs.dfsmasterserver.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final JWTServiceImpl jwtService;
    private final AuthenticationManager authManager;
    private final BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder(12);
    @Override
    public String login(LoginReqDTO user) {
        log.info("User {} logging in", user.getUsername());
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }

        return "login failed";
    }

    @Override
    public AppUser register(AppUser user) {
        log.info("Saving new user {} to database", user.getName());
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
}
