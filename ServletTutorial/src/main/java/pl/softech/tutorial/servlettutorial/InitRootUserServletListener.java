/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.softech.tutorial.servlettutorial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import pl.softech.tutorial.servlettutorial.dao.UserDao;

/**
 * Web application lifecycle listener.
 *
 * @author ssledz
 */
@WebListener
public class InitRootUserServletListener implements ServletContextListener {

    @Resource(mappedName = "jdbc/myUsersDatasource")
    private DataSource ds;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        UserDao userDao = new UserDao(ds);
        sce.getServletContext().setAttribute("userDao", userDao);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
