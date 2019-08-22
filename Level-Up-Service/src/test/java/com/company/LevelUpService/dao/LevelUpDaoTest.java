package com.company.LevelUpService.dao;

import com.company.LevelUpService.dto.LevelUp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LevelUpDaoTest {
    @Autowired
    LevelUpDao levelUpDao;

    @Before
    public void setUp() throws Exception {
        List<LevelUp> levList = levelUpDao.getAllLevelUp();

        levList.stream().forEach(x -> levelUpDao.deleteLevelUp(x.getLevelUpId()));
    }

    @Test
    public void createLevelUp() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(2);
        levelUp.setPoints(5);
        levelUp.setMemberDate(LocalDate.of(2012, 5, 20));
        levelUp = levelUpDao.createLevelUp(levelUp);

        LevelUp levelUp1 = levelUpDao.getLevelUp(levelUp.getLevelUpId());
        assertEquals(levelUp, levelUp1);
    }

    @Test
    public void updateLevelUp() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(2);
        levelUp.setPoints(5);
        levelUp.setMemberDate(LocalDate.of(2012, 5, 20));
        levelUp = levelUpDao.createLevelUp(levelUp);

        levelUp.setPoints(8);
        LevelUp levelUp1 = levelUpDao.updateLevelUp(levelUp);
        assertEquals(levelUp, levelUp1);
    }

    @Test
    public void deleteLevelUp() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(2);
        levelUp.setPoints(5);
        levelUp.setMemberDate(LocalDate.of(2012, 5, 20));
        levelUp = levelUpDao.createLevelUp(levelUp);

        levelUpDao.deleteLevelUp(levelUp.getLevelUpId());
        levelUp = levelUpDao.getLevelUp(levelUp.getLevelUpId());
        assertNull(levelUp);
    }

    @Test
    public void getAllLevelUp() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(2);
        levelUp.setPoints(5);
        levelUp.setMemberDate(LocalDate.of(2012, 5, 20));
        levelUp = levelUpDao.createLevelUp(levelUp);

        List<LevelUp> lvlList = levelUpDao.getAllLevelUp();
        assertEquals(lvlList.size(), 1);
    }
}