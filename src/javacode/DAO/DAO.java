package javacode.DAO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ww on 02.05.2016.
 *
 *
 * Interface for realization all DAO
 */
public interface DAO<T,PK extends Serializable> {
    public T create()throws SQLException;
    public T persist(T object) throws SQLException;
    public void update(T object) throws SQLException;
    public void delete(T object) throws SQLException;
    public T getbyPK(int key) throws SQLException;
    public List<T> getAll() throws SQLException;
}
