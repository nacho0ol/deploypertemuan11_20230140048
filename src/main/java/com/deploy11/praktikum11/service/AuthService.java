package com.deploy11.praktikum11.service;

import com.deploy11.praktikum11.model.Profile;
import com.deploy11.praktikum11.model.User;
import com.deploy11.praktikum11.model.dto.RegisterRequest;
import com.deploy11.praktikum11.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request) {

        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        Profile profile = Profile.builder()
                .id(UUID.randomUUID().toString())
                .nama(request.getNama())
                .alamat(request.getAlamat())
                .user(user)
                .build();

        user.setProfile(profile);

        userRepository.save(user);
    }

}
