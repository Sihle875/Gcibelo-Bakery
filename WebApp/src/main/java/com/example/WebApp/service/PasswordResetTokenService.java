package com.example.WebApp.service;

import com.example.WebApp.entity.PasswordResetToken;
import com.example.WebApp.entity.Registration;
import com.example.WebApp.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetTokenService {
    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    public PasswordResetToken createPasswordResetToken(Registration user) {
        PasswordResetToken existingToken = tokenRepository.findByUser(user);
        if (existingToken != null) {
            // If token already exists, update the expiry date
            existingToken.setExpiryDate(LocalDateTime.now().plusHours(1)); // Extend expiry by 1 hour
            return tokenRepository.save(existingToken);
        } else {
            // If token doesn't exist, create a new one
            String token = UUID.randomUUID().toString();
            PasswordResetToken passwordResetToken = new PasswordResetToken();
            passwordResetToken.setToken(token);
            passwordResetToken.setUser(user);
            passwordResetToken.setExpiryDate(LocalDateTime.now().plusHours(1)); // Token expires in 1 hour
            return tokenRepository.save(passwordResetToken);
        }
    }

    public PasswordResetToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void delete(PasswordResetToken resetToken) {
        tokenRepository.delete(resetToken);
    }

}
