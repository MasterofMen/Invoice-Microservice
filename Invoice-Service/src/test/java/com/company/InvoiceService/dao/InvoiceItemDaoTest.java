package com.company.InvoiceService.dao;

import com.company.InvoiceService.dto.Invoice;
import com.company.InvoiceService.dto.InvoiceItem;
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
public class InvoiceItemDaoTest {
    @Autowired
    InvoiceItemDao invoiceItemDao;
    @Autowired
    InvoiceDao invoiceDao;

    @Before
    public void setUp() throws Exception {

        List<InvoiceItem> itemList = invoiceItemDao.getAllInvoiceItem();

        itemList.stream().forEach(x -> invoiceItemDao.deleteInvoiceItem(x.getInvoiceItemId()));

        List<Invoice> invList = invoiceDao.getAllInvoice();

        invList.stream().forEach(x -> invoiceDao.deleteInvoice(x.getInvoiceId()));
    }

    @Test
    public void createGetInvoiceItem() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(5);
        invoice.setPurchaseDate(LocalDate.of(2012, 8, 20));
        invoice = invoiceDao.createInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setUnitPrice(12.50f);
        invoiceItem.setInventoryId(5);
        invoiceItem.setQuantity(5);
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());
        assertEquals(invoiceItem, invoiceItem1);
    }


    @Test
    public void updateInvoiceItem() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(5);
        invoice.setPurchaseDate(LocalDate.of(2012, 8, 20));
        invoice = invoiceDao.createInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setUnitPrice(12.50f);
        invoiceItem.setInventoryId(5);
        invoiceItem.setQuantity(5);
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        invoiceItem.setQuantity(3);
        InvoiceItem invoiceItem1 = invoiceItemDao.updateInvoiceItem(invoiceItem);
        assertEquals(invoiceItem, invoiceItem1);
    }

    @Test
    public void deleteInvoiceItem() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(5);
        invoice.setPurchaseDate(LocalDate.of(2012, 8, 20));
        invoice = invoiceDao.createInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setUnitPrice(12.50f);
        invoiceItem.setInventoryId(5);
        invoiceItem.setQuantity(5);
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId());
        invoiceItem = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());
        assertNull(invoiceItem);
    }

    @Test
    public void getAllInvoiceItem() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(5);
        invoice.setPurchaseDate(LocalDate.of(2012, 8, 20));
        invoice = invoiceDao.createInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setUnitPrice(12.50f);
        invoiceItem.setInventoryId(5);
        invoiceItem.setQuantity(5);
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        List<InvoiceItem> invList = invoiceItemDao.getAllInvoiceItem();
        assertEquals(invList.size(), 1);
    }
}