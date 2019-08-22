package com.company.InvoiceService.dao;

import com.company.InvoiceService.dto.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao{
    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException{
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(rs.getInt("invoice_id"));
        invoice.setCustomerId(rs.getInt("customer_id"));
        invoice.setPurchaseDate(rs.getDate("purchase_date").toLocalDate());
        return invoice;
    }
    JdbcTemplate jdbcTemplate;
    @Autowired
    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate newjdbcTemplate){
        this.jdbcTemplate = newjdbcTemplate;
    }
    private static final String Create_Invoice_SQL = "insert into invoice (customer_id, purchase_date) values (?,?)";
    private static final String Get_Invoice_SQL = "select * from invoice where invoice_id = ?";
    private static final String Update_Invoice_SQL = "update invoice set customer_id = ?, purchase_date = ? where invoice_id = ?";
    private static final String Delete_Invoice_SQL = "delete from invoice where invoice_id = ?";
    private static final String Get_All_Invoice_SQL = "select * from invoice";
    private static final String Get_All_By_Customer_Id = "select * from invoice where customer_id = ?";
    @Override
    @Transactional
    public Invoice createInvoice(Invoice invoice) {
        jdbcTemplate.update(Create_Invoice_SQL, invoice.getCustomerId(), invoice.getPurchaseDate());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        invoice.setInvoiceId(id);
        return invoice;
    }

    @Override
    public Invoice getInvoice(int id) {
        try {
            return jdbcTemplate.queryForObject(Get_Invoice_SQL, this::mapRowToInvoice, id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        jdbcTemplate.update(Update_Invoice_SQL, invoice.getCustomerId(), invoice.getPurchaseDate(), invoice.getInvoiceId());
        return jdbcTemplate.queryForObject(Get_Invoice_SQL, this::mapRowToInvoice, invoice.getInvoiceId());
    }

    @Override
    @Transactional
    public void deleteInvoice(int id) {
        jdbcTemplate.update(Delete_Invoice_SQL, id);
    }

    @Override
    public List<Invoice> getAllInvoice() {
        return jdbcTemplate.query(Get_All_Invoice_SQL, this::mapRowToInvoice);
    }

    @Override
    public List<Invoice> getInvoicesByCustomerId(int id) {
        return jdbcTemplate.query(Get_All_By_Customer_Id, this::mapRowToInvoice, id);
    }
}
