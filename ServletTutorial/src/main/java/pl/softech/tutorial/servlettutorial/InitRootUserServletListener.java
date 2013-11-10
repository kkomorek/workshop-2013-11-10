/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.softech.tutorial.servlettutorial;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author ssledz
 */
@WebListener
public class InitRootUserServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        List<User>  users = new LinkedList<>();
        User root = new User();
        root.setFirstName("Root");
        root.setEmail("root@gmail.com");
        root.setLastName("Root");
        root.setLogin("root");
        root.setLogin("test");
        users.add(root);
        sce.getServletContext().setAttribute("users", users);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
