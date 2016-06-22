package javacode.DAO;

import javacode.entity.Client;
import javacode.entity.Race;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ww on 04.05.2016.
 */
public class MySqlRaceDAO extends AbstractJDBCDao<Race,Integer> {
    private final Logger logger = Logger.getLogger(MySqlDaoParticipation.class);
    public MySqlRaceDAO(Connection connection) {
        super(connection);
    }
    private class PersistRace extends Race {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public Race create() throws SQLException {
        Race race=new Race();
        return persist(race);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, racename, date, ippodrom, first, second, third FROM races.race ";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE races.race SET  racename=?, date=?, ippodrom=?, first=?, second=?, third=? WHERE id=?; ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT races.race (id,racename, date, ippodrom, first, second, third) VALUE (?,?,?,?,?,?,?);  ";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM races.race WHERE id= ?;";
    }

    @Override
    protected List<Race> parseResultSet(ResultSet rs) throws SQLException {
        ArrayList<Race> result = new ArrayList<>();
        try {
            while (rs.next()) {
                PersistRace race = new PersistRace();
                race.setId(rs.getInt("id"));
                race.setRacename(rs.getString("racename"));
                race.setDates(rs.getTimestamp("date"));
                race.setIppodrom(rs.getString("ippodrom"));
                race.setFirst(rs.getString("first"));
                race.setSecond(rs.getString("second"));
                race.setThird(rs.getString("third"));
                result.add(race);
            }
        } catch (Exception e) {
            logger.error("Troubles with parameters in method parseResultSet:",e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Race object) throws SQLException {
        try {
            int raceId = (object.getId() == null) ? 0 : object.getId();

            statement.setInt(1,raceId);
            statement.setString(2,object.getRacename());
            statement.setTimestamp(3,object.getDate());
            statement.setString(4,object.getIppodrom());
            statement.setString(5, object.getFirst());
            statement.setString(6,object.getSecond());
            statement.setString(7,object.getThird());
        } catch (Exception e) {
            logger.error("Troubles with parameters in method prepareStatementForInsert:",e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Race object) throws SQLException {
        try {
            statement.setString(1, object.getRacename());
            statement.setTimestamp(2,object.getDate());
            statement.setString(3, object.getIppodrom());
            statement.setString(4, object.getFirst());
            statement.setString(5, object.getSecond());
            statement.setString(6, object.getThird());
            statement.setInt(7, object.getId());
        } catch (Exception e) {
            logger.error("Troubles with parameters in method prepareStatementForUpdate:",e);
        }
    }

    public List<Race> getTimeRace(String time) {
        String sql="select * from races.race where races.race.date>TIMESTAMP(now()) and races.race.date<adddate(now(),interval+1 MONTH) ORDER BY races.race.date";
        if(time!=null && time.equals("day")) {
            sql = "select * from races.race where races.race.date>TIMESTAMP(now()) and races.race.date<adddate(now(),interval+1 day) ORDER BY races.race.date";
        }
        if(time!=null && time.equals("month")){
            sql="select * from races.race where races.race.date>TIMESTAMP(now()) and races.race.date<adddate(now(),interval+1 MONTH) ORDER BY races.race.date";
        }
        if(time!=null && time.equals("week")){
            sql="select * from races.race where races.race.date>TIMESTAMP(now()) and races.race.date<adddate(now(),interval+1 WEEK) ORDER BY races.race.date";
        }
        if(time!=null && time.equals("all")){
            sql="select * from races.race where races.race.date>TIMESTAMP(now())";
        }
        List<Race> list=new ArrayList<Race>();
        try (final  PreparedStatement preparedStatement=MySqlDaoFactory.getConnection().prepareStatement(sql);
             final ResultSet resultSet=preparedStatement.executeQuery()){
            while (resultSet.next()){
                Race race=new Race();
                race.setId(resultSet.getInt("id"));
                race.setIppodrom(resultSet.getString("ippodrom"));
                race.setDate(resultSet.getTimestamp("date"));
                race.setRacename(resultSet.getString("racename"));
                race.setFirst(resultSet.getString("first"));
                race.setSecond(resultSet.getString("second"));
                race.setThird(resultSet.getString("third"));
                list.add(race);
            }
        } catch (SQLException e) {
            logger.error("SQLException getTimeRace",e);
        }
        return list;
    }
    public List<Race> getHistory(String time) {
        String sql="select * from races.race where races.race.date<TIMESTAMP(now()) and races.race.date>adddate(now(),interval-1 MONTH) ORDER BY races.race.date";
        if(time!=null && time.equals("day")) {
            sql = "select * from races.race where races.race.date<TIMESTAMP(now()) and races.race.date>adddate(now(),interval-1 day) ORDER BY races.race.date";
        }
        if(time!=null && time.equals("month")){
            sql="select * from races.race where races.race.date<TIMESTAMP(now()) and races.race.date>adddate(now(),interval-1 MONTH) ORDER BY races.race.date";
        }
        if(time!=null && time.equals("week")){
            sql="select * from races.race where races.race.date<TIMESTAMP(now()) and races.race.date>adddate(now(),interval-1 WEEK) ORDER BY races.race.date";
        }
        if(time!=null && time.equals("all")){
            sql="select * from races.race where races.race.date<TIMESTAMP(now())";
        }
        List<Race> list=new ArrayList<Race>();
        try (final  PreparedStatement preparedStatement=MySqlDaoFactory.getConnection().prepareStatement(sql);
             final ResultSet resultSet=preparedStatement.executeQuery()){
            while (resultSet.next()){
                Race race=new Race();
                race.setId(resultSet.getInt("id"));
                race.setIppodrom(resultSet.getString("ippodrom"));
                race.setDate(resultSet.getTimestamp("date"));
                race.setRacename(resultSet.getString("racename"));
                race.setFirst(resultSet.getString("first"));
                race.setSecond(resultSet.getString("second"));
                race.setThird(resultSet.getString("third"));
                list.add(race);
            }
        } catch (SQLException e) {
            logger.error("SQLException getHistory",e);
        }
        return list;
    }
    public List<Race> getFutureRace() {
        String sql="SELECT * FROM races.race WHERE races.race.date> TIMESTAMP (now()) ORDER BY races.race.date";
        List<Race> list=new ArrayList<Race>();
        try(final PreparedStatement preparedStatement=MySqlDaoFactory.getConnection().prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery()) {

            int i=0;
            while (resultSet.next()){
                if(i>10)
                    break;
                i++;
                Race race=new Race();
                race.setId(resultSet.getInt("id"));
                race.setIppodrom(resultSet.getString("ippodrom"));
                race.setDate(resultSet.getTimestamp("date"));
                race.setRacename(resultSet.getString("racename"));
                race.setFirst(resultSet.getString("first"));
                race.setSecond(resultSet.getString("second"));
                race.setThird(resultSet.getString("third"));
                list.add(race);
            }
        } catch (SQLException e) {
            logger.error("SQLException getFutureRace",e);
        }

        return list;
    }

}
