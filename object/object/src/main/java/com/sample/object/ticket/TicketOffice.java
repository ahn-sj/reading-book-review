package com.sample.object.ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketOffice { // 매표소

    private Long amount;                              // 판매 금액
    private List<Ticket> tickets = new ArrayList<>(); // 티켓 목록

    public TicketOffice(Long amount, Ticket ... tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    public Ticket getTicket() {
        return tickets.remove(0);
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }
}
