package com.jwt.springjwt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "Etudiant")
public class Etudiant extends User{
    @Column(unique=true, nullable=false)
   Integer NCin ;
public Etudiant(){}
    public Etudiant(String username, String email, String password, Role role, Integer NCin) {
        super(username, email, password, role);
        super.getRole().setName("ROLE_ETUDIANT");
        this.NCin = NCin;
    }

    public Integer getNCin() {
        return NCin;
    }

    public void setNCin(Integer NCin) {
        this.NCin = NCin;
    }
}
