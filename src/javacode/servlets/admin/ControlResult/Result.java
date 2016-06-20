package javacode.servlets.admin.ControlResult;

import javacode.DAO.*;
import javacode.entity.Client;
import javacode.entity.Race;
import javacode.entity.Ticket;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ww on 10.06.2016.
 *
 *
 *
 * Class for control bet and take win bets to Clients-winner
 */
public class Result {
    /**
     * @param raceId
     * @throws SQLException
     */
    public static void control(int raceId) throws SQLException {
        MySqlRaceDAO raceDAO =new MySqlRaceDAO(MySqlDaoFactory.getConnection());
        MySqlClientDao clientDao =new MySqlClientDao(MySqlDaoFactory.getConnection());
        MySqlTicketDao ticketDao=new MySqlTicketDao(MySqlDaoFactory.getConnection());
        Race race=raceDAO.getbyPK(raceId);
        List<Ticket> list=ticketDao.getByRaceId(raceId);
        for(Ticket ticket:list){
            Client client=clientDao.getbyPK(ticket.getIdClient());
            switch (ticket.getTypeBet()) {
                case "winB":{
                    if(race.getFirst().equals(ticket.getHorse())) {
                        client.setBalance(client.getBalance() + ticket.getMoney() * ticket.getCoefficient());
                        ticket.setWin(String.valueOf(ticket.getMoney() * ticket.getCoefficient()));
                    }
                    if(!race.getFirst().equals(ticket.getHorse())) {
                        ticket.setWin(String.valueOf(0));
                    }
                    break;
                }
                case "winL":{
                    if(!race.getFirst().equals(ticket.getHorse())) {
                        client.setBalance(client.getBalance() + ticket.getMoney() * ticket.getCoefficient());
                        ticket.setWin(String.valueOf(ticket.getMoney() * ticket.getCoefficient()));
                    }
                    if(race.getFirst().equals(ticket.getHorse())) {
                        ticket.setWin(String.valueOf(0));
                    }
                    break;
                }
                case "place":{
                    if(race.getFirst().equals(ticket.getHorse())||race.getSecond().equals(ticket.getHorse())) {
                        client.setBalance(client.getBalance() + ticket.getMoney() * ticket.getCoefficient());
                        ticket.setWin(String.valueOf(ticket.getMoney() * ticket.getCoefficient()));
                    }
                    if(!race.getFirst().equals(ticket.getHorse())&&!race.getSecond().equals(ticket.getHorse())) {
                        ticket.setWin(String.valueOf(0));
                    }
                    break;
                }
                case "show":{

                    if(race.getFirst().equals(ticket.getHorse())||race.getSecond().equals(ticket.getHorse())||race.getThird().equals(ticket.getHorse())) {
                        client.setBalance(client.getBalance() + ticket.getMoney() * ticket.getCoefficient());
                        ticket.setWin(String.valueOf(ticket.getMoney() * ticket.getCoefficient()));
                    }
                    if(!race.getFirst().equals(ticket.getHorse())&&!race.getSecond().equals(ticket.getHorse())&&!race.getSecond().equals(ticket.getHorse())) {
                        ticket.setWin(String.valueOf(0));
                    }
                    break;
                }
                default:break;
            }
        }

    }
}
