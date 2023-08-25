package com.example.infwalk;

public class Purchase {
    private PurchaseType ticketType;
    private PurchaseStatus ticketStatus;

    public void pay(Customer customer, PurchaseType type) {
        PurchaseType ticketType = type.getTicketType(type);
        customer.calculateDiscount(ticketType);
    }
}
