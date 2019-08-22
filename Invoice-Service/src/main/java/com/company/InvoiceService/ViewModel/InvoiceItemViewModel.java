package com.company.InvoiceService.ViewModel;

import com.company.InvoiceService.dto.Invoice;
import com.company.InvoiceService.dto.InvoiceItem;

import java.util.Objects;

public class InvoiceItemViewModel {
    private int invoiceItemId;
    private int invoiceId;
    private int inventoryId;
    private int quantity;
    private float unitPrice;
    //private Invoice invoice;

//    public Invoice getInvoice() {
//        return invoice;
//    }
//
//    public void setInvoice(Invoice invoice) {
//        this.invoice = invoice;
//    }

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
        InvoiceItemViewModel invoiceItem = (InvoiceItemViewModel) o;
        return getInvoiceId() == invoiceItem.getInvoiceId() &&
                getInvoiceItemId() == invoiceItem.getInvoiceItemId() &&
                getInventoryId() == invoiceItem.getInventoryId() &&
                getQuantity() == invoiceItem.getQuantity() &&
                getUnitPrice() == invoiceItem.getUnitPrice();// &&
                //Objects.equals(getInvoice(), invoiceItem.getInvoice());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getInvoiceItemId(), getInventoryId(), getQuantity(), getUnitPrice());//, getInvoice());
    }
}
