package com.events.api.domain.coupon;

import java.util.Date;

public record CouponDTO(String code, int discount, Date valid){}
