package com.jwt.springjwt.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.jwt.springjwt.models.*;
import com.jwt.springjwt.payload.request.LoginRequest;
import com.jwt.springjwt.payload.request.SignUpEnseignant;
import com.jwt.springjwt.payload.request.SignUpEtudiant;
import com.jwt.springjwt.payload.request.SignupRequest;
import com.jwt.springjwt.payload.response.JwtResponse;
import com.jwt.springjwt.payload.response.MessageResponse;
import com.jwt.springjwt.repository.EnseignantRepository;
import com.jwt.springjwt.repository.EtudiantRepository;
import com.jwt.springjwt.repository.RoleRepository;
import com.jwt.springjwt.repository.UserRepository;
import com.jwt.springjwt.security.jwt.JwtUtils;
import com.jwt.springjwt.security.configuration.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	@Autowired
	EnseignantRepository enseignantRepository;
	@Autowired
	EtudiantRepository etudiantRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(),
												 userDetails.getUsername(),
												 userDetails.getEmail(),roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

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
			Enseignant e=new Enseignant(signUpRequest.getNom(),signUpRequest.getPrenom(),signUpRequest.getUsername(),signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()),role,"NCONTRAT");
			enseignantRepository.save(e);
			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}

		return ResponseEntity.badRequest().body("no user");
	}

	@PostMapping("/enseignant/signup")
	public ResponseEntity<?> registerEnseignant(@Valid @RequestBody SignUpEnseignant sign) {
		if (enseignantRepository.existsByNContrat(sign.getNContrat())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: NCin is already in use!"));
		}
		if (userRepository.existsByUsername(sign.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: username is already in use!"));
		}
		if (userRepository.existsByEmail(sign.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		Role role=roleRepository.findByName("ROLE_ENSEIGNANT");
			Enseignant e=new Enseignant(sign.getNom(),sign.getPrenom(),sign.getUsername(),sign.getEmail(),encoder.encode(sign.getPassword()),role,sign.getNContrat());
			enseignantRepository.save(e);
			return ResponseEntity.ok(new MessageResponse(sign.getUsername()+" registered successfully!"));

	}
	@PostMapping("/etudiant/signup")
	public ResponseEntity<?> registerEtudiant(@Valid @RequestBody SignUpEtudiant signUpEtudiant) {
		if (etudiantRepository.existsByNCin(signUpEtudiant.getNCin())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: NCin is already in use!"));
		}
		if (userRepository.existsByUsername(signUpEtudiant.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: username is already in use!"));
		}
		if (userRepository.existsByEmail(signUpEtudiant.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		Role role=roleRepository.findByName("ROLE_ETUDIANT");
			Etudiant e=new Etudiant(signUpEtudiant.getNom(),signUpEtudiant.getPrenom(),signUpEtudiant.getUsername(),signUpEtudiant.getEmail(),encoder.encode(signUpEtudiant.getPassword()),role,signUpEtudiant.getNCin());
			etudiantRepository.save(e);
			return ResponseEntity.ok(new MessageResponse(signUpEtudiant.getUsername()+" registered successfully!"));

	}
	@PostMapping("/add/admin")
	public ResponseEntity<?> AddAdmin(@Valid @RequestBody SignupRequest request) {

		if (userRepository.existsByUsername(request.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: username is already in use!"));
		}
		if (userRepository.existsByEmail(request.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		Role role=roleRepository.findByName("ROLE_ADMIN");
		User e=new Admin(request.getNom(),request.getPrenom(),request.getUsername(),request.getEmail(),encoder.encode(request.getPassword()),role);
		userRepository.save(e);
		return ResponseEntity.ok(new MessageResponse(request.getUsername()+" added successfully!"));

	}
}
