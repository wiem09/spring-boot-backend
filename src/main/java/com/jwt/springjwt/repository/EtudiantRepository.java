package com.jwt.springjwt.repository;

import com.jwt.springjwt.models.Etudiant;
import com.jwt.springjwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    List<Etudiant> findAllByRole(Role role);

    Optional<Etudiant> findByNCin(String nCin);
    Boolean existsByNCin(String NCin);
}
