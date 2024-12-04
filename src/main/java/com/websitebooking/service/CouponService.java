package com.websitebooking.service;

import com.websitebooking.model.Coupon;
import com.websitebooking.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponService {
    @Autowired
    private CouponRepository couponRepository;

    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    public Coupon saveCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Optional<Coupon> getCouponByCode(String code) {
        return couponRepository.findByCode(code);
    }

    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }

    public Coupon applyCoupon(String code) {
        Optional<Coupon> optionalCoupon = couponRepository.findByCode(code);

        if (optionalCoupon.isPresent()) {
            Coupon coupon = optionalCoupon.get();
            if (coupon.getIsActive() && coupon.getEndDate().after(new java.util.Date())) {
                return coupon; // Coupon hợp lệ
            } else {
                throw new IllegalArgumentException("Coupon đã hết hạn hoặc không hoạt động!");
            }
        } else {
            throw new IllegalArgumentException("Coupon không tồn tại!");
        }
    }
}

