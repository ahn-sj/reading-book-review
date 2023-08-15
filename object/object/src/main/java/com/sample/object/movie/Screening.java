package com.sample.object.movie;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie;                // 영화
    private int sequence;               // 상영 순번
    private LocalDateTime whenScreened; // 상영 시간

    public Screening(final Movie movie, final int sequence, final LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public Reservation reserve(Customer customer, int audienceCount) {
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    public Money getMovieFee() {
        return movie.getFee();
    }

    public LocalDateTime getStartTime() {
        return whenScreened;
    }

    private Money calculateFee(final int audienceCount) { // Screening -> Movie: 가격을 계산하라.
        return movie.calculateMovieFee(this).times(audienceCount);
    }

    public LocalDateTime getWhenScreened() {
        return whenScreened;
    }

    public int getSequence() {
        return sequence;
    }
}
