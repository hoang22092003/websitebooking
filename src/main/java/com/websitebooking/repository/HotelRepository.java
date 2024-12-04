package com.websitebooking.repository;

import com.websitebooking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("SELECT h FROM Hotel h WHERE h.hotelName LIKE %:hotelName% " +
            "AND h.location LIKE %:location% " +
            "AND h.pricePerNight BETWEEN :minPrice AND :maxPrice " +
            "AND (:amenities IS NULL OR :amenities LIKE %:amenities%)") // Sửa phần amenities
    List<Hotel> findHotelsByCriteria(String hotelName, String location, double minPrice, double maxPrice, String amenities);
}

