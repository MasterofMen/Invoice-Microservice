package com.company.LevelUpService.dao;

import com.company.LevelUpService.dto.LevelUp;

import java.util.List;

public interface LevelUpDao {
    LevelUp createLevelUp(LevelUp levelUp);
    LevelUp getLevelUp(int id);
    LevelUp updateLevelUp(LevelUp levelUp);
    void deleteLevelUp(int id);
    List<LevelUp> getAllLevelUp();
    LevelUp getLevelUpPointsByCustomerId(int id);
}
