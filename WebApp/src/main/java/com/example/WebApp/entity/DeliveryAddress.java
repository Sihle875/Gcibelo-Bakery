package com.example.WebApp.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class DeliveryAddress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String suburb;
    private String city;
    private String province;
    private String postalCode;

    // Constructors
    public DeliveryAddress() {
    }

    public DeliveryAddress(String street, String suburb, String city, String province, String postalCode) {
        this.street = street;
        this.suburb = suburb;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
