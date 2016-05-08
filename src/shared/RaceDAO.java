package shared;

import java.util.List;

/**
 * Created by ww on 04.05.2016.
 */
public interface RaceDAO {
    public Race create();
    public Race read(int key);
    public void update(Race race);
    public void delete(Race race);
    public List<Race> getAll();
}
