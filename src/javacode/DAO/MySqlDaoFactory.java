package javacode.DAO;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by ww on 02.05.2016.
 */
public class MySqlDaoFactory implements DAOFactory{
    final private static String user="root";
    final private static String password="6831259";
    final private static String url="jdbc:mysql://localhost:3306/races";
    final private static String driver="com.mysql.jdbc.Driver";

    private final static Logger logger = Logger.getLogger(MySqlDaoFactory.class);
    public MySqlDaoFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error("Troubles with driver ",e);
        }
    }


    public static Connection getConnection() throws SQLException {
        Locale.setDefault(Locale.ENGLISH);
        try {
            Class.forName(driver).newInstance();
        } catch (InstantiationException |IllegalAccessException|ClassNotFoundException e) {
            logger.error("Exception in getConnection",e);
            e.printStackTrace();
        }
        return DriverManager.getConnection(url,user,password);

    }

    @Override
    public DAO getClientDao(Connection connection) {
        return new MySqlClientDao(connection);
    }

    @Override
    public DAO getRaceDao(Connection connection) {
        return new MySqlRaceDAO(connection);
    }

}
