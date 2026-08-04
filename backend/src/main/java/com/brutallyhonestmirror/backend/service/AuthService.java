package com.brutallyhonestmirror.backend.service;

import com.brutallyhonestmirror.backend.config.JwtUtil;
import com.brutallyhonestmirror.backend.dto.RegisterRequest;
import com.brutallyhonestmirror.backend.exception.EmailAlreadyExistsException;
import com.brutallyhonestmirror.backend.model.User;
import com.brutallyhonestmirror.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest request){

        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("An account with this email already exists");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword);
        userRepository.save(user);

        return jwtUtil.generateToken(request.getEmail());
    }
}
