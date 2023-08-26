package com.example.infwalk.purchase;

import com.example.infwalk.Customer;
import com.example.infwalk.RetentionType;

public class Purchase {
    private int customerId;
    private int payPrice;
    private RetentionType retentionType;
    private PurchaseStatus purchaseStatus;

    public Purchase(final Customer customer, final int payPrice, final RetentionType retentionType) {
        this.customerId = customer.getCustomerId();
        this.payPrice = payPrice;
        this.retentionType = retentionType;
        this.purchaseStatus = PurchaseStatus.SUCCESS;
    }

    public int getPrice() {
        return payPrice;
    }
}
