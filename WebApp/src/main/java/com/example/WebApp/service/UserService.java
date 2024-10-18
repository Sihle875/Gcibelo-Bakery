package com.example.WebApp.service;

import com.example.WebApp.entity.Registration;
import com.example.WebApp.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private RegistrationRepository userRepository;

    public Registration findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Registration save(Registration user) {
        return userRepository.save(user);
    }

    // Other methods for user-related operations as needed

}

