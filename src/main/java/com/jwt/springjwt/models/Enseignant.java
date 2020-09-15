package com.jwt.springjwt.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("Enseignant")
@Setter
@Getter
@NoArgsConstructor
public class Enseignant extends User{
    @Column(columnDefinition = "varchar(255) default 'interdit'" , nullable=false)
    String nContrat ;
    public Enseignant(String nom,String prenom,String username, String email, String password, Role role, String nContrat) {
        super(nom,prenom,username, email, password, role);
        super.getRole().setName("ROLE_ENSEIGNANT");
        this.nContrat = nContrat;
    }
}
