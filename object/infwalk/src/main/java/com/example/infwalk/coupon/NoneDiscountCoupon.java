package com.example.infwalk.coupon;

import com.example.infwalk.Money;

public class NoneDiscountCoupon implements DiscountCoupon {

    @Override
    public int getDiscountAmount(final Money money) {
        return money.getValue();
    }
}
