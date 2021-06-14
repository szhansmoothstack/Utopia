package com.ss.utopia.service;

import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.domain.Airport;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TravelerServices {
    ConnectionUtil connectionUtil = new ConnectionUtil();

    public boolean checkMembership (String username) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            UserDAO userDAO = new UserDAO(conn);
            return userDAO.readAllUsername(username).size() == 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            assert conn != null;
            conn.rollback();
        } finally {
            assert conn != null;
            conn.close();
        }
        return false;
    }

    /**
     * Airport manipulation section
     */
    public List<Airport> readAllAirport() throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            AirportDAO airportDAO = new AirportDAO(conn);
            return airportDAO.readAllAirports();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            assert conn != null;
            conn.rollback();
        } finally {
            assert conn != null;
            conn.close();
        }
        return null;
    }
}
