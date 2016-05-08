package shared;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by ww on 02.05.2016.
 */
public interface DAOFactory {
    public Connection getConnection()throws SQLException;

    public ClientDAO getClientDao(Connection connection);

}
