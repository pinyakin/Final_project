package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ww on 02.05.2016.
 */
public class MySqlDaoFactory implements DAOFactory{
    private String user="root";
    private String password="6831259";
    private String url="jdbc:mysql://localhost:3306/races";
    private String driver="com.mysql.jdbc.Driver";

    public MySqlDaoFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    @Override
    public ClientDAO getClientDao(Connection connection) {
        return new MySqlClientDao(connection);
    }

    public RaceDAO getRaceDao(Connection connection) {
        return new MySqlRaceDAO(connection);
    }

}
