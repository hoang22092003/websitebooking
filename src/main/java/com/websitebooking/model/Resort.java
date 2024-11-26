package com.websitebooking.model;

import jakarta.persistence.*;

@Entity
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private double price;

    // Getters and setters
}
