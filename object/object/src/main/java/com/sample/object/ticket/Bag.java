package com.sample.object.ticket;

public class Bag {

    private Long amount;           // 현금
    private Invitation invitation; // 초대장
    private Ticket ticket;         // 티켓

    public Bag(Long amount) {      // 초대장을 받지 못한 사람
        this(null, amount);
    }

    public Bag(Invitation invitation, Long amount) { // 초대장을 받은 사람
        this.invitation = invitation;
        this.amount = amount;
    }

    public void addTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public boolean isInvited() {
        return invitation != null;
    }

    public boolean hasTicket() {
        return ticket != null;
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }

}
