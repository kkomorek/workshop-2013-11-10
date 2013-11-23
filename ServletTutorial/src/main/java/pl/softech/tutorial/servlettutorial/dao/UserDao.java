/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.softech.tutorial.servlettutorial.dao;

import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import pl.softech.tutorial.servlettutorial.User;

/**
 *
 * @author ssledz
 */ 
public class UserDao {
    
    private final DataSource ds;

    public UserDao(DataSource ds) {
        this.ds = ds;
    }
    
    public void createUser(User user) {
    }
    
    public List<User> findAll() {
        return new LinkedList<>();
    }
    
    public User find(String name, String password) {
        return null;
    }
    
}
