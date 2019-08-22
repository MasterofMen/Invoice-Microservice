package com.company.CustomerService.Controller;

import com.company.CustomerService.Service.ServiceLayer;
import com.company.CustomerService.ViewModel.CustomerViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class CustomerController {
    @Autowired
    ServiceLayer serviceLayer;

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerViewModel createCustomer(@RequestBody CustomerViewModel customerViewModel){
        return serviceLayer.createCustomer(customerViewModel);
    }
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerViewModel> getAllCustomer(){
        return serviceLayer.getAllCustomer();
    }
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CustomerViewModel getCustomer(int id){
        return serviceLayer.getCustomer(id);
    }
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable int id){
        serviceLayer.deleteCustomer(id);
    }
    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@RequestBody CustomerViewModel customerViewModel){
        serviceLayer.updateCustomer(customerViewModel);
    }
}
