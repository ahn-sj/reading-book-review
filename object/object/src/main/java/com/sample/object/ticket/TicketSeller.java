package com.sample.object.ticket;

public class TicketSeller { // 매표소 판매원
    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    public void sellTo(Audience audience) {
        Ticket ticket = ticketOffice.getTicket();
        Long feePrice = audience.buy(ticket);

        ticketOffice.plusAmount(feePrice);
    }
}
