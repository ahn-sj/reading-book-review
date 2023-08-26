package com.example.infwalk;

import com.example.infwalk.coupon.DiscountCoupon;
import com.example.infwalk.coupon.NoneDiscountCoupon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Customer {
    private int customerId;
    private String name;
    private Money money;
    private List<Lecture> lectures = new ArrayList<>();
    private List<DiscountCoupon> coupons = new ArrayList<>();

    public Customer(final int customerId, final String name, final Money money, final DiscountCoupon... coupons) {
        this.customerId = customerId;
        this.name = name;
        this.money = money;
        this.coupons = new ArrayList<>(Arrays.asList(coupons));
    }

    /**
     * 단점: 강의만 구매 가능하다
     */
    public int pay(final Lecture lecture, final DiscountCoupon discountCoupon) {
        DiscountCoupon coupon = getCouponOrDefault(discountCoupon);
        int purchaseAmount = coupon.getDiscountAmount(lecture.getSalePrice()); // 20000

        coupons.remove(coupon);
        money.minus(purchaseAmount);
        lectures.add(lecture);

        return purchaseAmount;
    }

    public DiscountCoupon getCouponOrDefault(final DiscountCoupon condition) {
        return coupons.contains(condition) ? condition : new NoneDiscountCoupon();
    }

    public int getCustomerId() {
        return customerId;
    }

    public Money getMoney() {
        return money;
    }

    public int getCouponSize() {
        return coupons.size();
    }
}
