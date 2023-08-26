package com.example.infwalk;

import com.example.infwalk.coupon.DiscountCoupon;
import com.example.infwalk.purchase.Purchase;

public class Lecture {

    private String lectureName;
    private int saleCount;
    private int walkerId;
    private RetentionType retentionType;
    private Money salePrice;

    public Lecture(final String lectureName, final int walkerId, final RetentionType retentionType, final Money salePrice) {
        this.lectureName = lectureName;
        this.walkerId = walkerId;
        this.retentionType = retentionType;
        this.salePrice = salePrice;
    }

    public Purchase buy(Customer customer, DiscountCoupon condition) {
        int payPrice = customer.pay(this, condition);
        addSaleCount();
        return new Purchase(customer, payPrice, retentionType);
    }

    public void addSaleCount() {
        saleCount++;
    }

    public Money getSalePrice() {
        return salePrice;
    }
}
