package com.example.WebApp.repository;

import com.example.WebApp.entity.PasswordResetToken;
import com.example.WebApp.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(Registration user);
}
