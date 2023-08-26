package com.example.infwalk;

import java.util.Objects;

public class Money {

    private int value;

    public Money(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void minus(final int discountAmount) {
        int result = value - discountAmount;
        if(result < 0) {
            throw new IllegalArgumentException("금액 부족으로 결제 실패하였습니다.");
        }
        this.value = result;
    }
}
