package com.jwt.springjwt.controllers;

import com.jwt.springjwt.models.Enseignant;
import com.jwt.springjwt.models.Etudiant;
import com.jwt.springjwt.models.Role;
import com.jwt.springjwt.payload.request.SignUpEtudiant;
import com.jwt.springjwt.payload.request.SignupRequest;
import com.jwt.springjwt.payload.response.MessageResponse;
import com.jwt.springjwt.repository.EnseignantRepository;
import com.jwt.springjwt.repository.EtudiantRepository;
import com.jwt.springjwt.repository.RoleRepository;
import com.jwt.springjwt.repository.UserRepository;
import com.jwt.springjwt.services.EtudianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/etudiant")
public class EtudiantController {
    @Autowired
    EtudianService etudianService;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EnseignantRepository enseignantRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Etudiant> allEtudiant() {
        return  etudiantRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) { return etudianService.getOne(id); }

}
