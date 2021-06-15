package com.ss.utopia.dao;

import com.ss.utopia.domain.BookingGuest;
import com.ss.utopia.domain.BookingPayment;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingPaymentDAOTest {
    ConnectionUtil connectionUtil = new ConnectionUtil();
    BookingPaymentDAO bookingPaymentDAO;
    @Test
    void generalTest() throws SQLException {
        Connection conn = null;
        try{
            conn = connectionUtil.getConnection();
            bookingPaymentDAO = new BookingPaymentDAO(conn);
            BookingPayment a = new BookingPayment(33, "stripe", 1);
            bookingPaymentDAO.addBookingPayment(a);
            List<BookingPayment> bookingPayments = bookingPaymentDAO.readAllBookingPayments();
            assertTrue(bookingPayments.contains(a));

            a.setRefunded(0);
            bookingPaymentDAO.updateBookingPayment(a);
            bookingPayments = bookingPaymentDAO.readAllBookingPayments();
            assertTrue(bookingPayments.contains(a));

            bookingPaymentDAO.deleteBookingPayment(a);
            bookingPayments = bookingPaymentDAO.readAllBookingPayments();
            assertFalse (bookingPayments.contains(a));
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