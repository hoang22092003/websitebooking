package com.websitebooking.service;

import com.websitebooking.entity.Apartment;
import com.websitebooking.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    public List<Apartment> getAllApartments() {
        return apartmentRepository.findAll();
    }

    public Optional<Apartment> getApartmentById(Long id) {
        return apartmentRepository.findById(id);
    }

    public Apartment addApartment(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    public Apartment updateApartment(Long id, Apartment updatedApartment) {
        return apartmentRepository.findById(id)
                .map(apartment -> {
                    apartment.setName(updatedApartment.getName());
                    apartment.setLocation(updatedApartment.getLocation());
                    apartment.setPricePerNight(updatedApartment.getPricePerNight());
                    apartment.setNumberOfRooms(updatedApartment.getNumberOfRooms());
                    apartment.setImageUrl(updatedApartment.getImageUrl());
                    return apartmentRepository.save(apartment);
                })
                .orElseThrow(() -> new RuntimeException("Apartment not found with id: " + id));
    }

    public void deleteApartment(Long id) {
        apartmentRepository.deleteById(id);
    }
}
