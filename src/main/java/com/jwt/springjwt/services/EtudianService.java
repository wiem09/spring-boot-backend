package com.jwt.springjwt.services;

import com.jwt.springjwt.models.Etudiant;
import com.jwt.springjwt.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EtudianService {
    @Autowired
    EtudiantRepository etudiantRepository;
    public ResponseEntity<?> getOne (Long id)   {

        if(id == null)
            return ResponseEntity.badRequest().body("set id");

        Optional<Etudiant> etudiantOptional = etudiantRepository.findById(id);

        if(!etudiantOptional.isPresent())
            return ResponseEntity.badRequest().body("no one is there");

        return new ResponseEntity<>(etudiantOptional.get() , HttpStatus.OK);
    }
}
