package javacode.entity;

import javacode.DAO.Identified;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

/**
 * Created by ww on 04.05.2016.
 */
public class Race implements Identified<Integer> {
    private Integer id;
    private String racename;
    private String ippodrom;
    private Timestamp date;
    private String first;
    private String second;
    private String third;

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

/*    public Race(int id, String racename, String ippodrom, String dateS, String first, String second, String third) {
        this.id = id;
        this.racename = racename;
        this.ippodrom = ippodrom;
        this.dateS = dateS;
        this.first = first;
        this.second = second;
        this.third = third;

    }*/

    public Race() {
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    /*public void setDateS(String dateS) {
        this.dateS = dateS;
    }


    public String getDateS() {
        return dateS;
    }*/

    public void setDates(Timestamp date) {
        this.date = date;
    }


    public Integer getId() {
        return id;
    }


    public String getRacename() {
        return racename;
    }

    public void setRacename(String racename) {
        this.racename = racename;
    }

    public String getIppodrom() {
        return ippodrom;
    }

    public static String toStr(String s){
        return s.substring(0,16);
    }

    public void setIppodrom(String ippodrom) {
        this.ippodrom = ippodrom;
    }
}
