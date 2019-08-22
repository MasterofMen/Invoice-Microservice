package com.company.CustomerService.dao;

import com.company.CustomerService.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao {
    private Customer mapCustomerToRow(ResultSet rs, int rowNum) throws SQLException{
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setStreet(rs.getString("street"));
        customer.setCity(rs.getString("city"));
        customer.setZip(rs.getString("zip"));
        customer.setEmail(rs.getString("email"));
        customer.setPhone(rs.getString("phone"));
        return customer;
    }
    JdbcTemplate jdbcTemplate;
    @Autowired
    public CustomerDaoJdbcTemplateImpl(JdbcTemplate newjdbcTemplate){
        this.jdbcTemplate = newjdbcTemplate;
    }

    private static final String Create_Customer_SQL = "insert into customer (first_name, last_name, street, city, zip, email, phone) values (?,?,?,?,?,?,?)";
    private static final String Get_Customer_SQL = "select * from customer where customer_id = ?";
    private static final String Update_Customer_SQL = "update customer set first_name = ?, last_name = ?, street = ?, city = ?, zip = ?, email = ?, phone = ? where customer_id = ?";
    private static final String Get_All_Customer_SQL = "select * from customer";
    private static final String Delete_Customer_SQL = "delete from customer where customer_id = ?";

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        jdbcTemplate.update(Create_Customer_SQL, customer.getFirstName(), customer.getLastName(), customer.getStreet(), customer.getCity(),
                customer.getZip(), customer.getEmail(), customer.getPhone());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        customer.setCustomerId(id);
        return customer;
    }

    @Override
    public Customer getCustomer(int id) {
        try {
            return jdbcTemplate.queryForObject(Get_Customer_SQL, this::mapCustomerToRow, id);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        jdbcTemplate.update(Update_Customer_SQL, customer.getFirstName(), customer.getLastName(), customer.getStreet(), customer.getCity(),
                customer.getZip(), customer.getEmail(), customer.getPhone(), customer.getCustomerId());
        return jdbcTemplate.queryForObject(Get_Customer_SQL, this::mapCustomerToRow, customer.getCustomerId());
    }

    @Override
    public List<Customer> getAllCustomer() {
        return jdbcTemplate.query(Get_All_Customer_SQL, this::mapCustomerToRow);
    }

    @Override
    @Transactional
    public void deleteCustomer(int id) {
        jdbcTemplate.update(Delete_Customer_SQL, id);
    }
}
