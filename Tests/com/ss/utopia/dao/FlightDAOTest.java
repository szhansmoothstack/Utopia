package com.ss.utopia.dao;

import com.ss.utopia.domain.BookingUser;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightDAOTest {
    ConnectionUtil connectionUtil = new ConnectionUtil();
    FlightDAO flightDAO;
    @Test
    void generalTest() throws SQLException {
        Connection conn = null;
        try{
            conn = connectionUtil.getConnection();
            flightDAO = new FlightDAO(conn);
            Flight a = new Flight(-1,2,1,
                    Timestamp.valueOf("2021-01-01 01:01:01") , 0, 300.50f);
            flightDAO.addFlight(a);
            List<Flight> flights = flightDAO.readAllFlights();
            assertTrue(flights.contains(a));

            a.setReservedSeats(27);
            flightDAO.updateFlight(a);
            flights = flightDAO.readAllFlights();
            assertTrue(flights.contains(a));

            flightDAO.deleteFlight(a.getId());
            flights = flightDAO.readAllFlights();
            assertFalse (flights.contains(a));
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