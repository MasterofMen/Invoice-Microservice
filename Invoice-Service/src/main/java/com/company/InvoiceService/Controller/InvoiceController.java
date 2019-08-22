package com.company.InvoiceService.Controller;

import com.company.InvoiceService.Service.ServiceLayer;
import com.company.InvoiceService.ViewModel.InvoiceItemViewModel;
import com.company.InvoiceService.ViewModel.InvoiceViewModel;
import com.company.InvoiceService.dto.Invoice;
import com.company.InvoiceService.dto.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class InvoiceController {
    @Autowired
    ServiceLayer serviceLayer;

    public InvoiceController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }
    //invoice
    //
    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody InvoiceViewModel invoiceViewModel){
        invoiceViewModel = serviceLayer.createInvoice(invoiceViewModel);
        return invoiceViewModel;
    }
    @RequestMapping(value = "/invoices", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateInvoice(@RequestBody InvoiceViewModel invoiceViewModel){
        serviceLayer.updateInvoice(invoiceViewModel);
    }
    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoice(){
        return serviceLayer.getAllInvoice();
    }
    @RequestMapping(value = "/invoices/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoice(@PathVariable int id){
        return serviceLayer.getInvoice(id);
    }
    @RequestMapping(value = "/invoices/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoice(@PathVariable int id){
        serviceLayer.deleteInvoice(id);
    }
    @RequestMapping(value = "/invoices/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getInvoiceByCustomerId(@PathVariable int id){
        return serviceLayer.getInvoicesByCustomerId(id);
    }
    //invoice item
    //
    @RequestMapping(value = "/invoiceItem", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceItemViewModel createInvoiceItem(@RequestBody InvoiceItemViewModel invoiceItemViewModel){
        return serviceLayer.createInvoiceItem(invoiceItemViewModel);
    }
    @RequestMapping(value = "/invoiceItem", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceItemViewModel> getAllInvoiceItem(){
        return serviceLayer.getAllInvoiceItem();
    }
    @RequestMapping(value = "/invoiceItem",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateInvoiceItem(@RequestBody InvoiceItemViewModel invoiceItemViewModel){
        serviceLayer.updateInvoiceItem(invoiceItemViewModel);
    }
    @RequestMapping(value = "/invoiceItem/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public InvoiceItemViewModel getInvoiceItem(@PathVariable int id){
        return serviceLayer.getInvoiceItem(id);
    }
    @RequestMapping(value = "/invoiceItem/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoiceItem(@PathVariable int id){
        serviceLayer.deleteInvoiceItem(id);
    }
}
