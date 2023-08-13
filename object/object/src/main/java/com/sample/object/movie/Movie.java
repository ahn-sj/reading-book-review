package com.sample.object.movie;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountPolicyImpl> discountConditions = new ArrayList<>();

    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    public Movie(final String title, final Duration runningTime, final Money fee, final DiscountPolicyImpl discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee() {
        return fee;
    }

    public Money calculateMovieFee(Screening screening) {
        if(isDiscountable(screening)) {
            return fee.minus(calculateDiscountAmount());
        }
        return fee;
        // return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }

    private Money calculateDiscountAmount() {
        switch (movieType) {
            case AMOUNT_DISCOUNT:
                return calculateAmountDiscountAmount();
            case PERCENT_DISCOUNT:
                return calculatePercentDiscountAmount();
            case NONE_DISCOUNT:
                return calculateNoneDiscountAmount();
        }
        throw new IllegalStateException();
    }

    private Money calculateNoneDiscountAmount() {
        return Money.ZERO;
    }

    private Money calculatePercentDiscountAmount() {
        return fee.times(discountPercent);
    }

    private Money calculateAmountDiscountAmount() {
        return discountAmount;
    }

    private boolean isDiscountable(final Screening screening) {
        return discountConditions.stream()
                .anyMatch(condition -> condition.isSatisfiedBy(screening));
    }
}
