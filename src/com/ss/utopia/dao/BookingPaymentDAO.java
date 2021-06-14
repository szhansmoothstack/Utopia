package com.ss.utopia.dao;

import com.ss.utopia.domain.BookingPayment;
import com.ss.utopia.domain.Passenger;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingPaymentDAO extends BaseDAO<BookingPayment> {

    public BookingPaymentDAO(Connection conn) {
        super(conn);
    }

    public void addBookingPayment(BookingPayment bookingPayment) throws SQLException {
        save("insert into booking_payment values (?,?,?)", new Object[]{
                bookingPayment.getBookingId(),bookingPayment.getStripeId(), bookingPayment.getRefunded()
        });
    }

    public void updateBookingPayment(BookingPayment bookingPayment) throws SQLException {
        save("update booking_payment set stripe_id = ?, refunded = ? where booking_id = ?",
                new Object[]{bookingPayment.getStripeId(),
                        bookingPayment.getRefunded(), bookingPayment.getBookingId()});
    }

    public void deleteBookingPayment(BookingPayment bookingPayment) throws SQLException {
        save("delete from booking_payment where booking_id = ?"
                , new Object[]{bookingPayment.getBookingId()});
    }

    public List<BookingPayment> readAllBookingPayments() throws SQLException, ClassNotFoundException {
        return read("select * from booking_payment",
                null);
    }

    @Override
    public List<BookingPayment> extractData(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        List<BookingPayment> bookingPayments = new ArrayList<>();

        while (resultSet.next()) {
            BookingPayment bookingPayment = new BookingPayment(resultSet.getInt("booking_id"),
                    resultSet.getString("stripe_id"), resultSet.getInt("refunded"));
            bookingPayments.add(bookingPayment);
        }
        return bookingPayments;
    }
}
