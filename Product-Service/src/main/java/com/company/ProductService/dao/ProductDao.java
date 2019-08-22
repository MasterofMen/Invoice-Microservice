package com.company.ProductService.dao;

import com.company.ProductService.dto.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProductsInInventory();
    List<Product> getProductByInvoiceId(int id);
    Product createProduct(Product product);
    Product getProduct(int id);
    Product updateProduct(Product product);
    void deleteProduct(int id);
    List<Product> getAllProduct();
}
