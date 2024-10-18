package com.example.WebApp.repository;

import com.example.WebApp.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration,Long> {
    Registration findByEmail(String email);
}
