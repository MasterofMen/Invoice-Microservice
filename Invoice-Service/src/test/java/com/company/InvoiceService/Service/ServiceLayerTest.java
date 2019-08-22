package com.company.InvoiceService.Service;

import com.company.InvoiceService.ViewModel.InvoiceItemViewModel;
import com.company.InvoiceService.ViewModel.InvoiceViewModel;
import com.company.InvoiceService.dao.InvoiceDao;
import com.company.InvoiceService.dao.InvoiceDaoJdbcTemplateImpl;
import com.company.InvoiceService.dao.InvoiceItemDao;
import com.company.InvoiceService.dao.InvoiceItemDaoJdbcTemplateImpl;
import com.company.InvoiceService.dto.Invoice;
import com.company.InvoiceService.dto.InvoiceItem;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {
    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;
    ServiceLayer serviceLayer;

    @Before
    public void setUp() throws Exception {
        setUpInvoiceItemMock();
        setUpInvoiceMock();

        serviceLayer = new ServiceLayer(invoiceDao, invoiceItemDao);
    }

    @Test
    public void createGetInvoice() {
        InvoiceViewModel invoice2 = new InvoiceViewModel();
        invoice2.setCustomerId(5);
        invoice2.setPurchaseDate(LocalDate.of(2012, 8, 20));
        invoice2 = serviceLayer.createInvoice(invoice2);

        InvoiceViewModel invoice = serviceLayer.getInvoice(8);
        assertEquals(invoice, invoice2);
    }

    @Test
    public void updateInvoice() {
        InvoiceViewModel invoice2 = new InvoiceViewModel();
        invoice2.setCustomerId(5);
        invoice2.setPurchaseDate(LocalDate.of(2012, 8, 20));
        invoice2 = serviceLayer.createInvoice(invoice2);

        invoice2.setPurchaseDate(LocalDate.of(2012, 8, 25));
        InvoiceViewModel invoiceViewModel = serviceLayer.updateInvoice(invoice2);
        assertEquals(invoice2, invoiceViewModel);
    }

    @Test
    public void getAllInvoice() {
        InvoiceViewModel invoice2 = new InvoiceViewModel();
        invoice2.setCustomerId(5);
        invoice2.setPurchaseDate(LocalDate.of(2012, 8, 20));
        invoice2 = serviceLayer.createInvoice(invoice2);

        List<InvoiceViewModel> invList = serviceLayer.getAllInvoice();
        assertEquals(invList.size(), 1);
    }

    @Test
    public void getInvoicesByCustomerId() {
        InvoiceViewModel invoice2 = new InvoiceViewModel();
        invoice2.setCustomerId(5);
        invoice2.setPurchaseDate(LocalDate.of(2012, 8, 20));
        invoice2 = serviceLayer.createInvoice(invoice2);

        List<InvoiceViewModel> invList = serviceLayer.getInvoicesByCustomerId(5);
        assertEquals(invList.size(), 1);
    }

    @Test
    public void createGetInvoiceItem() {
        InvoiceItemViewModel invoiceItem2 = new InvoiceItemViewModel();
        invoiceItem2.setInvoiceId(8);
        invoiceItem2.setUnitPrice(20f);
        invoiceItem2.setQuantity(5);
        invoiceItem2.setInventoryId(5);
        invoiceItem2 = serviceLayer.createInvoiceItem(invoiceItem2);

        InvoiceItemViewModel invoiceItemViewModel = serviceLayer.getInvoiceItem(5);
        assertEquals(invoiceItem2, invoiceItemViewModel);
    }

    @Test
    public void updateInvoiceItem() {
        InvoiceItemViewModel invoiceItem2 = new InvoiceItemViewModel();
        invoiceItem2.setInvoiceId(8);
        invoiceItem2.setUnitPrice(20f);
        invoiceItem2.setQuantity(5);
        invoiceItem2.setInventoryId(5);
        invoiceItem2 = serviceLayer.createInvoiceItem(invoiceItem2);

        invoiceItem2.setQuantity(3);
        InvoiceItemViewModel invoiceItemViewModel = serviceLayer.updateInvoiceItem(invoiceItem2);
        assertEquals(invoiceItem2, invoiceItemViewModel);
    }

    @Test
    public void getAllInvoiceItem() {
        InvoiceItemViewModel invoiceItem2 = new InvoiceItemViewModel();
        invoiceItem2.setInvoiceId(8);
        invoiceItem2.setUnitPrice(20f);
        invoiceItem2.setQuantity(5);
        invoiceItem2.setInventoryId(5);
        invoiceItem2 = serviceLayer.createInvoiceItem(invoiceItem2);

        List<InvoiceItemViewModel> invList = serviceLayer.getAllInvoiceItem();
        assertEquals(invList.size(), 1);
    }

    private void setUpInvoiceMock(){
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(8);
        invoice.setCustomerId(5);
        invoice.setPurchaseDate(LocalDate.of(2012, 8, 20));

        Invoice invoice2 = new Invoice();
        invoice2.setCustomerId(5);
        invoice2.setPurchaseDate(LocalDate.of(2012, 8, 20));

        Invoice invoice3 = new Invoice();
        invoice3.setInvoiceId(8);
        invoice3.setCustomerId(5);
        invoice3.setPurchaseDate(LocalDate.of(2012, 8, 25));

        List<Invoice> invList = new ArrayList<>();
        invList.add(invoice);

        doReturn(invoice).when(invoiceDao).createInvoice(invoice2);
        doReturn(invoice).when(invoiceDao).getInvoice(8);
        doReturn(invoice3).when(invoiceDao).updateInvoice(invoice3);
        doReturn(invList).when(invoiceDao).getAllInvoice();
        doReturn(invList).when(invoiceDao).getInvoicesByCustomerId(5);
    }
    private void setUpInvoiceItemMock(){
        invoiceItemDao = mock(InvoiceItemDaoJdbcTemplateImpl.class);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(5);
        invoiceItem.setInvoiceId(8);
        invoiceItem.setUnitPrice(20f);
        invoiceItem.setQuantity(5);
        invoiceItem.setInventoryId(5);

        InvoiceItem invoiceItem2 = new InvoiceItem();
        invoiceItem2.setInvoiceId(8);
        invoiceItem2.setUnitPrice(20f);
        invoiceItem2.setQuantity(5);
        invoiceItem2.setInventoryId(5);

        InvoiceItem invoiceItem3 = new InvoiceItem();
        invoiceItem3.setInvoiceItemId(5);
        invoiceItem3.setInvoiceId(8);
        invoiceItem3.setUnitPrice(20f);
        invoiceItem3.setQuantity(3);
        invoiceItem3.setInventoryId(5);

        List<InvoiceItem> invList = new ArrayList<>();
        invList.add(invoiceItem);

        doReturn(invoiceItem).when(invoiceItemDao).createInvoiceItem(invoiceItem2);
        doReturn(invoiceItem).when(invoiceItemDao).getInvoiceItem(5);
        doReturn(invoiceItem3).when(invoiceItemDao).updateInvoiceItem(invoiceItem3);
        doReturn(invList).when(invoiceItemDao).getAllInvoiceItem();
    }
}