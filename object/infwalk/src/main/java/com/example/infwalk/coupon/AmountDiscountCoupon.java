package com.example.infwalk.coupon;

import com.example.infwalk.Money;

import java.util.Objects;

public class AmountDiscountCoupon implements DiscountCoupon {

    private int discountAmount;

    public AmountDiscountCoupon(final int discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public int getDiscountAmount(final Money money) {
        return money.getValue() - discountAmount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AmountDiscountCoupon that = (AmountDiscountCoupon) o;
        return discountAmount == that.discountAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountAmount);
    }
}
