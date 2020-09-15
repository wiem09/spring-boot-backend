package com.jwt.springjwt.repository;

import com.jwt.springjwt.models.Etudiant;
import com.jwt.springjwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    List<Etudiant> findAllByRole(Role role);
}
