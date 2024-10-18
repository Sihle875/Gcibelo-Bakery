package com.example.WebApp.repository;

import com.example.WebApp.entity.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress,Long> {
}
