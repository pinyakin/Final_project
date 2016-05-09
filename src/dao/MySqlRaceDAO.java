package dao;

import entity.Race;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ww on 04.05.2016.
 */
public class MySqlRaceDAO implements RaceDAO {
    private final Connection connection;

    public MySqlRaceDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Race create() {
        return null;
    }

    @Override
    public Race read(int key) {
        return null;
    }

    @Override
    public void update(Race race) {

    }

    @Override
    public void delete(Race race) {

    }

    @Override
    public List<Race> getAll() {
        String sql="SELECT * FROM races.race";
        List<Race> list=new ArrayList<Race>();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Race race=new Race();
                race.setId(resultSet.getInt("id"));
                race.setDate(resultSet.getDate("date"));
                race.setRacename(resultSet.getString("racename"));
                race.setIppodrom(resultSet.getString("ippodrom"));
                list.add(race);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Race> getFutureRace() {
        String sql="SELECT * FROM races.race WHERE races.race.date> DATE(now())";
        List<Race> list=new ArrayList<Race>();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            int i=0;
            while (resultSet.next()){
                if(i>10)
                    break;
                i++;
                Race race=new Race();
                race.setId(resultSet.getInt("id"));
                race.setDate(resultSet.getDate("date"));
                race.setRacename(resultSet.getString("racename"));
                race.setIppodrom(resultSet.getString("ippodrom"));
                list.add(race);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
