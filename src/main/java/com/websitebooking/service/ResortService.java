package com.websitebooking.service;

import com.websitebooking.model.Resort;
import com.websitebooking.repository.ResortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResortService {

    @Autowired
    private ResortRepository resortRepository;

    public List<Resort> getAllResorts() {
        return resortRepository.findAll();
    }

    public Resort saveResort(Resort apartment) {
        return resortRepository.save(apartment);
    }

    public Resort getResortById(Long id) {
        return resortRepository.findById(id).orElse(null);
    }

    public void deleteResort(Long id) {
        resortRepository.deleteById(id);
    }

    public List<Resort> searchResorts(String resortName, String location, double minPrice, double maxPrice, String amenities) {
        return resortRepository.findResortsByCriteria(resortName, location, minPrice, maxPrice, amenities);
    }

    // Phương thức cập nhật số phòng còn trống
    public Resort updateAvailableRooms(Long resortId, int roomsToUpdate) {
        Resort resort = getResortById(resortId);
        if (resort != null) {
            int updatedRooms = resort.getAvailableRooms() + roomsToUpdate;
            // Đảm bảo số phòng không âm
            if (updatedRooms < 0) {
                throw new IllegalArgumentException("Số phòng không thể âm.");
            }
            resort.setAvailableRooms(updatedRooms);
            return resortRepository.save(resort);
        }
        throw new IllegalArgumentException("Không tìm thấy resort với ID: " + resortId);
    }
}
