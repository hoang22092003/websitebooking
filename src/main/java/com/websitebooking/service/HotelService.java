package com.websitebooking.service;

import com.websitebooking.entity.Hotel;
import com.websitebooking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Long id, Hotel updatedHotel) {
        return hotelRepository.findById(id)
                .map(hotel -> {
                    hotel.setName(updatedHotel.getName());
                    hotel.setLocation(updatedHotel.getLocation());
                    hotel.setPricePerNight(updatedHotel.getPricePerNight());
                    hotel.setRating(updatedHotel.getRating());
                    hotel.setImageUrl(updatedHotel.getImageUrl());
                    return hotelRepository.save(hotel);
                })
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}
