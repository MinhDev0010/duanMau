/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;

/**
 *
 * @author DELL
 */
public abstract class EduSysDAO<EntityType, KeyType> {

    public abstract void insert(EntityType entity);

    public abstract void update(EntityType entity);

    public abstract void delete(KeyType ID);

    public abstract List<EntityType> seletAll();

    public abstract EntityType seletByID(KeyType id);

    public abstract List<EntityType> seletBySQL(String sql, Object... args);
}
