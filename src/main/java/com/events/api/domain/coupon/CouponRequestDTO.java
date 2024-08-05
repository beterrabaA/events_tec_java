package com.events.api.domain.coupon;

public record CouponRequestDTO(String code,int discount,Long valid) {
}
