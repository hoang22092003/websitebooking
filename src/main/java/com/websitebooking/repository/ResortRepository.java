package com.websitebooking.repository;

import com.websitebooking.model.Resort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResortRepository extends JpaRepository<Resort, Long> {
    @Query("SELECT r FROM Resort r WHERE r.resortName LIKE %:resortName% " +
            "AND r.location LIKE %:location% " +
            "AND r.pricePerNight BETWEEN :minPrice AND :maxPrice " +
            "AND (:amenities IS NULL OR r.amenities LIKE %:amenities%)")
    List<Resort> findResortsByCriteria(String resortName, String location, double minPrice, double maxPrice, String amenities);
}

