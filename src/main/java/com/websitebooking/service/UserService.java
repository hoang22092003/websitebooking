package com.websitebooking.service;

import com.websitebooking.model.User;
import com.websitebooking.repository.UserRepository;
import com.websitebooking.Role; // Ensure this import is present
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void setDefaultRole(String username) {
        User user = findByUsername(username);
        if (user != null) {
            user.setRole(Role.USER); // Set default role to USER
            userRepository.save(user); // Save changes to the database
        }
    }
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void updateUserRole(Long id, Role role) {
        User user = findUserById(id);
        if (user != null) {
            user.setRole(role);
            userRepository.save(user);
        }
    }

    public void updateUserBookedNights(User user, int newBookedNights) {
        user.setBookedNights(newBookedNights);  // Cập nhật số đêm đã đặt
        updateVipLevel(user);  // Gọi updateVipLevel để tính lại cấp độ VIP
    }

    public String calculateVipLevel(int bookedNights) {
        if (bookedNights >= 25) {
            return "Platinum";
        } else if (bookedNights >= 15) {
            return "Gold";
        } else if (bookedNights >= 5) {
            return "Silver";
        } else {
            return "Bronze";
        }
    }

    public String getUserVipLevel(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return calculateVipLevel(user.getBookedNights());
        }
        return "Unknown";
    }

    public void updateVipLevel(User user) {
        int bookedNights = user.getBookedNights();
        String vipLevel = calculateVipLevel(bookedNights);
        user.setVipLevel(vipLevel);
        userRepository.save(user);
    }


}