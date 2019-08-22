package com.company.CustomerService.dao;

import com.company.CustomerService.dto.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoTest {
    @Autowired
    CustomerDao dao;

    @Before
    public void setUp() throws Exception {
        List<Customer> customerList = dao.getAllCustomer();

        customerList.stream().forEach(x -> dao.deleteCustomer(x.getCustomerId()));
    }

    @Test
    public void createGetCustomer() {
        Customer customer = new Customer();
        customer.setCity("New York");
        customer.setEmail("haha@live.com");
        customer.setFirstName("Bob");
        customer.setLastName("Nye");
        customer.setPhone("123-456-7890");
        customer.setStreet("Highway 35");
        customer.setZip("12345");
        customer = dao.createCustomer(customer);

        Customer customer1 = dao.getCustomer(customer.getCustomerId());
        assertEquals(customer, customer1);
    }

    @Test
    public void updateCustomer() {
        Customer customer = new Customer();
        customer.setCity("New York");
        customer.setEmail("haha@live.com");
        customer.setFirstName("Bob");
        customer.setLastName("Nye");
        customer.setPhone("123-456-7890");
        customer.setStreet("Highway 35");
        customer.setZip("12345");
        customer = dao.createCustomer(customer);

        customer.setStreet("Highway 36");
        Customer customer1 = dao.updateCustomer(customer);
        assertEquals(customer, customer1);
    }

    @Test
    public void deleteCustomer() {
        Customer customer = new Customer();
        customer.setCity("New York");
        customer.setEmail("haha@live.com");
        customer.setFirstName("Bob");
        customer.setLastName("Nye");
        customer.setPhone("123-456-7890");
        customer.setStreet("Highway 35");
        customer.setZip("12345");

        customer = dao.createCustomer(customer);
        dao.deleteCustomer(customer.getCustomerId());
        customer = dao.getCustomer(customer.getCustomerId());
        assertNull(customer);
    }

    @Test
    public void getAllCustomer() {
        Customer customer = new Customer();
        customer.setCity("New York");
        customer.setEmail("haha@live.com");
        customer.setFirstName("Bob");
        customer.setLastName("Nye");
        customer.setPhone("123-456-7890");
        customer.setStreet("Highway 35");
        customer.setZip("12345");
        customer = dao.createCustomer(customer);

        List<Customer> cList = dao.getAllCustomer();
        assertEquals(cList.size(), 1);
    }
}