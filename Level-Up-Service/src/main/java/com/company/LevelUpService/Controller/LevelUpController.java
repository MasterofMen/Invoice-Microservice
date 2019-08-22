package com.company.LevelUpService.Controller;

import com.company.LevelUpService.Service.ServiceLayer;
import com.company.LevelUpService.ViewModel.LevelUpViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class LevelUpController {
    @Autowired
    ServiceLayer serviceLayer;

    public LevelUpController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }
    @RequestMapping(value = "/levelup", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUpViewModel createLevelUp(@RequestBody LevelUpViewModel levelUpViewModel){
        return serviceLayer.createLevelUp(levelUpViewModel);
    }
    @RequestMapping(value = "/levelup", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUpViewModel> getAllLevelUp(){
        return serviceLayer.getAllLevelUp();
    }
    @RequestMapping(value = "/levelup", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateLevelUp(@RequestBody LevelUpViewModel levelUpViewModel){
        serviceLayer.updateLevelUp(levelUpViewModel);
    }
    @RequestMapping(value = "/levelup/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public LevelUpViewModel getLevelUp(@PathVariable int id){
        return serviceLayer.getLevelUp(id);
    }
    @RequestMapping(value = "/levelup/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteLevelUp(@PathVariable int id){
        serviceLayer.deleteLevelUp(id);
    }
    @RequestMapping(value = "/levelup/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public int getLevelUpPointsByCustomerId(@PathVariable int id){
        return serviceLayer.getLevelUpByCustomerId(id);
    }
}
