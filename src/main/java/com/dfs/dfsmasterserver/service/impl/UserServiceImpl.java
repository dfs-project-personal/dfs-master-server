package com.dfs.dfsmasterserver.service.impl;

import com.dfs.dfsmasterserver.exceptions.DataAccessException;
import com.dfs.dfsmasterserver.exceptions.ResourceNotFoundException;
import com.dfs.dfsmasterserver.model.AppUser;
import com.dfs.dfsmasterserver.model.Role;
import com.dfs.dfsmasterserver.model.UserPrincipal;
import com.dfs.dfsmasterserver.repo.UserRepo;
import com.dfs.dfsmasterserver.repo.RoleRepo;
import com.dfs.dfsmasterserver.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j // for logging
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser user = userRepo.findByUsername(username);

        if (user == null) {
            log.error("User not found");
            throw new ResourceNotFoundException("User not found");
        } else {
            log.info("User {} found", username);
        }

        return new UserPrincipal(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        try {
            return roleRepo.save(role);
        } catch (Exception e) {
            log.error("Failed to save role {} to the database", role.getName());
            throw new DataAccessException("Failed to save role to the database");
        }

    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", username, roleName);
        try {
            AppUser user = userRepo.findByUsername(username);
            Role role = roleRepo.findByName(roleName);
            if (user == null || role == null) {
                log.error("User {} not found", username);
                throw new ResourceNotFoundException("User not found");
            }
            user.getRoles().add(role);
        } catch (Exception e) {
            log.error("Failed to add role {} to user {}", roleName, username);
            throw new DataAccessException("Failed to add role to user");
        }
    }
}
