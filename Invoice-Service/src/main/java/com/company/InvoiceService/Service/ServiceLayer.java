package com.company.InvoiceService.Service;

import com.company.InvoiceService.ViewModel.InvoiceItemViewModel;
import com.company.InvoiceService.ViewModel.InvoiceViewModel;
import com.company.InvoiceService.dao.InvoiceDao;
import com.company.InvoiceService.dao.InvoiceItemDao;
import com.company.InvoiceService.dto.Invoice;
import com.company.InvoiceService.dto.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    InvoiceItemDao invoiceItemDao;

    @Autowired
    public ServiceLayer(InvoiceDao invoiceDao, InvoiceItemDao invoiceItemDao) {
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
    }
    //invoice api
    //
    @Transactional
    public InvoiceViewModel createInvoice(InvoiceViewModel invoice){
        Invoice invoice1 = new Invoice();
        invoice1.setPurchaseDate(invoice.getPurchaseDate());
        invoice1.setCustomerId(invoice.getCustomerId());
        invoice1 = invoiceDao.createInvoice(invoice1);

        invoice.setInvoiceId(invoice1.getInvoiceId());
        return invoice;
    }
    public InvoiceViewModel getInvoice(int id){
        return buildInvoiceViewModel(invoiceDao.getInvoice(id));
    }
    public InvoiceViewModel updateInvoice(InvoiceViewModel invoice){
        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceId(invoice.getInvoiceId());
        invoice1.setPurchaseDate(invoice.getPurchaseDate());
        invoice1.setCustomerId(invoice.getCustomerId());

        invoiceDao.updateInvoice(invoice1);
        return invoice;
    }
    @Transactional
    public void deleteInvoice(int id){
        invoiceDao.deleteInvoice(id);
    }
    public List<InvoiceViewModel> getAllInvoice() {
        List<Invoice> invList = invoiceDao.getAllInvoice();
        List<InvoiceViewModel> viewList = new ArrayList<>();
        for(Invoice invoice: invList){
            viewList.add(buildInvoiceViewModel(invoice));
        }
        return viewList;
    }
    public List<InvoiceViewModel> getInvoicesByCustomerId(int id){
        List<Invoice> invList = invoiceDao.getInvoicesByCustomerId(id);
        List<InvoiceViewModel> viewList = new ArrayList<>();
        for(Invoice invoice: invList){
            viewList.add(buildInvoiceViewModel(invoice));
        }
        return viewList;
    }
    //invoice item api
    //
    @Transactional
    public InvoiceItemViewModel createInvoiceItem(InvoiceItemViewModel invoiceItem){
        InvoiceItem invoiceItem1 = new InvoiceItem();
        invoiceItem1.setInventoryId(invoiceItem.getInventoryId());
        invoiceItem1.setQuantity(invoiceItem.getQuantity());
        invoiceItem1.setUnitPrice(invoiceItem.getUnitPrice());
        invoiceItem1.setInvoiceId(invoiceItem.getInvoiceId());
        invoiceItem1 = invoiceItemDao.createInvoiceItem(invoiceItem1);

        invoiceItem.setInvoiceItemId(invoiceItem1.getInvoiceItemId());
        return invoiceItem;
    }
    public InvoiceItemViewModel getInvoiceItem(int id){
        return buildInvoiceItemViewModel(invoiceItemDao.getInvoiceItem(id));
    }
    public InvoiceItemViewModel updateInvoiceItem(InvoiceItemViewModel invoiceItem){
        InvoiceItem invoiceItem1 = new InvoiceItem();
        invoiceItem1.setInvoiceItemId(invoiceItem.getInvoiceItemId());
        invoiceItem1.setInventoryId(invoiceItem.getInventoryId());
        invoiceItem1.setQuantity(invoiceItem.getQuantity());
        invoiceItem1.setUnitPrice(invoiceItem.getUnitPrice());
        invoiceItem1.setInvoiceId(invoiceItem.getInvoiceId());
        invoiceItemDao.updateInvoiceItem(invoiceItem1);
        return invoiceItem;
    }
    @Transactional
    public void deleteInvoiceItem(int id){
        invoiceItemDao.deleteInvoiceItem(id);
    }
    public List<InvoiceItemViewModel> getAllInvoiceItem(){
        List<InvoiceItem> invList = invoiceItemDao.getAllInvoiceItem();
        List<InvoiceItemViewModel> viewList = new ArrayList<>();
        for(InvoiceItem invoiceItem: invList){
            viewList.add(buildInvoiceItemViewModel(invoiceItem));
        }
        return viewList;
    }
    //build invoice view model
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice){
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setInvoiceId(invoice.getInvoiceId());
        invoiceViewModel.setPurchaseDate(invoice.getPurchaseDate());
        invoiceViewModel.setCustomerId(invoice.getCustomerId());
        return invoiceViewModel;
    }
    //build invoice item view model
    private InvoiceItemViewModel buildInvoiceItemViewModel(InvoiceItem invoiceItem){
        InvoiceItemViewModel invoiceItemViewModel = new InvoiceItemViewModel();
        invoiceItemViewModel.setInvoiceItemId(invoiceItem.getInvoiceItemId());
        invoiceItemViewModel.setQuantity(invoiceItem.getQuantity());
        invoiceItemViewModel.setInventoryId(invoiceItem.getInventoryId());
        invoiceItemViewModel.setInvoiceId(invoiceItem.getInvoiceId());
        invoiceItemViewModel.setUnitPrice(invoiceItem.getUnitPrice());
        return invoiceItemViewModel;
    }
}
