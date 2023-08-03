package com.sample.object.ticket;

public class Audience { // 관람객

    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    public Long buy(Ticket ticket) {
        if(bag.isInvited()) { // 초대장을 받았을 경우 금액 차감 없이 티켓 획득
            bag.addTicket(ticket);
            return 0L;
        } else {              // 초대장을 받지 않았을 경우 금액 차감 후 티켓 획득
            bag.addTicket(ticket);
            bag.minusAmount(ticket.getFee());
            return ticket.getFee();
        }
    }
}
