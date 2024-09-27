package com.dfs.dfsmasterserver.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

// this class cannot be named as User as spring security has a class called User.
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false, updatable = false)
    private Long id;
    private String firstName;
    private String lastName;
    // username will be the email of the user from the frontend
    @Column(unique = true)
    private String username;
    private String password;
    // Need to fetch all the corresponding roles when I fetch the User
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
}
