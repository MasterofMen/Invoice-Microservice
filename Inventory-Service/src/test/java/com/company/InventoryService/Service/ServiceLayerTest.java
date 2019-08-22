package com.company.InventoryService.Service;

import com.company.InventoryService.ViewModel.InventoryViewModel;
import com.company.InventoryService.dao.InventoryDao;
import com.company.InventoryService.dao.InventoryDaoJdbcTemplateImpl;
import com.company.InventoryService.dto.Inventory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {
    InventoryDao inventoryDao;
    ServiceLayer serviceLayer;

    @Before
    public void setUp() throws Exception {
        setUpInventoryMockDao();

        serviceLayer = new ServiceLayer(inventoryDao);
    }

    @Test
    public void createGetInventory() {
        InventoryViewModel inventory2 = new InventoryViewModel();
        inventory2.setProductId(8);
        inventory2.setQuantity(5);
        inventory2 = serviceLayer.createInventory(inventory2);

        InventoryViewModel inventory = serviceLayer.getInventory(5);
        assertEquals(inventory, inventory2);
    }

    @Test
    public void updateInventory() {
        InventoryViewModel inventory2 = new InventoryViewModel();
        inventory2.setInventoryId(5);
        inventory2.setProductId(8);
        inventory2.setQuantity(5);
        serviceLayer.createInventory(inventory2);

        inventory2.setQuantity(3);
        InventoryViewModel inventory = serviceLayer.updateInventory(inventory2);
        assertEquals(inventory, inventory2);
    }

    @Test
    public void getAllInventory() {
        InventoryViewModel inventory2 = new InventoryViewModel();
        inventory2.setInventoryId(5);
        inventory2.setProductId(8);
        inventory2.setQuantity(5);
        serviceLayer.createInventory(inventory2);

        List<InventoryViewModel> invList = serviceLayer.getAllInventory();
        assertEquals(invList.size(), 1);
    }
    private void setUpInventoryMockDao(){
        inventoryDao = mock(InventoryDaoJdbcTemplateImpl.class);

        Inventory inventory = new Inventory();
        inventory.setInventoryId(5);
        inventory.setProductId(8);
        inventory.setQuantity(5);

        Inventory inventory2 = new Inventory();
        inventory2.setProductId(8);
        inventory2.setQuantity(5);

        Inventory inventory3 = new Inventory();
        inventory3.setInventoryId(5);
        inventory3.setProductId(8);
        inventory3.setQuantity(3);

        List<Inventory> invList = new ArrayList<>();
        invList.add(inventory);

        doReturn(inventory).when(inventoryDao).createInventory(inventory2);
        doReturn(inventory).when(inventoryDao).getInventory(5);
        doReturn(inventory3).when(inventoryDao).updateInventory(inventory3);
        doReturn(invList).when(inventoryDao).getAllInventory();
    }
}