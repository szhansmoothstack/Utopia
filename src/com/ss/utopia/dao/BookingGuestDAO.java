package com.ss.utopia.dao;

import com.ss.utopia.domain.BookingGuest;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingGuestDAO extends BaseDAO<BookingGuest> {
    public BookingGuestDAO(Connection conn) {
        super(conn);
    }

    public void addBookingGuest(BookingGuest bookingGuest) throws SQLException {
        save("insert into booking_guest values (?,?,?)", new Object[]{
                bookingGuest.getBookingId(), bookingGuest.getContactEmail(), bookingGuest.getContactPhone()
        });
    }

    public void updateBookingGuest(BookingGuest bookingGuest) throws SQLException {
        save("update booking_guest set contact_email = ?, contact_phone = ?, where booking_id = ?",
                new Object[]{bookingGuest.getContactPhone(), bookingGuest.getContactPhone(),
                        bookingGuest.getBookingId()});
    }

    public void deleteBookingGuest(BookingGuest bookingGuest) throws SQLException {
        save("delete from booking_guest where booking_id = ?"
                , new Object[]{bookingGuest.getBookingId()});
    }

    public List<BookingGuest> readAllBookingPayments() throws SQLException, ClassNotFoundException {
        return read("select * from booking_payment",
                null);
    }

    @Override
    public List<BookingGuest> extractData(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        List<BookingGuest> bookingGuests = new ArrayList<>();

        while (resultSet.next()) {
            BookingGuest bookingGuest = new BookingGuest(resultSet.getInt("booking_id"),
                    resultSet.getString("contanct_email"), resultSet.getString("contact_phone"));
            bookingGuests.add(bookingGuest);
        }
        return bookingGuests;
    }
}
