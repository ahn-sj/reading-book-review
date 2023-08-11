package com.sample.object.movie;

public class Reservation {
    private Customer customer;
    private Screening Screening;
    private Money fee;
    private int audienceCount;

    public Reservation(final Customer customer, final Screening screening, final Money fee, final int audienceCount) {
        this.customer = customer;
        Screening = screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }
}
