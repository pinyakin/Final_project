package javacode.entity;

import javacode.DAO.Identified;

/**
 * Created by ww on 09.05.2016.
 */
public class Horse implements Identified<Integer> {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
