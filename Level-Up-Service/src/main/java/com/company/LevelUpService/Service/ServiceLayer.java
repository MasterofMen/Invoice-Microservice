package com.company.LevelUpService.Service;

import com.company.LevelUpService.ViewModel.LevelUpViewModel;
import com.company.LevelUpService.dao.LevelUpDao;
import com.company.LevelUpService.dto.LevelUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {
    @Autowired
    LevelUpDao dao;

    public ServiceLayer(LevelUpDao dao) {
        this.dao = dao;
    }
    public LevelUpViewModel createLevelUp(LevelUpViewModel levelUp){
        LevelUp levelUp1 = new LevelUp();
        levelUp1.setCustomerId(levelUp.getCustomerId());
        levelUp1.setMemberDate(levelUp.getMemberDate());
        levelUp1.setPoints(levelUp.getPoints());
        levelUp1 = dao.createLevelUp(levelUp1);

        levelUp.setLevelUpId(levelUp1.getLevelUpId());
        return levelUp;
    }
    public LevelUpViewModel getLevelUp(int id){
        return buildViewModel(dao.getLevelUp(id));
    }
    public LevelUpViewModel updateLevelUp(LevelUpViewModel levelUp){
        LevelUp levelUp1 = new LevelUp();
        levelUp1.setLevelUpId(levelUp.getLevelUpId());
        levelUp1.setCustomerId(levelUp.getCustomerId());
        levelUp1.setMemberDate(levelUp.getMemberDate());
        levelUp1.setPoints(levelUp.getPoints());
        return levelUp;
    }
    public void deleteLevelUp(int id){
        dao.deleteLevelUp(id);
    }
    public List<LevelUpViewModel> getAllLevelUp(){
        List<LevelUp> lvlList = dao.getAllLevelUp();
        List<LevelUpViewModel> viewList = new ArrayList<>();
        for(LevelUp levelUp: lvlList){
            viewList.add(buildViewModel(levelUp));
        }
        return viewList;
    }
    public int getLevelUpByCustomerId(int id){
        LevelUp levelUp = dao.getLevelUpPointsByCustomerId(id);
        return levelUp.getPoints();
    }
    private LevelUpViewModel buildViewModel(LevelUp levelUp){
        LevelUpViewModel levelUpViewModel = new LevelUpViewModel();
        levelUpViewModel.setLevelUpId(levelUp.getLevelUpId());
        levelUpViewModel.setCustomerId(levelUp.getCustomerId());
        levelUpViewModel.setMemberDate(levelUp.getMemberDate());
        levelUpViewModel.setPoints(levelUp.getPoints());
        return levelUpViewModel;
    }
}
