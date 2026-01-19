package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class RegisterService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    public String register(RegisterRequest request) {

        System.out.println("➡ Register API hit");
        System.out.println("➡ Email received: " + request.getEmail());
        System.out.println("➡ Role received: " + request.getRole());

        if (repo.existsByEmail(request.getEmail())) {
            System.out.println("❌ Email already exists");
            return "Email already registered!";
        }

        Role role = roleRepo.findByName(request.getRole());
        System.out.println("➡ Role from DB: " + role);

        if (role == null) {
            System.out.println("❌ Role not found in DB");
            return "Invalid role!";
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(role);

        repo.save(user);
        System.out.println("✔ User saved successfully!");

        return "User Registered Successfully!";
    }
}
