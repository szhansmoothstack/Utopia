package com.ss.utopia.dao;

import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.FlightBookings;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO extends BaseDAO<Booking> {

    public BookingDAO(Connection conn) {
        super(conn);
    }

    public int addBooking(Booking booking) throws SQLException {
        int newId = saveWithPk("insert into booking ( is_active , confirmation_code ) values (?, ?)", new Object[]{
                booking.getIsActive(), booking.getConfirmationCode()
        });
        booking.setId(newId);
        return newId;
    }

    public void updateBookingId(Booking booking) throws SQLException {
        save("update booking set is_active = ?, confirmation_code = ? where id = ?",
                new Object[]{booking.getIsActive(),
                        booking.getConfirmationCode(), booking.getId()});
    }

    public void deleteBooking(Booking booking) throws SQLException {
        save("delete from booking where id = ?"
                , new Object[]{booking.getId()});
    }

    public Booking readBookingsByBookingId(int id) throws SQLException, ClassNotFoundException {
        return read("select * from booking where id = ?",
                new Object[]{id}).get(0);
    }

    public Booking readBookingsByBookingConfirmation(String confirmation) throws SQLException, ClassNotFoundException {
        return read("select * from booking where confirmation_code = ?",
                new Object[]{confirmation}).get(0);
    }

    public List<Booking> readAllBookings() throws SQLException, ClassNotFoundException {
        return read("select * from booking",
                null);
    }

    @Override
    public List<Booking> extractData(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        List<Booking> bookings = new ArrayList<>();

        while (resultSet.next()) {
            Booking booking = new Booking(resultSet.getInt("id"),
                    resultSet.getInt("is_active"), resultSet.getString("confirmation_code"));
            bookings.add(booking);
        }
        return bookings;
    }
}
