package shared;

import java.sql.Date;

/**
 * Created by ww on 04.05.2016.
 */
public class Race {
    int id;
    String racename;
    Date date;
    String ippodrom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRacename() {
        return racename;
    }

    public void setRacename(String racename) {
        this.racename = racename;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIppodrom() {
        return ippodrom;
    }

    public void setIppodrom(String ippodrom) {
        this.ippodrom = ippodrom;
    }
}
