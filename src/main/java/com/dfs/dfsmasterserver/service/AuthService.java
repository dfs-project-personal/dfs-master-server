package com.dfs.dfsmasterserver.service;

import com.dfs.dfsmasterserver.dto.LoginReqDTO;
import com.dfs.dfsmasterserver.model.AppUser;

public interface AuthService {
    String login(LoginReqDTO user);
    AppUser register(AppUser user);
}
