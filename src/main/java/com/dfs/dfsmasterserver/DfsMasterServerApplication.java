package com.dfs.dfsmasterserver;

import com.dfs.dfsmasterserver.model.AppUser;
import com.dfs.dfsmasterserver.model.Role;
import com.dfs.dfsmasterserver.service.AuthService;
import com.dfs.dfsmasterserver.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class DfsMasterServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DfsMasterServerApplication.class, args);
    }

    // This method is used to insert some data into the database when the application starts
    @Bean
    CommandLineRunner run(UserService userService, AuthService authService) {
        return args -> {
            userService.saveRole(new Role(null, "USER"));
            userService.saveRole(new Role(null, "ADMIN"));
            userService.saveRole(new Role(null, "SUPER_ADMIN"));

            authService.register(new AppUser(null, "Akash Tharuka", "akashtharuka", "a@123", new ArrayList<>()));
            authService.register(new AppUser(null, "Dilshan", "dilshan", "d@123", new ArrayList<>()));
            authService.register(new AppUser(null, "Kasun", "kasun", "k@123", new ArrayList<>()));
            authService.register(new AppUser(null, "Nipuna", "nipuna", "n@123", new ArrayList<>()));

            userService.addRoleToUser("akashtharuka", "SUPER_ADMIN");
            userService.addRoleToUser("akashtharuka", "USER");
            userService.addRoleToUser("dilshan", "USER");
            userService.addRoleToUser("dilshan", "ADMIN");
            userService.addRoleToUser("kasun", "USER");
            userService.addRoleToUser("nipuna", "USER");
        };
    }

}
