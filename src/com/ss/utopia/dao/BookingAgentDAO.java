package com.ss.utopia.dao;

import com.ss.utopia.domain.BookingAgent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingAgentDAO extends BaseDAO<BookingAgent> {

    public BookingAgentDAO(Connection conn) {
        super(conn);
    }

    public void addBookingAgent(BookingAgent bookingAgent) throws SQLException {
        save("insert into booking_agent values (?,?)", new Object[]{
                bookingAgent.getBookingId(), bookingAgent.getAgentId()
        });
    }

    public void updateBookingId (BookingAgent bookingAgent) throws SQLException {
        save("update booking_agent set booking_id = ? where agent_id = ?",
                new Object[] {bookingAgent.getBookingId(),
                        bookingAgent.getAgentId()});
    }

    public void updateAgentId (BookingAgent bookingAgent) throws SQLException {
        save("update booking_agent set agent_id = ? where booking_id = ?",
                new Object[] {bookingAgent.getAgentId(),
                        bookingAgent.getBookingId()});
    }

    public void deleteBookingAgent(BookingAgent bookingAgent) throws SQLException {
        save("delete from booking_agent where booking_id = ? and agent_id = ?"
                , new Object[]{bookingAgent.getBookingId(), bookingAgent.getAgentId()});
    }

    public List<BookingAgent> readAllBookingPayments() throws SQLException, ClassNotFoundException {
        return read("select * from booking_agent",
                null);
    }

    @Override
    public List<BookingAgent> extractData(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        List<BookingAgent> bookingAgents = new ArrayList<>();

        while (resultSet.next()) {
            BookingAgent bookingAgent = new BookingAgent(resultSet.getInt("booking_id"),
                    resultSet.getInt("agent_id"));
            bookingAgents.add(bookingAgent);
        }
        return bookingAgents;
    }
}
