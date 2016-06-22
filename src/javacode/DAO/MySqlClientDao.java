package javacode.DAO;

import javacode.entity.Client;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ww on 02.05.2016.
 */
public class MySqlClientDao extends AbstractJDBCDao<Client,Integer> {

    private final Logger logger = Logger.getLogger(MySqlClientDao.class);
    private class PersistClient extends Client {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlClientDao(Connection connection) {
      super(connection);
    }

    @Override
    public Client create() throws  SQLException {
        Client client=new Client();
        return persist(client);
    }

    @Override
    public String getSelectQuery() {
         return "SELECT id, firstName, lastName, patronymic, date, email, password, tel, address,role,balance  FROM races.client ";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE races.client SET firstName=?, lastName=?, patronymic=?, date=?, email=?, password=?, tel=?, address=?,role=?,balance=? WHERE id=?;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO races.client (id, firstName, lastName, patronymic, date, email, password, tel, address, role, balance) \n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    }


    @Override
    public String getDeleteQuery() {
        return "DELETE FROM races.client WHERE id= ?;";
    }

    @Override
    protected List<Client> parseResultSet(ResultSet rs) throws SQLException {

        ArrayList<Client> result = new ArrayList<>();
        try {
            while (rs.next()) {
                PersistClient client = new PersistClient();
                client.setId(rs.getInt("id"));
                client.setFirstName(rs.getString("firstName"));
                client.setLastName(rs.getString("lastName"));
                client.setPatronymic(rs.getString("patronymic"));
                client.setDate(rs.getDate("date"));
                client.setEmail(rs.getString("email"));
                client.setPassword(rs.getString("password"));
                client.setTel(rs.getString("tel"));
                client.setAddress(rs.getString("address"));
                client.setRole(rs.getString("role"));
                client.setBalance(rs.getDouble("balance"));
                result.add(client);
            }
        } catch (Exception e) {
            logger.error("SQLException parseResultSet",e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Client object) throws SQLException {
        try {
            int clientId = (object.getId() == null) ? 0 : object.getId();

            statement.setInt(1,clientId);
            statement.setString(2,object.getFirstName());
            statement.setString(3,object.getLastName());
            statement.setString(4,object.getPatronymic());
            statement.setDate(5, object.getDate());
            statement.setString(6,object.getEmail());
            statement.setString(7,object.getPassword());
            statement.setString(8,object.getTel());
            statement.setString(9,object.getAddress());
            statement.setString(10,object.getRole());
            statement.setDouble(11,object.getBalance());
        } catch (Exception e) {
            logger.error("SQLException prepareStatementForInsert",e);
        }
    }
    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Client object) throws SQLException {
        try {
            statement.setString(1,object.getFirstName());
            statement.setString(2,object.getLastName());
            statement.setString(3,object.getPatronymic());
            statement.setDate(4,object.getDate());
            statement.setString(5,object.getEmail());
            statement.setString(6,object.getPassword());
            statement.setString(7,object.getTel());
            statement.setString(8,object.getAddress());
            statement.setString(9,object.getRole());
            statement.setDouble(10,object.getBalance());
            statement.setInt(11,object.getId());
        } catch (Exception e) {
            logger.error("SQLException prepareStatementForUpdate",e);
        }
    }
    public Client getClientByEmail(String email) throws SQLException {
        try(final Connection connection=MySqlDaoFactory.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet rs = statement.executeQuery(
                    "SELECT id,firstName, lastName, patronymic, date,email, password, tel,address, role,balance FROM client where email = '" +
                            "" + email +"'")){
                if (rs.next()) {
                    return new Client(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("patronymic"),
                    rs.getString("tel"),
                    rs.getString("address"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getDate("date"),
                    rs.getString("role"),
                    rs.getDouble("balance"));
                }
            }
         catch (SQLException e) {
             logger.error("Exception in getConnection",e);
        }
        return null;
    }
}
