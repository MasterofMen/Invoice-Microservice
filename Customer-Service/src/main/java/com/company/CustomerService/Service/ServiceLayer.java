package com.company.CustomerService.Service;

import com.company.CustomerService.ViewModel.CustomerViewModel;
import com.company.CustomerService.dao.CustomerDao;
import com.company.CustomerService.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {
    @Autowired
    CustomerDao customerDao;

    @Autowired
    public ServiceLayer(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    public CustomerViewModel createCustomer(CustomerViewModel customer){
        Customer customer1 = new Customer();
        customer1.setStreet(customer.getStreet());
        customer1.setZip(customer.getZip());
        customer1.setEmail(customer.getEmail());
        customer1.setPhone(customer.getPhone());
        customer1.setCity(customer.getCity());
        customer1.setLastName(customer.getLastName());
        customer1.setFirstName(customer.getFirstName());
        customer1 = customerDao.createCustomer(customer1);

        customer.setCustomerId(customer1.getCustomerId());
        return customer;
    }
    public CustomerViewModel getCustomer(int id){
        return buildCustomerViewModel(customerDao.getCustomer(id));
    }
    public CustomerViewModel updateCustomer(CustomerViewModel customer){
        Customer customer1 = new Customer();
        customer1.setCustomerId(customer.getCustomerId());
        customer1.setStreet(customer.getStreet());
        customer1.setZip(customer.getZip());
        customer1.setEmail(customer.getEmail());
        customer1.setPhone(customer.getPhone());
        customer1.setCity(customer.getCity());
        customer1.setLastName(customer.getLastName());
        customer1.setFirstName(customer.getFirstName());

        customerDao.updateCustomer(customer1);
        return customer;
    }
    public void deleteCustomer(int id){
        customerDao.deleteCustomer(id);
    }
    public List<CustomerViewModel> getAllCustomer(){
        List<Customer> clist = customerDao.getAllCustomer();
        List<CustomerViewModel> viewList = new ArrayList<>();
        for(Customer customer: clist){
            viewList.add(buildCustomerViewModel(customer));
        }
        return viewList;
    }
    private CustomerViewModel buildCustomerViewModel(Customer customer){
        CustomerViewModel customerViewModel = new CustomerViewModel();
        customerViewModel.setCustomerId(customer.getCustomerId());
        customerViewModel.setFirstName(customer.getFirstName());
        customerViewModel.setLastName(customer.getLastName());
        customerViewModel.setCity(customer.getCity());
        customerViewModel.setEmail(customer.getEmail());
        customerViewModel.setPhone(customer.getPhone());
        customerViewModel.setStreet(customer.getStreet());
        customerViewModel.setZip(customer.getZip());
        return customerViewModel;
    }
}
