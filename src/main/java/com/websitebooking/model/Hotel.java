package com.websitebooking.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hotelName; // Tên khách sạn
    private String location; // Địa điểm
    private int availableRooms; // Số lượng phòng còn trống
    private double pricePerNight;// Giá phòng trung bình
    @Temporal(TemporalType.DATE)
    private Date checkIn; // Thời gian nhận phòng

    @Temporal(TemporalType.DATE)
    private Date checkOut; // Thời gian trả phòng


    @Lob
    private String description; // Mô tả về khách sạn

    private Float rating; // Đánh giá trung bình (5 sao)

    private String imageUrl; // Đường dẫn ảnh đại diện của khách sạn

    @ElementCollection
    private List<String> amenities; // Các tiện nghi (Wi-Fi, điều hòa, v.v.)

    private String address; // Địa chỉ cụ thể của khách sạn

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews; // Đánh giá từ người dùng

    // Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return hotelName;
    }

    public void setName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double price) {
        this.pricePerNight = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
