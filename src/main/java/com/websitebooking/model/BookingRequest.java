package com.websitebooking.model;

public class BookingRequest {
    private Long userId;
    private Long itemId; // Can refer to a hotel, apartment, or activity
    private String bookingDate;
    private int numberOfGuests;
    private int numberOfRooms; // Applicable for hotels and apartments

    // Constructors, getters, and setters
}

