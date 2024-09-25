package com.dfs.dfsmasterserver.controller;

import com.dfs.dfsmasterserver.dto.LoginReqDTO;
import com.dfs.dfsmasterserver.model.AppUser;
import com.dfs.dfsmasterserver.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginReqDTO user) {
        return authService.login(user);
    }

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody AppUser user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/register").toUriString());
        return ResponseEntity.created(uri).body(authService.register(user));
    }

}
