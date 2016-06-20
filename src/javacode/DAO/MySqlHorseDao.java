package javacode.DAO;

import javacode.entity.Horse;
import javacode.entity.Participation;
import javacode.entity.Race;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ww on 02.06.2016.
 */
public class MySqlHorseDao extends AbstractJDBCDao<Horse,Integer> {
    private final Logger logger = Logger.getLogger(MySqlHorseDao.class);
    public MySqlHorseDao(Connection connection) {
        super(connection);
    }

    private class PersistHorse extends Horse {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, horsename FROM races.horses ";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE races.horses SET horsename=? WHERE id=?; ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO races.horses (id,horsename) VALUE(?,?);";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM races.horses WHERE id=?; ";
    }

    @Override
    protected List<Horse> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<Horse> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistHorse horse = new PersistHorse();
                horse.setId(rs.getInt("id"));
                horse.setName(rs.getString("horsename"));
                result.add(horse);
            }
        } catch (Exception e) {
            logger.error("Troubles with parameters in method parseResultSet: ",e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Horse object) throws SQLException {
        try {
            int horseId = (object.getId() == null) ? 0 : object.getId();

            statement.setInt(1, horseId);
            statement.setString(2, object.getName());
        } catch (Exception e) {
            logger.error("Troubles with parameters in method prepareStatementForInsert: ",e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Horse object) throws SQLException {
        try {
            statement.setString(1, object.getName());
            statement.setInt(2, object.getId());
        } catch (Exception e) {
            logger.error("Troubles with parameters in method prepareStatementForUpdate: ",e);
        }
    }

    @Override
    public Horse create() throws SQLException {
        Horse horse = new Horse();
        return persist(horse);
    }
    public List<Horse> getForPart(String key) {
        List<Horse> list = new ArrayList<>();
        String sql = "select * From races.horses where id not in(select idhorse from races.participants where idrace="+key+");";
        try (PreparedStatement statement = MySqlDaoFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        }  catch (SQLException e) {
            logger.error("SQLException: ",e);
        }
        return list;
    }
    public List<Horse> getPartByIdRace(int idRace) {
        List<Horse> list = new ArrayList<>();
        String sql = "select * From races.horses where id in(select idhorse from races.participants where idrace="+idRace+");";
        try (PreparedStatement statement = MySqlDaoFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list =parseResultSet(rs);
        } catch (SQLException e) {
            logger.error("SQLException: ",e);
        }
        return list;
    }
}
