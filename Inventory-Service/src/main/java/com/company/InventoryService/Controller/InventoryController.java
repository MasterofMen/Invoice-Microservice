package com.company.InventoryService.Controller;

import com.company.InventoryService.Service.ServiceLayer;
import com.company.InventoryService.ViewModel.InventoryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class InventoryController {
    @Autowired
    ServiceLayer serviceLayer;

    public InventoryController(ServiceLayer serviceLayer){
        this.serviceLayer = serviceLayer;
    }

    @RequestMapping(value = "/inventory", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryViewModel createInventory(@RequestBody InventoryViewModel inventoryViewModel){
        inventoryViewModel = serviceLayer.createInventory(inventoryViewModel);
        return inventoryViewModel;
    }
    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public InventoryViewModel getInventory(@PathVariable int id){
        return serviceLayer.getInventory(id);
    }
    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteInventory(@PathVariable int id){
        serviceLayer.deleteInventory(id);
    }
    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryViewModel> getAllInventory(){
        return serviceLayer.getAllInventory();
    }
    @RequestMapping(value = "/inventory", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateInventory(@RequestBody InventoryViewModel inventoryViewModel){
        serviceLayer.updateInventory(inventoryViewModel);
    }
}
