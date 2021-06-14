package com.ss.utopia.dao;

import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.FlightBookings;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightBookingsDAO extends BaseDAO<FlightBookings> {
    public FlightBookingsDAO(Connection conn) {
        super(conn);
    }

    public void addFlightBooking (FlightBookings flightBookings) throws SQLException {
        save("insert into flight_bookings values (?, ?)", new Object[] {
                flightBookings.getFlightId(), flightBookings.getBookingId()
        });
    }

    //update booking id
    public void updateBookingId (FlightBookings flightBookings) throws SQLException {
        save("update flight_bookings set booking_id = ? where flight_id = ?",
                new Object[] {flightBookings.getBookingId(),
                        flightBookings.getFlightId()});
    }

    public void updateFlightId (FlightBookings flightBookings) throws SQLException {
        save("update flight_bookings set flight_id = ? where booking_id = ?",
                new Object[] {flightBookings.getFlightId(),
                        flightBookings.getBookingId()});
    }

    //delete one booking at a time
    public void deleteFlightBooking (FlightBookings flightBookings) throws SQLException {
        save ("delete from flight_bookings where booking_id = ?", new Object[]{flightBookings.getBookingId()});
    }

    //deletes all bookings from a flight
    public void deleteAllFlightBookings (FlightBookings flightBookings) throws SQLException {
        save ("delete from flight_bookings where flight_id = ?", new Object[]{flightBookings.getFlightId()});
    }

    public List<FlightBookings> readFlightBookings(FlightBookings flightBookings) throws SQLException, ClassNotFoundException {
        return read ("select * from flight_bookings where flight_id = ?",
                new Object[]{flightBookings.getFlightId()});
    }

    public List<FlightBookings> readAllFlightBookings() throws SQLException, ClassNotFoundException {
        return read ("select * from flight_bookings", null);
    }

    @Override
    public List<FlightBookings> extractData(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        List<FlightBookings> flightBookings = new ArrayList<>();

        while (resultSet.next()){
            FlightBookings fb = new FlightBookings(resultSet.getInt("flight_id"),
                    resultSet.getInt ("booking_id"));
            flightBookings.add(fb);
        }
        return flightBookings;
    }
}
