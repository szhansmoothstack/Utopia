package com.ss.utopia.dao;

import com.ss.utopia.domain.Passenger;
import com.ss.utopia.domain.UserRole;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleDAOTest {
    ConnectionUtil connectionUtil = new ConnectionUtil();
    UserRoleDAO userRoleDAO;
    @Test
    void generalTest() throws SQLException {
        Connection conn = null;
        try{
            conn = connectionUtil.getConnection();
            userRoleDAO = new UserRoleDAO(conn);
            UserRole a = new UserRole(4, "test");
            userRoleDAO.addUserRole(a);
            List<UserRole> userRoles = userRoleDAO.readAllUserRoles();
            assertTrue(userRoles.contains(a));

            a.setName("Test2");
            userRoleDAO.updateUserRole(a);
            userRoles = userRoleDAO.readAllUserRoles();
            assertEquals("Test2", userRoles.get(userRoles.indexOf(a)).getName());

            userRoleDAO.deleteUserRole(a);
            userRoles = userRoleDAO.readAllUserRoles();
            assertFalse (userRoles.contains(a));
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