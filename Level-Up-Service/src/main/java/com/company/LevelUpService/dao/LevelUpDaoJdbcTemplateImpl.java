package com.company.LevelUpService.dao;

import com.company.LevelUpService.dto.LevelUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LevelUpDaoJdbcTemplateImpl implements LevelUpDao {
    private LevelUp mapRowToLevelUp(ResultSet rs, int rowNum) throws SQLException{
        LevelUp levelUp = new LevelUp();
        levelUp.setLevelUpId(rs.getInt("level_up_id"));
        levelUp.setCustomerId(rs.getInt("customer_id"));
        levelUp.setPoints(rs.getInt("points"));
        levelUp.setMemberDate(rs.getDate("member_date").toLocalDate());
        return levelUp;
    }
    JdbcTemplate jdbcTemplate;
    @Autowired
    public LevelUpDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final String Create_Level_Up = "insert into level_up (customer_id, points, member_date) values (?,?,?)";
    private static final String Get_Level_Up = "select * from level_up where level_up_id = ?";
    private static final String Update_Level_Up = "update level_up set customer_id = ?, points = ?, member_date = ? where level_up_id = ?";
    private static final String Get_All_Level_Up = "select * from level_up";
    private static final String Delete_Level_Up = "delete from level_up where level_up_id = ?";
    private static final String Get_Level_Up_By_Customer = "select * from level_up where customer_id = ?";

    @Override
    @Transactional
    public LevelUp createLevelUp(LevelUp levelUp) {
        jdbcTemplate.update(Create_Level_Up, levelUp.getCustomerId(), levelUp.getPoints(), levelUp.getMemberDate());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        levelUp.setLevelUpId(id);
        return levelUp;
    }

    @Override
    public LevelUp getLevelUp(int id) {
        try {
            return jdbcTemplate.queryForObject(Get_Level_Up, this::mapRowToLevelUp, id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public LevelUp updateLevelUp(LevelUp levelUp) {
        jdbcTemplate.update(Update_Level_Up, levelUp.getCustomerId(), levelUp.getPoints(), levelUp.getMemberDate(), levelUp.getLevelUpId());
        return jdbcTemplate.queryForObject(Get_Level_Up, this::mapRowToLevelUp, levelUp.getLevelUpId());
    }

    @Override
    public List<LevelUp> getAllLevelUp() {
        return jdbcTemplate.query(Get_All_Level_Up, this::mapRowToLevelUp);
    }

    @Override
    @Transactional
    public void deleteLevelUp(int id) {
        jdbcTemplate.update(Delete_Level_Up, id);
    }

    @Override
    public LevelUp getLevelUpPointsByCustomerId(int id) {
        return jdbcTemplate.queryForObject(Get_Level_Up_By_Customer, this::mapRowToLevelUp, id);
    }
}
