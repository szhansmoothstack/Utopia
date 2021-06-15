package com.ss.utopia.dao;

import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.BookingAgent;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingDAOTest {
    ConnectionUtil connectionUtil = new ConnectionUtil();
    BookingDAO bookingDAO;
    @Test
    void generalTest() throws SQLException {
        Connection conn = null;
        try{
            conn = connectionUtil.getConnection();
            bookingDAO = new BookingDAO(conn);
            Booking a = new Booking(-1, 1, "TestConf");
            bookingDAO.addBooking(a);
            List<Booking> bookings = bookingDAO.readAllBookings();
            assertTrue(bookings.contains(a));

            a.setConfirmationCode("Confirmation2");
            bookingDAO.updateBookingId(a);
            bookings = bookingDAO.readAllBookings();
            assertTrue(bookings.contains(a));


            Booking b = bookingDAO.readBookingsByBookingId(a.getId());
            Booking c = bookingDAO.readBookingsByBookingConfirmation("Confirmation2");
            assertEquals(a, b);
            assertEquals(a, c);


            bookingDAO.deleteBooking(a);
            bookings = bookingDAO.readAllBookings();
            assertFalse (bookings.contains(a));
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