package javacode.DAO;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ww on 19.05.2016.
 *
 *
 * Abstract class for MysqlDAO of all our entities
 */
public abstract class AbstractJDBCDao<T extends Identified<PK>,PK extends Integer> implements DAO<T,PK> {
    private Connection connection;

    /**
     * @return MySQL Select query
     *
     */
    public abstract String getSelectQuery();

    /**
     * @return MySQL Update query
     */
    public abstract String getUpdateQuery();

    /**
     * @return MySQL Create query
     */
    public abstract String getCreateQuery();

    /**
     * @return MySQL Delete query
     */
    public abstract String getDeleteQuery();

    /**
     * @param rs
     * @return
     * @throws SQLException
     *
     * Method for parsing ResultSet form DB
     */
    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

    /**
     * @param statement
     * @param object
     * @throws SQLException
     *
     * Method to prepare statement for mysql insert query
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;

    /**
     * @param statement
     * @param object
     * @throws SQLException
     *
     * Method to prepare statement for mysql update query
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;

    private final Logger logger = Logger.getLogger(AbstractJDBCDao.class);

    public void closePrepareStatement(PreparedStatement ps){
        if(ps!=null)
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    /**
     * @param object
     * @return
     * @throws SQLException
     *
     * Create new object of entity for create query without constructor
     */
    @Override
    public T persist(T object) throws SQLException {
        if (object.getId() != null) {
            logger.error("Object is already persist.");
        }
        T persistInstance=null;
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                logger.error("On persist modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            logger.error("Troubles with connection",e);
        } // Get last entry
         sql = getSelectQuery() + " WHERE id = last_insert_id();";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
         List<T> list = parseResultSet(rs);
        if ((list == null) || (list.size() != 1)) {
            logger.debug("Exception on findByPK new persist data.");
        }
            persistInstance = list.iterator().next();
    }catch (Exception e) {
            logger.error(e);
    }
    return persistInstance;
}


    /**
     * @param object
     * @throws SQLException
     *
     * Update entity of type T
     */
    @Override
    public void update(T object) throws SQLException {

        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count !=1) {
                logger.error("Method update: On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            logger.error("Troubles with connection");
        }
    }

    /**
     * @param object
     * @throws SQLException
     *
     *
     * Delete object type T from DB
     */
    @Override
    public void delete(T object) throws SQLException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                logger.error("Method delete: Troubles with parameter id");
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                logger.error("Method delete: On delete modify more then 1 record: " + count);
            }
        }
        catch (Exception e) {
            logger.error("Troubles with connection");
        }
    }

    /**
     * @param key
     * @return
     * @throws SQLException
     *
     *
     * Get entity by his primary key
     */
    @Override
    public T getbyPK(int key) throws SQLException {
        logger.debug("getAll method:"+getSelectQuery()+" WHERE id ="+key);
        List<T> list=new ArrayList<>();
        final String sql = getSelectQuery()+" WHERE id = ?";
        try (final PreparedStatement statement = connection.prepareStatement(sql)){
             statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            logger.error("Method getbyPK: troubles with connection");
        } if (list == null || list.size() == 0) {
            return null; }
        if (list.size() > 1) { logger.error("Method getbyPK: received more than one record."); }
        return list.iterator().next();
    }

    /**
     * @return
     * @throws SQLException
     *
     * Get all entities from DB
     */
    @Override
    public List<T> getAll() throws SQLException {
        logger.debug("getAll method:"+getSelectQuery());
        List<T> list=new ArrayList<>();
        final String sql = getSelectQuery();
        try (final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet rs = statement.executeQuery()){
            list = parseResultSet(rs);
        } catch (SQLException e) {
            logger.error("SQLException getAll", e);
        }
        return list;
    }
    public AbstractJDBCDao(Connection connection) {
        this.connection = connection; }
}
