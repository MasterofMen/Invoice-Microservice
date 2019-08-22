package com.company.InventoryService.dao;

import com.company.InventoryService.dto.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InventoryDaoJdbcTemplateImpl implements InventoryDao {
    private Inventory mapRowToInventory(ResultSet rs, int rowNum) throws SQLException{
        Inventory inventory = new Inventory();
        inventory.setInventoryId(rs.getInt("inventory_id"));
        inventory.setProductId(rs.getInt("product_id"));
        inventory.setQuantity(rs.getInt("quantity"));
        return inventory;
    }
    JdbcTemplate jdbcTemplate;
    @Autowired
    public InventoryDaoJdbcTemplateImpl(JdbcTemplate newjdbcTemplate){
        this.jdbcTemplate = newjdbcTemplate;
    }
    private static final String Create_Inventory_SQL = "insert into inventory (product_id, quantity) values (?,?)";
    private static final String Get_Inventory_SQL = "select * from inventory where inventory_id = ?";
    private static final String Update_Inventory_SQL = "update inventory set product_id = ?, quantity = ? where inventory_id = ?";
    private static final String Delete_Inventory_SQL = "delete from inventory where inventory_id = ?";
    private static final String Get_All_Inventory_SQL = "select * from inventory";
    @Override
    @Transactional
    public Inventory createInventory(Inventory inventory) {
        jdbcTemplate.update(Create_Inventory_SQL, inventory.getProductId(), inventory.getQuantity());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        inventory.setInventoryId(id);
        return inventory;
    }

    @Override
    public Inventory getInventory(int id) {
        try {
            return jdbcTemplate.queryForObject(Get_Inventory_SQL, this::mapRowToInventory, id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Inventory updateInventory(Inventory inventory) {
        jdbcTemplate.update(Update_Inventory_SQL, inventory.getProductId(), inventory.getQuantity(), inventory.getInventoryId());
        return jdbcTemplate.queryForObject(Get_Inventory_SQL, this::mapRowToInventory, inventory.getInventoryId());
    }

    @Override
    @Transactional
    public void deleteInventory(int id) {
        jdbcTemplate.update(Delete_Inventory_SQL, id);
    }

    @Override
    public List<Inventory> getAllInventory() {
        return jdbcTemplate.query(Get_All_Inventory_SQL, this::mapRowToInventory);
    }
}
