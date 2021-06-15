package com.ss.utopia.dao;

import com.ss.utopia.domain.BookingAgent;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingAgentDAOTest {
    ConnectionUtil connectionUtil = new ConnectionUtil();
    BookingAgentDAO bookingAgentDAO;
    @Test
    void generalTest() throws SQLException {
        Connection conn = null;
        try{
            conn = connectionUtil.getConnection();
            bookingAgentDAO = new BookingAgentDAO(conn);
            BookingAgent a = new BookingAgent(2, 2);
            bookingAgentDAO.addBookingAgent(a);
            List<BookingAgent> bookingAgents = bookingAgentDAO.readAllBookingAgents();
            assertTrue(bookingAgents.contains(a));

            a.setAgentId(1);
            bookingAgentDAO.updateAgentId(a);
            a.setBookingId(6);
            bookingAgentDAO.updateBookingId(a);
            bookingAgents = bookingAgentDAO.readAllBookingAgents();
            assertEquals(1, bookingAgents.get(bookingAgents.indexOf(a)).getAgentId());
            assertEquals(6, bookingAgents.get(bookingAgents.indexOf(a)).getBookingId());

            bookingAgentDAO.deleteBookingAgent(a);
            bookingAgents = bookingAgentDAO.readAllBookingAgents();
            assertFalse (bookingAgents.contains(a));
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