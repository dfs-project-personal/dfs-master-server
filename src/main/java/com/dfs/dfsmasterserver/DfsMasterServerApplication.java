package com.dfs.dfsmasterserver;

import com.dfs.dfsmasterserver.model.AppUser;
import com.dfs.dfsmasterserver.model.Role;
import com.dfs.dfsmasterserver.service.AuthService;
import com.dfs.dfsmasterserver.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

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

            authService.register(new AppUser(null, "Akash", "Tharuka", "akash@email.com", "Akash@123", new ArrayList<>()));
            authService.register(new AppUser(null, "Dilshan", "Perera", "dilshan@email.com", "Dilshan@123", new ArrayList<>()));
            authService.register(new AppUser(null, "Kasun", "Lakshan", "kasun@email.com", "Kasun@123", new ArrayList<>()));
            authService.register(new AppUser(null, "Nipun", "Iranga", "nipun@email.com", "Nipun@123", new ArrayList<>()));

            userService.addRoleToUser("akash@email.com", "SUPER_ADMIN");
            userService.addRoleToUser("akash@email.com", "USER");
            userService.addRoleToUser("dilshan@email.com", "USER");
            userService.addRoleToUser("dilshan@email.com", "ADMIN");
            userService.addRoleToUser("kasun@email.com", "USER");
            userService.addRoleToUser("nipun@email.com", "USER");
        };
    }

}
