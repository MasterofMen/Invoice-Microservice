package com.company.InventoryService.dao;

import com.company.InventoryService.dto.Inventory;
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
public class InventoryDaoTest {
    @Autowired
    InventoryDao dao;

    @Before
    public void setUp() throws Exception {
        List<Inventory> invList = dao.getAllInventory();

        invList.stream().forEach(x -> dao.deleteInventory(x.getInventoryId()));
    }

    @Test
    public void createGetInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(5);
        inventory = dao.createInventory(inventory);

        Inventory inventory1 = dao.getInventory(inventory.getInventoryId());
        assertEquals(inventory, inventory1);
    }

    @Test
    public void updateInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(5);
        inventory = dao.createInventory(inventory);

        inventory.setQuantity(3);
        Inventory inventory1 = dao.updateInventory(inventory);
        assertEquals(inventory, inventory1);
    }

    @Test
    public void deleteInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(5);
        inventory = dao.createInventory(inventory);

        dao.deleteInventory(inventory.getInventoryId());
        inventory = dao.getInventory(inventory.getInventoryId());
        assertNull(inventory);
    }

    @Test
    public void getAllInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(5);
        inventory = dao.createInventory(inventory);

        List<Inventory> invList = dao.getAllInventory();
        assertEquals(invList.size(), 1);
    }
}