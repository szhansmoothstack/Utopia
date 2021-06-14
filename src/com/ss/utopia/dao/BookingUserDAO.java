package com.ss.utopia.dao;

import com.ss.utopia.domain.BookingAgent;
import com.ss.utopia.domain.BookingUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingUserDAO extends BaseDAO<BookingUser> {
    public BookingUserDAO(Connection conn) {
        super(conn);
    }

    public void addBookingUser(BookingUser bookingUser) throws SQLException {
        save("insert into booking_user values (?,?)", new Object[]{
                bookingUser.getBookingId(), bookingUser.getUserId()
        });
    }

    public void updateBookingId (BookingUser bookingUser) throws SQLException {
        save("update booking_user set booking_id = ? where user_id = ?",
                new Object[] {bookingUser.getBookingId(),
                        bookingUser.getUserId()});
    }

    public void updateUserId (BookingUser bookingUser) throws SQLException {
        save("update booking_user set user_id = ? where booking_id = ?",
                new Object[] {bookingUser.getUserId(),
                        bookingUser.getBookingId()});
    }

    public void deleteBookingUser(BookingUser bookingUser) throws SQLException {
        save("delete from booking_agent where booking_id = ? and user_id = ?"
                , new Object[]{bookingUser.getBookingId(), bookingUser.getUserId()});
    }

    public List<BookingUser> readAllBookingPayments() throws SQLException, ClassNotFoundException {
        return read("select * from booking_user",
                null);
    }

    @Override
    public List<BookingUser> extractData(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        List<BookingUser> bookingUsers = new ArrayList<>();

        while (resultSet.next()) {
            BookingUser bookingUser = new BookingUser(resultSet.getInt("booking_id"),
                    resultSet.getInt("user_id"));
            bookingUsers.add(bookingUser);
        }
        return bookingUsers;
    }
}
