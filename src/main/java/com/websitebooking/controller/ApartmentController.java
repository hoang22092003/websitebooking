package com.websitebooking.controller;

import com.websitebooking.entity.Apartment;
import com.websitebooking.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping
    public List<Apartment> getAllApartments() {
        return apartmentService.getAllApartments();
    }

    @GetMapping("/{id}")
    public Optional<Apartment> getApartmentById(@PathVariable Long id) {
        return apartmentService.getApartmentById(id);
    }

    @PostMapping
    public Apartment addApartment(@RequestBody Apartment apartment) {
        return apartmentService.addApartment(apartment);
    }

    @PutMapping("/{id}")
    public Apartment updateApartment(@PathVariable Long id, @RequestBody Apartment updatedApartment) {
        return apartmentService.updateApartment(id, updatedApartment);
    }

    @DeleteMapping("/{id}")
    public void deleteApartment(@PathVariable Long id) {
        apartmentService.deleteApartment(id);
    }
}
