package shared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ww on 02.05.2016.
 */
public class MySqlClientDao implements ClientDAO{
    private final Connection connection;

    public MySqlClientDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Client create() {
        return null;
    }

    @Override
    public Client read(int key) {
        String sql="SELECT * FROM races.client WHERE id=?;";
        Client client=new Client();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,key);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.next();
            client.setId(resultSet.getInt("id"));
            client.setFirstName(resultSet.getString("firstName"));
            client.setLastName(resultSet.getString("lastName"));


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client;
    }

    @Override
    public void update(Client client) {

    }

    @Override
    public void delete(Client client) {

    }

    @Override
    public List<Client> getAll() {
        String sql="SELECT * FROM client";
        List<Client>list=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (!resultSet.next()){
                Client client=new Client();
                client.setId(resultSet.getInt("id"));
                client.setFirstName(resultSet.getString("firstName"));
                client.setLastName(resultSet.getString("lastName"));
                list.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
