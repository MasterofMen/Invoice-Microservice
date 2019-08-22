package com.company.InvoiceService.dao;

import com.company.InvoiceService.dto.Invoice;

import java.util.List;

public interface InvoiceDao {
    Invoice createInvoice(Invoice invoice);
    Invoice getInvoice(int id);
    Invoice updateInvoice(Invoice invoice);
    void deleteInvoice(int id);
    List<Invoice> getAllInvoice();
    List<Invoice> getInvoicesByCustomerId(int id);
}
