package com.example.infwalk;

public class ThreeThousandCoupon implements DiscountCoupon {

    private static final int DISCOUNT = 3_000;

    @Override
    public int calculatePriceBy(final Money money) {
        return money.getValue() - DISCOUNT;
    }
}
