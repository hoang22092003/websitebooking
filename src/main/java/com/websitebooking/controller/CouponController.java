package com.websitebooking.controller;

import com.websitebooking.entity.Coupon;
import com.websitebooking.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping
    public List<Coupon> getAllCoupons() {
        return couponService.getAllCoupons();
    }

    @GetMapping("/{id}")
    public Optional<Coupon> getCouponById(@PathVariable Long id) {
        return couponService.getCouponById(id);
    }

    @PostMapping
    public Coupon addCoupon(@RequestBody Coupon coupon) {
        return couponService.addCoupon(coupon);
    }

    @PutMapping("/{id}")
    public Coupon updateCoupon(@PathVariable Long id, @RequestBody Coupon updatedCoupon) {
        return couponService.updateCoupon(id, updatedCoupon);
    }

    @DeleteMapping("/{id}")
    public void deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
    }

    @GetMapping("/code/{code}")
    public Coupon getCouponByCode(@PathVariable String code) {
        return couponService.getCouponByCode(code);
    }
}
