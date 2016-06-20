package javacode.DAO;

import java.io.Serializable;

/**
 * Created by ww on 19.05.2016.
 */
public interface Identified<PK extends Serializable> {

    /** Get primary key of object */
    public PK getId();
}