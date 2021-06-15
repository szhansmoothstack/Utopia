package com.ss.utopia.dao;

import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.FlightBookings;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightBookingsDAOTest {
    ConnectionUtil connectionUtil = new ConnectionUtil();
    FlightBookingsDAO flightBookingsDAO;
    @Test
    void generalTest() throws SQLException {
        Connection conn = null;
        try{
            conn = connectionUtil.getConnection();
            flightBookingsDAO = new FlightBookingsDAO(conn);
            FlightBookings a = new FlightBookings(11, 12);
            flightBookingsDAO.deleteFlightBooking(a);
            flightBookingsDAO.addFlightBooking(a);
            List<FlightBookings> flightBookings = flightBookingsDAO.readAllFlightBookings();
            assertTrue(flightBookings.contains(a));

            flightBookingsDAO.deleteFlightBooking(a);
            flightBookings = flightBookingsDAO.readAllFlightBookings();
            assertFalse (flightBookings.contains(a));
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