package com.jwt.springjwt.repository;

import com.jwt.springjwt.models.Enseignant;
import com.jwt.springjwt.models.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
    Boolean existsByNContrat(String ncontrat);
}
