package com.jwt.springjwt.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@DiscriminatorValue("Etudiant")
@NoArgsConstructor
@Setter
@Getter
//@Table(name = "Etudiant")
public class Etudiant extends User {
    @Column(columnDefinition = "varchar(255) default 'interdit'", nullable=false)
    String nCin ;

    public Etudiant(String nom ,String prenom,String username, String email, String password, Role role, String NCin) {
        super(nom,prenom,username, email, password, role);
        super.getRole().setName("ROLE_ETUDIANT");
        this.nCin = NCin;
    }

}
