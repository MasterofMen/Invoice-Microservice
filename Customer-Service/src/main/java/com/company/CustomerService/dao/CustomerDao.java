package com.company.CustomerService.dao;

import com.company.CustomerService.dto.Customer;

import java.util.List;

public interface CustomerDao {
    Customer createCustomer(Customer customer);
    Customer getCustomer(int id);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(int id);
    List<Customer> getAllCustomer();
}
