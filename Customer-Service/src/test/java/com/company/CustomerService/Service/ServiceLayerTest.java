package com.company.CustomerService.Service;

import com.company.CustomerService.ViewModel.CustomerViewModel;
import com.company.CustomerService.dao.CustomerDao;
import com.company.CustomerService.dao.CustomerDaoJdbcTemplateImpl;
import com.company.CustomerService.dto.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {
    CustomerDao dao;
    ServiceLayer serviceLayer;

    @Before
    public void setUp() throws Exception {
        setUpCustomerMockDao();

        serviceLayer = new ServiceLayer(dao);
    }

    @Test
    public void createGetCustomer() {
        CustomerViewModel customer2 = new CustomerViewModel();
        customer2.setFirstName("Sean");
        customer2.setLastName("Grano");
        customer2.setCity("New York");
        customer2.setPhone("123-456-7890");
        customer2.setEmail("haha@live.com");
        customer2.setZip("12345");
        customer2.setStreet("Highway 34");
        customer2 = serviceLayer.createCustomer(customer2);

        CustomerViewModel customer = serviceLayer.getCustomer(5);
        assertEquals(customer2, customer);
    }

    @Test
    public void updateCustomer() {
        CustomerViewModel customer2 = new CustomerViewModel();
        customer2.setFirstName("Sean");
        customer2.setLastName("Grano");
        customer2.setCity("New York");
        customer2.setPhone("123-456-7890");
        customer2.setEmail("haha@live.com");
        customer2.setZip("12345");
        customer2.setStreet("Highway 34");
        customer2 = serviceLayer.createCustomer(customer2);

        customer2.setStreet("Highway 35");
        CustomerViewModel customer = serviceLayer.updateCustomer(customer2);
        assertEquals(customer, customer2);
    }

    @Test
    public void getAllCustomer() {
        CustomerViewModel customer2 = new CustomerViewModel();
        customer2.setCustomerId(5);
        customer2.setFirstName("Sean");
        customer2.setLastName("Grano");
        customer2.setCity("New York");
        customer2.setPhone("123-456-7890");
        customer2.setEmail("haha@live.com");
        customer2.setZip("12345");
        customer2.setStreet("Highway 34");
        customer2 = serviceLayer.createCustomer(customer2);

        List<CustomerViewModel> cList = serviceLayer.getAllCustomer();
        assertEquals(cList.size(), 1);
    }
    private void setUpCustomerMockDao(){
        dao = mock(CustomerDaoJdbcTemplateImpl.class);

        Customer customer = new Customer();
        customer.setCustomerId(5);
        customer.setFirstName("Sean");
        customer.setLastName("Grano");
        customer.setCity("New York");
        customer.setPhone("123-456-7890");
        customer.setEmail("haha@live.com");
        customer.setZip("12345");
        customer.setStreet("Highway 34");

        Customer customer2 = new Customer();
        customer2.setFirstName("Sean");
        customer2.setLastName("Grano");
        customer2.setCity("New York");
        customer2.setPhone("123-456-7890");
        customer2.setEmail("haha@live.com");
        customer2.setZip("12345");
        customer2.setStreet("Highway 34");

        Customer uCustomer = new Customer();
        uCustomer.setCustomerId(5);
        uCustomer.setFirstName("Sean");
        uCustomer.setLastName("Grano");
        uCustomer.setCity("New York");
        uCustomer.setPhone("123-456-7890");
        uCustomer.setEmail("haha@live.com");
        uCustomer.setZip("12345");
        uCustomer.setStreet("Highway 35");

        List<Customer> cList = new ArrayList<>();
        cList.add(customer);

        doReturn(customer).when(dao).createCustomer(customer2);
        doReturn(customer).when(dao).getCustomer(5);
        doReturn(uCustomer).when(dao).updateCustomer(uCustomer);
        doReturn(cList).when(dao).getAllCustomer();
    }
}