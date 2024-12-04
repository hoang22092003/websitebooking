package com.websitebooking.service;

import com.websitebooking.model.Hotel;
import com.websitebooking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    // Tìm khách sạn theo các tiêu chí: location, minPrice, maxPrice, amenities
    public List<Hotel> searchHotels(String hotelName, String location, double minPrice, double maxPrice, String amenities) {
        return hotelRepository.findHotelsByCriteria(hotelName, location, minPrice, maxPrice, amenities);
    }
}
