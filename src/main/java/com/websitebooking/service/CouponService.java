package com.websitebooking.service;

import com.websitebooking.entity.Coupon;
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

    public Optional<Coupon> getCouponById(Long id) {
        return couponRepository.findById(id);
    }

    public Coupon addCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Coupon updateCoupon(Long id, Coupon updatedCoupon) {
        return couponRepository.findById(id)
                .map(coupon -> {
                    coupon.setCode(updatedCoupon.getCode());
                    coupon.setDiscountPercentage(updatedCoupon.getDiscountPercentage());
                    coupon.setExpirationDate(updatedCoupon.getExpirationDate());
                    return couponRepository.save(coupon);
                })
                .orElseThrow(() -> new RuntimeException("Coupon not found with id: " + id));
    }

    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }

    public Coupon getCouponByCode(String code) {
        return couponRepository.findByCode(code);
    }
}
