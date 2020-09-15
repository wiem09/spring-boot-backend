package com.jwt.springjwt.controllers;

import com.jwt.springjwt.models.Enseignant;
import com.jwt.springjwt.models.Etudiant;
import com.jwt.springjwt.models.Role;
import com.jwt.springjwt.payload.request.SignupRequest;
import com.jwt.springjwt.payload.response.MessageResponse;
import com.jwt.springjwt.repository.EnseignantRepository;
import com.jwt.springjwt.repository.EtudiantRepository;
import com.jwt.springjwt.repository.RoleRepository;
import com.jwt.springjwt.repository.UserRepository;
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
    public List<Etudiant> allEtudiant() {
        return  etudiantRepository.findAll();
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerEtudiant(@Valid @RequestBody SignupRequest signUpRequest) {


        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        if (!(roleRepository.existsRoleByName(signUpRequest.getRole()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: set valid role please!"));
        }
        Role role=roleRepository.findByName(signUpRequest.getRole());
        if(signUpRequest.getRole().equals("ROLE_ENSEIGNANT")){
            Enseignant e=new Enseignant(signUpRequest.getUsername(),signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()),role,"NCONTRAT");
            enseignantRepository.save(e);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        }
        if(signUpRequest.getRole().equals("ROLE_ETUDIANT")){
            Etudiant e=new Etudiant(signUpRequest.getUsername(),signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()),role,"NCIN");
            etudiantRepository.save(e);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        }
//		if(signUpRequest.getRole()==""){
//
//		}
        // Create new user's account
//		User user = new User(signUpRequest.getUsername(),
//							 signUpRequest.getEmail(),
//							 encoder.encode(signUpRequest.getPassword()),role);
//		userRepository.save(user);

        return ResponseEntity.badRequest().body("no user");
    }
}
