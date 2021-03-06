package com.jwt.springjwt.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.*;
 @Setter
 @Getter
 @NoArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min = 5, max = 20)
    private String nom;
    @NotBlank
    @Size(min = 3, max = 20)
    private String prenom;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private String role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}
