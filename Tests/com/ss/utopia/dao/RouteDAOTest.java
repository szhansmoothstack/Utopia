package com.ss.utopia.dao;

import com.ss.utopia.domain.Route;
import com.ss.utopia.domain.UserRole;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RouteDAOTest {
    ConnectionUtil connectionUtil = new ConnectionUtil();
    RouteDAO routeDAO;
    @Test
    void generalTest() throws SQLException {
        Connection conn = null;
        try{
            conn = connectionUtil.getConnection();
            routeDAO = new RouteDAO(conn);
            Route a = new Route(345,"AMS", "ATL");
            List<Route> routes;

            routeDAO.deleteRoute(a);
            routes = routeDAO.readAllRoute();
            assertFalse (routes.contains(a));

            routeDAO.addRoute(a);
            routes = routeDAO.readAllRoute();
            assertTrue(routes.contains(a));

            a.setDestination("MEX");
            routeDAO.updateRoute(a);
            routes = routeDAO.readAllRoute();
            assertTrue(routes.contains(a));


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