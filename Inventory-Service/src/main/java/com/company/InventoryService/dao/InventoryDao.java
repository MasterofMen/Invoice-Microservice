package com.company.InventoryService.dao;

import com.company.InventoryService.dto.Inventory;

import java.util.List;

public interface InventoryDao {
    Inventory createInventory(Inventory inventory);
    Inventory getInventory(int id);
    Inventory updateInventory(Inventory inventory);
    void deleteInventory(int id);
    List<Inventory> getAllInventory();
}
