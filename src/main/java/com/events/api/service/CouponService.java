package com.events.api.service;

import com.events.api.domain.coupon.Coupon;
import com.events.api.domain.coupon.CouponRequestDTO;
import com.events.api.domain.event.Event;
import com.events.api.repository.CouponRepository;
import com.events.api.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private EventRepository eventRepository;

    public Coupon addCouponToEvent(UUID eventId, CouponRequestDTO couponData) {
        Event currentEvent = eventRepository
                .findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("event not found."));

        Coupon newCoupon = new Coupon();
        newCoupon.setCode(couponData.code());
        newCoupon.setDiscount(couponData.discount());
        newCoupon.setValid(new Date(couponData.valid()));
        newCoupon.setEvent(currentEvent);

        return couponRepository.save(newCoupon);
    }
}
