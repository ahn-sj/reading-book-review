package com.example.infwalk;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private Money money;
    private List<DiscountCoupon> coupons = new ArrayList<>();

    public boolean hasCoupon(final DiscountCoupon couponCondition) {
        return coupons.stream().anyMatch(coupon -> coupon.equals(couponCondition));
    }
}
