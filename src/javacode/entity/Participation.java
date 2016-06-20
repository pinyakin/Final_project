package javacode.entity;

import javacode.DAO.Identified;

/**
 * Created by ww on 09.05.2016.
 */
public class Participation implements Identified<Integer>{
    private Integer id=null;
    private int idhorse;
    private int idrace;
    private double winB;
    private double winL;
    private double Place;
    private double Show;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWinL() {
        return winL;
    }

    public void setWinL(double winL) {
        this.winL = winL;
    }

    public double getPlace() {
        return Place;
    }

    public void setPlace(double place) {
        Place = place;
    }

    public double getShow() {
        return Show;
    }

    public void setShow(double show) {
        Show = show;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdhorse() {
        return idhorse;
    }

    public void setIdhorse(int idhorse) {
        this.idhorse = idhorse;
    }

    public int getIdrace() {
        return idrace;
    }

    public void setIdrace(int idrace) {
        this.idrace = idrace;
    }

    public double getWinB() {
        return winB;
    }

    public void setWinB(double winB) {
        this.winB = winB;
    }
}