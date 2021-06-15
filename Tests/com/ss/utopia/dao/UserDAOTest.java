package com.ss.utopia.dao;

import com.ss.utopia.domain.Passenger;
import com.ss.utopia.domain.User;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    ConnectionUtil connectionUtil = new ConnectionUtil();
    UserDAO userDAO;
    @Test
    void generalTest() throws SQLException {
        Connection conn = null;
        try{
            conn = connectionUtil.getConnection();
            userDAO = new UserDAO(conn);
            User a = new User(12, 3 , "Evalyn" , "Gancayco" ,
                    "BenAfleckIsAnOkActor1" , "bestemail1@me.com" , "love123" , "(234) 462-3706");
            userDAO.addUser(a);
            List<User> users = userDAO.readAllUsers();
            assertTrue(users.contains(a));

            a.setEmail("Testem2");
            userDAO.updateUser(a);
            users = userDAO.readAllUsers();
            assertEquals("Testem2", users.get(users.indexOf(a)).getEmail());

            userDAO.deleteUser(a.getId());
            users = userDAO.readAllUsers();
            assertFalse (users.contains(a));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            assert conn != null;
            conn.rollback();
        } finally {
            assert conn != null;
            conn.close();
        }
    }

}