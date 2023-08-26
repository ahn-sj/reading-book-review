package com.example.infwalk.coupon;

import com.example.infwalk.Money;

import java.util.Objects;

public class PercentDiscountCoupon implements DiscountCoupon {

    private int discountPercent;

    public PercentDiscountCoupon(final int discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public int getDiscountAmount(final Money money) {
        int discountPrice = money.getValue() / 100 * discountPercent;
        return money.getValue() - discountPrice;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PercentDiscountCoupon that = (PercentDiscountCoupon) o;
        return discountPercent == that.discountPercent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountPercent);
    }
}
