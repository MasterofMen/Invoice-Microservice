package com.company.InvoiceService.dao;

import com.company.InvoiceService.dto.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {
    InvoiceItem createInvoiceItem(InvoiceItem invoiceItem);
    InvoiceItem getInvoiceItem(int id);
    InvoiceItem updateInvoiceItem(InvoiceItem invoiceItem);
    void deleteInvoiceItem(int id);
    List<InvoiceItem> getAllInvoiceItem();
}
