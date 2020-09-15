package com.jwt.springjwt.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SignUpEtudiant {
    @NotBlank
    @Size(min = 4, max = 20)
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

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    @NotBlank
    @Size(min=8,max=8)
    String nCin ;
}
