package com.dfs.dfsmasterserver.repo;

import com.dfs.dfsmasterserver.model.AppUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {
    @Autowired
    private UserRepo userRepo;

    @Test
    public void userRepo_save_returnUser() {
        // Arrange
        AppUser user = AppUser.builder()
                .firstName("John")
                .lastName("Doe")
                .username("john@email.com")
                .password("John@123")
                .build();


        // Act
        AppUser savedUser = userRepo.save(user);

        // Assert
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isNotNull();
        Assertions.assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void userRepo_findByUsername_returnUser() {
        // Arrange
        AppUser user = AppUser.builder()
                .firstName("John")
                .lastName("Doe")
                .username("john@email.com")
                .password("John@123")
                .build();
        userRepo.save(user);

        // Act
        AppUser foundUser = userRepo.findByUsername(user.getUsername());

        // Assert
        Assertions.assertThat(foundUser).isNotNull();
        Assertions.assertThat(foundUser.getId()).isNotNull();
        Assertions.assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void userRepo_findByUsername_returnNull() {
        // Act
        AppUser foundUser = userRepo.findByUsername("nonexistent@email.com");

        // Assert
        Assertions.assertThat(foundUser).isNull();
    }
}
