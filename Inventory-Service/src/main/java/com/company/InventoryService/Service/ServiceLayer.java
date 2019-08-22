package com.company.InventoryService.Service;

import com.company.InventoryService.ViewModel.InventoryViewModel;
import com.company.InventoryService.dao.InventoryDao;
import com.company.InventoryService.dto.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {
    @Autowired
    InventoryDao dao;

    public ServiceLayer(InventoryDao inventoryDao){
        this.dao = inventoryDao;
    }
    @Transactional
    public InventoryViewModel createInventory(InventoryViewModel inventoryViewModel){
        Inventory inventory = new Inventory();
        inventory.setProductId(inventoryViewModel.getProductId());
        inventory.setQuantity(inventoryViewModel.getQuantity());
        inventory = dao.createInventory(inventory);

        inventoryViewModel.setInventoryId(inventory.getInventoryId());
        return inventoryViewModel;
    }
    public InventoryViewModel getInventory(int id){
        return buildViewModel(dao.getInventory(id));
    }
    public InventoryViewModel updateInventory(InventoryViewModel inventoryViewModel){
        Inventory inventory = new Inventory();
        inventory.setInventoryId(inventoryViewModel.getInventoryId());
        inventory.setProductId(inventoryViewModel.getProductId());
        inventory.setQuantity(inventoryViewModel.getQuantity());

        dao.updateInventory(inventory);
        return inventoryViewModel;
    }
    @Transactional
    public void deleteInventory(int id){
        dao.deleteInventory(id);
    }
    public List<InventoryViewModel> getAllInventory(){
        List<Inventory> invList = dao.getAllInventory();
        List<InventoryViewModel> viewList = new ArrayList<>();
        for(Inventory inventory: invList){
            viewList.add(buildViewModel(inventory));
        }
        return viewList;
    }
    private InventoryViewModel buildViewModel(Inventory inventory){
        InventoryViewModel inventoryViewModel = new InventoryViewModel();
        inventoryViewModel.setInventoryId(inventory.getInventoryId());
        inventoryViewModel.setProductId(inventory.getProductId());
        inventoryViewModel.setQuantity(inventory.getQuantity());
        return inventoryViewModel;
    }
}
