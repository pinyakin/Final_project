package javacode.DAO;

/**
 * Created by ww on 26.05.2016.
 *
 *
 * Class to get DAOFactory
 */
public class Connections {
    private static DAOFactory factory = null;

    /**
     * Returns DAOFactory
     * @return {@link MySqlDaoFactory}
     */
    public static DAOFactory getFactory() {
        if (factory == null) {
            factory = new MySqlDaoFactory();
        }
        return factory;
    }
}
