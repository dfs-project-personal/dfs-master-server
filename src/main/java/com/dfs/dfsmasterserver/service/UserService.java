package com.dfs.dfsmasterserver.service;

import com.dfs.dfsmasterserver.model.AppUser;
import com.dfs.dfsmasterserver.model.Role;

import java.util.List;

public interface UserService {
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
