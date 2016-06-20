package javacode.DAO;

import java.sql.Connection;

/**
 * Created by ww on 02.05.2016.
 */
public interface DAOFactory {
    public DAO getClientDao(Connection connection);
    public DAO getRaceDao(Connection connection);
}
