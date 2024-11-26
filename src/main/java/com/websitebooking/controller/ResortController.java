package com.websitebooking.controller;

import com.websitebooking.model.Apartment;
import com.websitebooking.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping
    public List<Apartment> getAllApartments() {
        return apartmentService.getAllApartments();
    }

    @PostMapping
    public Apartment createApartment(@RequestBody Apartment apartment) {
        return apartmentService.saveApartment(apartment);
    }

    @GetMapping("/{id}")
    public Apartment getApartmentById(@PathVariable Long id) {
        return apartmentService.getApartmentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteApartment(@PathVariable Long id) {
        apartmentService.deleteApartment(id);
    }
}
