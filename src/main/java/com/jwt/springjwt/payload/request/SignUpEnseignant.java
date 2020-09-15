package com.jwt.springjwt.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Setter
@Getter
@NoArgsConstructor
public class SignUpEnseignant {
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
    @Size(min=8,max=14)
    String nContrat ;
}
