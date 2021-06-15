package com.ss.utopia.dao;

import com.ss.utopia.domain.BookingUser;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingUserDAOTest {
    ConnectionUtil connectionUtil = new ConnectionUtil();
    BookingUserDAO bookingUserDAO;
    @Test
    void generalTest() throws SQLException {
        Connection conn = null;
        try{
            conn = connectionUtil.getConnection();
            bookingUserDAO = new BookingUserDAO(conn);
            BookingUser a = new BookingUser(30, 11);
            bookingUserDAO.addBookingUser(a);
            List<BookingUser> bookingUsers = bookingUserDAO.readAllBookingUsers();
            assertTrue(bookingUsers.contains(a));

            a.setBookingId(27);
            bookingUserDAO.updateBookingId(a);
            a.setUserId(6);
            bookingUserDAO.updateUserId(a);
            bookingUsers = bookingUserDAO.readAllBookingUsers();
            assertTrue(bookingUsers.contains(a));

            bookingUserDAO.deleteBookingUser(a);
            bookingUsers = bookingUserDAO.readAllBookingUsers();
            assertFalse (bookingUsers.contains(a));
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