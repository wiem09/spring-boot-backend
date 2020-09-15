package com.jwt.springjwt.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("Enseignant")
public class Enseignant extends User{
    @Column(columnDefinition = "varchar(255) default 'interdit'" ,unique=true, nullable=false)
    String NContrat ;
    public Enseignant(){}
    public Enseignant(String username, String email, String password, Role role, String NContrat) {
        super(username, email, password, role);
        super.getRole().setName("ROLE_ENSEIGNANT");
        this.NContrat = NContrat;
    }
}
