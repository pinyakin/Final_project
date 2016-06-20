package javacode.DAO;

import javacode.entity.Horse;
import javacode.entity.Participation;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ww on 03.06.2016.
 */
public class MySqlDaoParticipation extends AbstractJDBCDao<Participation,Integer> {
    private final Logger logger = Logger.getLogger(MySqlDaoParticipation.class);
    private class PersistParticipation extends Participation {
        public void setId(int id) {
            super.setId(id);
        }
    }
    public MySqlDaoParticipation(Connection connection) {
        super(connection);
    }


    @Override
    public String getSelectQuery() {
        return "SELECT id, idrace,idhorse,name,winB,winL,place,showbet FROM races.participants ";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE races.participants SET winB=?,winL=?,place=?,showbet=? WHERE id=?; ";
    }

    @Override
    public String getCreateQuery() {
        return  "INSERT INTO races.participants (id,idrace,idhorse,name,winB,winL,place,showbet) VALUE(?,?,?,?,?,?,?,?);";
    }

    @Override
    public String getDeleteQuery() {
        return  "DELETE FROM races.participants WHERE id=?; ";
    }

    @Override
    protected List<Participation> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<Participation> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistParticipation participation = new PersistParticipation();
                participation.setId(rs.getInt("id"));
                participation.setIdrace(rs.getInt("idrace"));
                participation.setIdhorse(rs.getInt("idhorse"));
                participation.setName(rs.getString("name"));
                participation.setWinB(rs.getDouble("winB"));
                participation.setWinL(rs.getDouble("winL"));
                participation.setPlace(rs.getDouble("place"));
                participation.setShow(rs.getDouble("showbet"));
                result.add(participation);
            }
        } catch (Exception e) {
            logger.error("Troubles with parameters in method parseResultSet: ",e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Participation object) throws SQLException {
        try {
            int partId = (object.getId() == null) ? 0 : object.getId();

            statement.setInt(1,partId);
            statement.setInt(2,object.getIdrace());
            statement.setInt(3,object.getIdhorse());
            statement.setString(4,object.getName());
            statement.setDouble(5,object.getWinB());
            statement.setDouble(6,object.getWinL());
            statement.setDouble(7,object.getPlace());
            statement.setDouble(8,object.getShow());
        } catch (Exception e) {
            logger.error("Troubles with parameters in method prepareStatementForInsert: ",e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Participation object) throws SQLException {
        try {
            statement.setDouble(1,object.getWinB());
            statement.setDouble(2,object.getWinL());
            statement.setDouble(3,object.getPlace());
            statement.setDouble(4,object.getShow());
            statement.setInt(5,object.getId());
        } catch (Exception e) {
            logger.error("Troubles with parameters in method prepareStatementForUpdate: ",e);
        }
    }

    @Override
    public Participation create() throws SQLException {
        Participation participation=new Participation();
        return persist(participation);
    }

    public ArrayList<Participation> getByRaceId(String raceId){
        List<Participation> list=new ArrayList<>();
        String sql = "SELECT  id, idrace,idhorse,name,winB,winL,place,showbet FROM races.participants WHERE idrace="+raceId;
        try (PreparedStatement statement = MySqlDaoFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        }  catch (SQLException e) {
            logger.error("SQLException in method prepareStatementForUpdate: ",e);
        }
        return (ArrayList<Participation>) list;
    }
    public Participation getByIdRIdH(int idR,int idH) throws SQLException {
        List<Participation> list=new ArrayList<>();
        String sql = getSelectQuery();
        sql += " WHERE idrace = ? and idhorse=?";
        try (PreparedStatement statement = MySqlDaoFactory.getConnection().prepareStatement(sql)) {
            statement.setInt(1, idR);
            statement.setInt(2,idH);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            logger.error("SQLException: ",e);
        } if (list == null || list.size() == 0) {
            return null; }
        if (list.size() > 1) { logger.error("Received more than one record."); }
        return  list.iterator().next();
    }
}