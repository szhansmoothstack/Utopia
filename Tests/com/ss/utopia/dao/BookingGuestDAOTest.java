package com.ss.utopia.dao;

import com.ss.utopia.domain.BookingGuest;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingGuestDAOTest {
    ConnectionUtil connectionUtil = new ConnectionUtil();
    BookingGuestDAO bookingGuest;
    @Test
    void generalTest() throws SQLException {
        Connection conn = null;
        try{
            conn = connectionUtil.getConnection();
            bookingGuest = new BookingGuestDAO(conn);
            BookingGuest a = new BookingGuest(33, "email", "123");
            bookingGuest.addBookingGuest(a);
            List<BookingGuest> bookingGuests = bookingGuest.readAllBookingGuests();
            assertTrue(bookingGuests.contains(a));

            a.setContactEmail("email2");
            bookingGuest.updateBookingGuest(a);
            bookingGuests = bookingGuest.readAllBookingGuests();
            assertTrue(bookingGuests.contains(a));

            bookingGuest.deleteBookingGuest(a);
            bookingGuests = bookingGuest.readAllBookingGuests();
            assertFalse (bookingGuests.contains(a));
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