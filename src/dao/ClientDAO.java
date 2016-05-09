package dao;

import entity.Client;

import java.util.List;

/**
 * Created by ww on 02.05.2016.
 */
public interface ClientDAO {
    public Client create();
    public Client read(int key);
    public void update(Client client);
    public void delete(Client client);
    public List<Client> getAll();
}
