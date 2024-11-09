package com.websitebooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private double pricePerNight;
    private int numberOfRooms;
    private String imageUrl;
}
