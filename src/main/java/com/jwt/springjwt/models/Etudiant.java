package com.jwt.springjwt.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@DiscriminatorValue("Etudiant")
//@Table(name = "Etudiant")
public class Etudiant extends User {
    @Column(columnDefinition = "varchar(255) default 'interdit'",unique=true, nullable=false)
    String NCin ;
public Etudiant(){}
    public Etudiant(String username, String email, String password, Role role, String NCin) {
        super(username, email, password, role);
        super.getRole().setName("ROLE_ETUDIANT");
        this.NCin = NCin;
    }

    public String getNCin() {
        return NCin;
    }

    public void setNCin(String NCin) {
        this.NCin = NCin;
    }
}
