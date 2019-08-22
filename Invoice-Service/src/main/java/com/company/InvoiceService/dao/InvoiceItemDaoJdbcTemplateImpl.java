package com.company.InvoiceService.dao;

import com.company.InvoiceService.dto.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceItemDaoJdbcTemplateImpl implements InvoiceItemDao{
    private InvoiceItem mapRowToInvoiceItem(ResultSet rs, int rowNum) throws SQLException{
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(rs.getInt("invoice_item_id"));
        invoiceItem.setInvoiceId(rs.getInt("invoice_id"));
        invoiceItem.setInventoryId(rs.getInt("inventory_id"));
        invoiceItem.setQuantity(rs.getInt("quantity"));
        invoiceItem.setUnitPrice(rs.getFloat("unit_price"));
        return invoiceItem;
    }
    JdbcTemplate jdbcTemplate;
    @Autowired
    public InvoiceItemDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final String Create_Invoice_Item = "insert into invoice_item (invoice_id, inventory_id, quantity, unit_price) values (?,?,?,?)";
    private static final String Get_Invoice_Item = "select * from invoice_item where invoice_item_id = ?";
    private static final String Update_Invoice_Item = "update invoice_item set invoice_id = ?, inventory_id = ?, quantity = ?, unit_price = ? where invoice_item_id = ?";
    private static final String Get_All_Invoice_Item = "select * from invoice_item";
    private static final String Delete_Invoice_Item = "delete from invoice_item where invoice_item_id = ?";

    @Override
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(Create_Invoice_Item, invoiceItem.getInvoiceId(), invoiceItem.getInventoryId(), invoiceItem.getQuantity(), invoiceItem.getUnitPrice());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        invoiceItem.setInvoiceItemId(id);
        return invoiceItem;
    }

    @Override
    public InvoiceItem getInvoiceItem(int id) {
        try {
            return jdbcTemplate.queryForObject(Get_Invoice_Item, this::mapRowToInvoiceItem, id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public InvoiceItem updateInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(Update_Invoice_Item, invoiceItem.getInvoiceId(), invoiceItem.getInventoryId(), invoiceItem.getQuantity(), invoiceItem.getUnitPrice(), invoiceItem.getInvoiceItemId());
        return jdbcTemplate.queryForObject(Get_Invoice_Item, this::mapRowToInvoiceItem, invoiceItem.getInvoiceItemId());
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItem() {
        return jdbcTemplate.query(Get_All_Invoice_Item, this::mapRowToInvoiceItem);
    }

    @Override
    public void deleteInvoiceItem(int id) {
        jdbcTemplate.update(Delete_Invoice_Item, id);
    }
}
