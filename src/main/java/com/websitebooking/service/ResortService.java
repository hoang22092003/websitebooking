package com.websitebooking.service;

import com.websitebooking.model.Apartment;
import com.websitebooking.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    public List<Apartment> getAllApartments() {
        return apartmentRepository.findAll();
    }

    public Apartment saveApartment(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    public Apartment getApartmentById(Long id) {
        return apartmentRepository.findById(id).orElse(null);
    }

    public void deleteApartment(Long id) {
        apartmentRepository.deleteById(id);
    }
}
