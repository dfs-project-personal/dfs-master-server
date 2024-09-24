package com.dfs.dfsmasterserver.repo;

import com.dfs.dfsmasterserver.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
