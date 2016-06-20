package javacode.entity;

import javacode.DAO.Identified;

import java.sql.Timestamp;

/**
 * Created by ww on 09.05.2016.
 */
public class Ticket implements Identified<Integer> {
    private Integer id=null;
    private int idRace;
    private int idClient;
    private String date;
    private String horse;
    private double money;
    private String typeBet;
    private double coefficient;
    private String win;



    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdRace() {
        return idRace;
    }

    public void setIdRace(int idRace) {
        this.idRace = idRace;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date =toStr( date.toString());
    }

    public String getHorse() {
        return horse;
    }

    public void setHorse(String horse) {
        this.horse = horse;
    }

    public String getTypeBet() {
        return typeBet;
    }

    public void setTypeBet(String typeBet) {
        this.typeBet = typeBet;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double bet) {
        this.money = bet;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    protected String toStr(String s){
        return s.substring(0,16);
    }

}
