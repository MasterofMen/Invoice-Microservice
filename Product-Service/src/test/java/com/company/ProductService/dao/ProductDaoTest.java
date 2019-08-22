package com.company.ProductService.dao;

import com.company.ProductService.dto.Product;
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
public class ProductDaoTest {
    @Autowired
    ProductDao productDao;

    @Before
    public void setUp() throws Exception {
        List<Product> prList = productDao.getAllProduct();

        prList.stream().forEach(x -> productDao.deleteProduct(x.getProductId()));
    }

    @Test
    public void getProductsInInventory() {
        Product product = new Product();
        product.setListPrice(25f);
        product.setProductDescription("Something cool");
        product.setProductId(5);
        product.setProductName("banana");
        product = productDao.createProduct(product);

        List<Product> product1 = productDao.getProductsInInventory();
        assertEquals(product1.size(), 1);
    }

    @Test
    public void getProductByInvoiceId() {
        Product product = new Product();
        product.setListPrice(25f);
        product.setProductDescription("Something cool");
        product.setProductId(5);
        product.setProductName("banana");
        product = productDao.createProduct(product);

        List<Product> prList = productDao.getProductByInvoiceId(5);
        assertEquals(prList.size(), 1);
    }

    @Test
    public void createProduct() {
        Product product = new Product();
        product.setListPrice(25f);
        product.setProductDescription("Something cool");
        product.setProductId(5);
        product.setProductName("banana");
        product = productDao.createProduct(product);

        Product product1 = productDao.getProduct(product.getProductId());
        assertEquals(product, product1);
    }

    @Test
    public void updateProduct() {
        Product product = new Product();
        product.setListPrice(25f);
        product.setProductDescription("Something cool");
        product.setProductId(5);
        product.setProductName("banana");
        product = productDao.createProduct(product);

        product.setProductName("hello");
        Product product1 = productDao.updateProduct(product);
        assertEquals(product, product1);
    }

    @Test
    public void deleteProduct() {
        Product product = new Product();
        product.setListPrice(25f);
        product.setProductDescription("Something cool");
        product.setProductId(5);
        product.setProductName("banana");
        product = productDao.createProduct(product);

        productDao.deleteProduct(product.getProductId());
        product = productDao.getProduct(product.getProductId());
        assertNull(product);
    }

    @Test
    public void getAllProduct() {
        Product product = new Product();
        product.setListPrice(25f);
        product.setProductDescription("Something cool");
        product.setProductId(5);
        product.setProductName("banana");
        product = productDao.createProduct(product);

        List<Product> prList = productDao.getAllProduct();
    }
}