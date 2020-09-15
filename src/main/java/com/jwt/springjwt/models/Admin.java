package com.jwt.springjwt.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Admin")
@NoArgsConstructor
@Setter
@Getter
public class Admin extends User{
    public Admin(String nom, String prenom, String username, String email, String password, Role role) {
        super(nom, prenom, username, email, password, role);
        super.getRole().setName("ROLE_ADMIN");
    }
}
