package com.company.LevelUpService.Service;

import com.company.LevelUpService.ViewModel.LevelUpViewModel;
import com.company.LevelUpService.dao.LevelUpDao;
import com.company.LevelUpService.dao.LevelUpDaoJdbcTemplateImpl;
import com.company.LevelUpService.dto.LevelUp;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {
    LevelUpDao levelUpDao;
    ServiceLayer serviceLayer;

    @Before
    public void setUp() throws Exception {
        buildLevelUpMock();
        serviceLayer = new ServiceLayer(levelUpDao);
    }

    @Test
    public void createGetLevelUp() {
        LevelUpViewModel levelUp2 = new LevelUpViewModel();
        levelUp2.setPoints(5);
        levelUp2.setMemberDate(LocalDate.of(2020, 3, 20));
        levelUp2.setCustomerId(5);
        levelUp2 = serviceLayer.createLevelUp(levelUp2);

        LevelUpViewModel levelUp = serviceLayer.getLevelUp(5);
        assertEquals(levelUp, levelUp2);
    }

    @Test
    public void updateLevelUp() {
        LevelUpViewModel levelUp2 = new LevelUpViewModel();
        levelUp2.setPoints(5);
        levelUp2.setMemberDate(LocalDate.of(2020, 3, 20));
        levelUp2.setCustomerId(5);
        levelUp2 = serviceLayer.createLevelUp(levelUp2);

        levelUp2.setPoints(10);
        LevelUpViewModel levelUpViewModel = serviceLayer.updateLevelUp(levelUp2);
        assertEquals(levelUp2, levelUpViewModel);
    }

    @Test
    public void getAllLevelUp() {
        LevelUpViewModel levelUp2 = new LevelUpViewModel();
        levelUp2.setPoints(5);
        levelUp2.setMemberDate(LocalDate.of(2020, 3, 20));
        levelUp2.setCustomerId(5);
        levelUp2 = serviceLayer.createLevelUp(levelUp2);

        List<LevelUpViewModel> viewList = serviceLayer.getAllLevelUp();
        assertEquals(viewList.size(), 1);
    }
    private void buildLevelUpMock(){
        levelUpDao = mock(LevelUpDaoJdbcTemplateImpl.class);

        LevelUp levelUp = new LevelUp();
        levelUp.setLevelUpId(5);
        levelUp.setPoints(5);
        levelUp.setMemberDate(LocalDate.of(2020, 3, 20));
        levelUp.setCustomerId(5);

        LevelUp levelUp2 = new LevelUp();
        levelUp2.setPoints(5);
        levelUp2.setMemberDate(LocalDate.of(2020, 3, 20));
        levelUp2.setCustomerId(5);

        LevelUp levelUp3 = new LevelUp();
        levelUp3.setLevelUpId(5);
        levelUp3.setPoints(10);
        levelUp3.setMemberDate(LocalDate.of(2020, 3, 20));
        levelUp3.setCustomerId(5);

        List<LevelUp> lvlList = new ArrayList<>();
        lvlList.add(levelUp);

        doReturn(levelUp).when(levelUpDao).createLevelUp(levelUp2);
        doReturn(levelUp).when(levelUpDao).getLevelUp(5);
        doReturn(levelUp3).when(levelUpDao).updateLevelUp(levelUp3);
        doReturn(lvlList).when(levelUpDao).getAllLevelUp();
    }
}