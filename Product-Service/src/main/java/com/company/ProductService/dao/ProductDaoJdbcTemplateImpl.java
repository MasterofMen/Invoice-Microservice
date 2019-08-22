package com.company.ProductService.dao;

import com.company.ProductService.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDaoJdbcTemplateImpl implements ProductDao{
    private Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException{
        Product product = new Product();
        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));
        product.setProductDescription(rs.getString("product_description"));
        product.setListPrice(rs.getFloat("list_price"));
        product.setUnitCost(rs.getFloat("unit_cost"));
        return product;
    }
    JdbcTemplate jdbcTemplate;
    @Autowired
    public ProductDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final String Create_Product = "";
    private static final String Get_Product = "";
    private static final String Update_Product = "";
    private static final String Delete_Product = "";
    private static final String Get_All_Product = "";
    private static final String Get_Product_By_Invoice_Id = "";
    private static final String Get_Product_In_Inventory = "";

    @Override
    public Product createProduct(Product product) {
        jdbcTemplate.update(Create_Product, product.getProductName(), product.getProductDescription(), product.getListPrice(), product.getUnitCost());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        product.setProductId(id);
        return product;
    }

    @Override
    public Product getProduct(int id) {
        try {
            return jdbcTemplate.queryForObject(Get_Product, this::mapRowToProduct, id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Product updateProduct(Product product) {
        jdbcTemplate.update(Update_Product, product.getProductName(), product.getProductDescription(), product.getListPrice(), product.getUnitCost(), product.getProductId());
        return jdbcTemplate.queryForObject(Get_Product, this::mapRowToProduct, product.getProductId());
    }

    @Override
    public void deleteProduct(int id) {
        jdbcTemplate.update(Delete_Product, id);
    }

    @Override
    public List<Product> getAllProduct() {
        return jdbcTemplate.query(Get_All_Product, this::mapRowToProduct);
    }

    @Override
    public List<Product> getProductByInvoiceId(int id) {
        return jdbcTemplate.query(Get_Product_By_Invoice_Id, this::mapRowToProduct, id);
    }

    @Override
    public List<Product> getProductsInInventory() {
        return jdbcTemplate.query(Get_Product_In_Inventory, this::mapRowToProduct);
    }
}

