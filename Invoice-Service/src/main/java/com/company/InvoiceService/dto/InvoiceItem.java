package com.company.InvoiceService.dto;

import java.util.Objects;

public class InvoiceItem {
    private int invoiceItemId;
    private int invoiceId;
    private int inventoryId;
    private int quantity;
    private float unitPrice;

    public int getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(int invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem invoiceItem = (InvoiceItem) o;
        return getInvoiceId() == invoiceItem.getInvoiceId() &&
                getInvoiceItemId() == invoiceItem.getInvoiceItemId() &&
                getInventoryId() == invoiceItem.getInventoryId() &&
                getQuantity() == invoiceItem.getQuantity() &&
                getUnitPrice() == invoiceItem.getUnitPrice();
    }
    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getInvoiceItemId(), getInventoryId(), getQuantity(), getUnitPrice());
    }
}
