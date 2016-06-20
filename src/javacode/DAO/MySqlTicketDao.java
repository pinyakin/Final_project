package javacode.DAO;

import javacode.entity.Client;
import javacode.entity.Ticket;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ww on 28.05.2016.
 */
public class MySqlTicketDao extends AbstractJDBCDao<Ticket, Integer> {
    private class PersistTicket extends Ticket {
    public void setId(int id) {
        super.setId(id);
    }
}
    private final Logger logger = Logger.getLogger(MySqlTicketDao.class);
    public MySqlTicketDao(Connection connection) {
        super(connection);
    }

    @Override
    public Ticket create() throws  SQLException {
        Ticket ticket=new Ticket();
        return persist(ticket);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM races.ticket ";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE races.ticket SET idrace=?, idrace=?, idclient=?, date=?, email=?, horse=?, money=?, koef=?,win=?,type=? WHERE id=?;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO races.ticket (id, idrace, idclient, date, horse ,money, koef, win, type) \n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?);";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM races.client WHERE id= ?;";
    }

    @Override
    protected List<Ticket> parseResultSet(ResultSet rs) throws SQLException {

        LinkedList<Ticket> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistTicket ticket = new PersistTicket();
                ticket.setId(rs.getInt("id"));
                ticket.setIdRace(rs.getInt("idrace"));
                ticket.setIdClient(rs.getInt("idclient"));
                ticket.setDate(rs.getTimestamp("date"));
                ticket.setHorse(rs.getString("horse"));
                ticket.setMoney(rs.getDouble("money"));
                ticket.setCoefficient(rs.getDouble("koef"));
                ticket.setWin(rs.getString("win"));
                ticket.setTypeBet(rs.getString("type"));
                result.add(ticket);
            }
        } catch (Exception e) {
            logger.error("Troubles with parameters in method parseResultSet:",e);
        }
        return result;
    }



    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Ticket object) throws SQLException {
        try {
            int ticketId = (object.getId() == null) ? 0 : object.getId();

            statement.setInt(1,ticketId);
            statement.setInt(2,object.getIdRace());
            statement.setInt(3,object.getIdClient());
            statement.setString(4,object.getDate());
            statement.setString(5, object.getHorse());
            statement.setDouble(6,object.getMoney());
            statement.setDouble(7,object.getCoefficient());
            statement.setString(8,object.getWin());
            statement.setString(9,object.getTypeBet());
        } catch (Exception e) {
            logger.error("Troubles with parameters in method prepareStatementForInsert:",e);
        }
    }
    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Ticket object) throws SQLException {
        try {
            statement.setInt(1,object.getIdRace());
            statement.setInt(2,object.getIdClient());
            statement.setString(3,object.getDate());
            statement.setString(4, object.getHorse());
            statement.setDouble(5,object.getMoney());
            statement.setDouble(6,object.getCoefficient());
            statement.setString(7,object.getWin());
            statement.setString(8,object.getTypeBet());
            statement.setInt(9,object.getId());
        } catch (Exception e) {
            logger.error("Troubles with parameters in method prepareStatementForUpdate:",e);
    }
    }

    public List<Ticket> getClientHistory(Client client)
    {
        String sql="SELECT * FROM races.ticket WHERE idclient = "+client.getId()+" ORDER BY races.ticket.date";
        List<Ticket> list=new ArrayList<Ticket>();
        try(final PreparedStatement preparedStatement=MySqlDaoFactory.getConnection().prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery()) {
            while (rs.next()){
                Ticket ticket=new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setIdRace(rs.getInt("idrace"));
                ticket.setIdClient(rs.getInt("idclient"));
                ticket.setDate(rs.getTimestamp("date"));
                ticket.setHorse(rs.getString("horse"));
                ticket.setMoney(rs.getDouble("money"));
                ticket.setCoefficient(rs.getDouble("koef"));
                ticket.setWin(rs.getString("win"));
                ticket.setTypeBet(rs.getString("type"));
                list.add(ticket);
            }
        } catch (SQLException e) {
            logger.error("SQLException getClientHistory",e);
        }

        return list;
    }
    public List<Ticket> getByRaceId(int raceId){
        String sql="SELECT * FROM races.ticket WHERE  idrace= "+raceId;
        List<Ticket> list=new ArrayList<Ticket>();
        try(final PreparedStatement preparedStatement=MySqlDaoFactory.getConnection().prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery()) {
            while (rs.next()){
                Ticket ticket=new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setIdRace(rs.getInt("idrace"));
                ticket.setIdClient(rs.getInt("idclient"));
                ticket.setDate(rs.getTimestamp("date"));
                ticket.setHorse(rs.getString("horse"));
                ticket.setCoefficient(rs.getDouble("koef"));
                ticket.setMoney(rs.getDouble("money"));
                ticket.setWin(rs.getString("win"));
                ticket.setTypeBet(rs.getString("type"));
                list.add(ticket);
            }
        } catch (SQLException e) {
            logger.error("SQLException getByRaceId",e);
        }

        return list;
    }
}