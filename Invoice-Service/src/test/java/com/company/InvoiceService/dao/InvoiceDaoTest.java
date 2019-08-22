package com.company.InvoiceService.dao;

import com.company.InvoiceService.dto.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {
    @Autowired
    InvoiceDao invoiceDao;

    @Before
    public void setUp() throws Exception {
        List<Invoice> invList = invoiceDao.getAllInvoice();

        invList.stream().forEach(x -> invoiceDao.deleteInvoice(x.getInvoiceId()));
    }

    @Test
    public void createGetInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2018, 5, 8));
        invoice = invoiceDao.createInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());
        assertEquals(invoice, invoice1);
    }

    @Test
    public void updateInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2018, 5, 8));
        invoice = invoiceDao.createInvoice(invoice);

        invoice.setPurchaseDate(LocalDate.of(2018, 6, 10));
        Invoice invoice1 = invoiceDao.updateInvoice(invoice);
        assertEquals(invoice, invoice1);
    }

    @Test
    public void deleteInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2018, 5, 8));
        invoice = invoiceDao.createInvoice(invoice);

        invoiceDao.deleteInvoice(invoice.getInvoiceId());
        invoice = invoiceDao.getInvoice(invoice.getInvoiceId());
        assertNull(invoice);
    }

    @Test
    public void getAllInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2018, 5, 8));
        invoice = invoiceDao.createInvoice(invoice);

        List<Invoice> invList = invoiceDao.getAllInvoice();
        assertEquals(invList.size(), 1);
    }

    @Test
    public void getInvoicesByCustomerId() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2018, 5, 8));
        invoice = invoiceDao.createInvoice(invoice);

        List<Invoice> invList = invoiceDao.getInvoicesByCustomerId(1);
        assertEquals(invList.size(), 1);
    }
}