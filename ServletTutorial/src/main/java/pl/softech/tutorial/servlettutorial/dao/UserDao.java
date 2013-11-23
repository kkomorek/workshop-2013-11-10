/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.softech.tutorial.servlettutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        try (Connection conn = ds.getConnection()) {

            PreparedStatement pstm = conn.prepareStatement(
                    "insert into myuser "
                    + "(login, password, firsta_name, last_name, email) "
                    + "values(?,?,?,?,?)");

            pstm.setString(1, user.getLogin());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getFirstName());
            pstm.setString(4, user.getLastName());
            pstm.setString(5, user.getEmail());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private User userFromRs(ResultSet rs) throws SQLException {

        User user = new User();
        user.setEmail(rs.getString("email"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("firsta_name"));
        user.setLastName(rs.getString("last_name"));
        
        return user;

    }

    public List<User> findAll() {

        List<User> users = new LinkedList<>();

        try (Connection conn = ds.getConnection()) {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from ssledz.myuser");

            while (rs.next()) {

                User user = userFromRs(rs);
                users.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    public User find(String login, String password) {

        try (Connection conn = ds.getConnection()) {

            PreparedStatement pstm = conn.prepareStatement("select * from ssledz.myuser "
                    + "where login=? and password=?");

            pstm.setString(1, login);
            pstm.setString(2, password);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {

                return userFromRs(rs);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
