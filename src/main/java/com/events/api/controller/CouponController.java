package com.events.api.controller;

import com.events.api.domain.coupon.Coupon;
import com.events.api.domain.coupon.CouponRequestDTO;
import com.events.api.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("event/{eventId}")
    public ResponseEntity<Coupon> addCouponToEvent(@PathVariable UUID eventId, @RequestBody CouponRequestDTO data) {
        Coupon coupon = this.couponService.addCouponToEvent(eventId,data);
        return ResponseEntity.ok(coupon);
    }
}
