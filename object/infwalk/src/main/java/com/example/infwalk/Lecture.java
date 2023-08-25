package com.example.infwalk;

public class Lecture {

    private String lectureName;
    private int saleCount;
    private int walkerId;
    private Money money;

    public Lecture(final String lectureName, final int saleCount, final int walkerId, final int price) {
        this.lectureName = lectureName;
        this.saleCount = saleCount;
        this.walkerId = walkerId;
        this.price = price;
    }

    public Purchase buy(Customer customer, DiscountCoupon condition) {
        if(!customer.hasCoupon(condition)) {
            throw new IllegalArgumentException("사용자는 쿠폰을 가지지 않았습니다.");
        }
        int price = condition.calculatePriceBy(money);

    }
}
