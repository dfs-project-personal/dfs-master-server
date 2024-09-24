package com.dfs.dfsmasterserver.repo;

import com.dfs.dfsmasterserver.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
