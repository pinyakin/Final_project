package javacode.entity;

import javacode.DAO.Identified;

import java.sql.Date;

/**
 * Created by ww on 02.05.2016.
 */
public class Client implements Identified<Integer> {
    private String firstName;
    private String lastName;
    private String  patronymic;
    private String tel;
    private String address;
    private String email;
    private String password;
    private Integer id=null;
    private Date date;
    private String role;
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Client() {
        this.role = "client";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Client(Integer id,String firstName, String lastName, String patronymic, String tel, String address, String email, String password, Date date, String role,double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.tel = tel;
        this.address = address;
        this.email = email;
        this.password = password;
        this.id = id;
        this.date = date;
        this.role = role;
        this.balance=balance;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAdmin(){this.setRole("admin");}

}
