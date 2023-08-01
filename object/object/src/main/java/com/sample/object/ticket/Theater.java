package com.sample.object.ticket;

public class Theater {
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    public void enter(Audience audience) {
        if(audience.getBag().isInvited()) { // 초대장을 받았을 경우 금액 차감 없이 티켓 획득
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().addTicket(ticket);
        } else {                            // 초대장을 받지 않았을 경우 금액 차감 후 티켓 획득
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();

            audience.getBag().minusAmount(ticket.getFee());
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee());

            audience.getBag().addTicket(ticket);
        }
    }
}
