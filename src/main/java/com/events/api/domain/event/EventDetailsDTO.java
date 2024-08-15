package com.events.api.domain.event;

import com.events.api.domain.coupon.CouponDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record EventDetailsDTO(UUID id,
                              String title,
                              String description,
                              Date date,
                              String city,
                              String uf,
                              String imageUrl,
                              String eventUrl,
                              List<CouponDTO> coupons) {
}

