package com.ss.utopia.dao;

import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Passenger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAO extends BaseDAO<Passenger> {

    public PassengerDAO(Connection conn) {
        super(conn);
    }

    public void addPassenger(Passenger passenger) throws SQLException {
        int newId = saveWithPk("insert into passenger ( booking_id , given_name , " +
                "family_name , dob , gender , address ) values (?,?,?,?,?,?)", new Object[]{
                passenger.getBookingId(), passenger.getGivenName(), passenger.getFamilyName(),
                passenger.getDob(), passenger.getGender(), passenger.getAddress()
        });
        passenger.setId(newId);
    }

    public void updatePassenger(Passenger passenger) throws SQLException {
        save("update passenger set booking_id = ?, given_name = ?, family_name = ?, " +
                        "dob = ?, gender = ?, address = ? where id = ?",
                new Object[]{passenger.getBookingId(), passenger.getGivenName(), passenger.getFamilyName(),
                        passenger.getDob(), passenger.getGender(), passenger.getAddress(), passenger.getId()});
    }

    public void deletePassenger(Passenger passenger) throws SQLException {
        save("delete from passenger where id = ?"
                , new Object[]{passenger.getId()});
    }

    public Passenger readPassengerByBookingId(int id) throws SQLException, ClassNotFoundException {
        return read("select * from passenger where booking_id = ?",
                new Object[]{id}).get(0);
    }

    public List<Passenger> readAllPassengers() throws SQLException, ClassNotFoundException {
        return read("select * from passenger",
                null);
    }

    @Override
    public List<Passenger> extractData(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        List<Passenger> passengers = new ArrayList<>();

        while (resultSet.next()) {
            Passenger passenger = new Passenger(resultSet.getInt("id"),
                    resultSet.getInt("booking_id"), resultSet.getString("given_name"),
                    resultSet.getString("family_name"), resultSet.getDate("dob"),
                    resultSet.getString("gender"), resultSet.getString("address"));
            passengers.add(passenger);
        }
        return passengers;
    }
}
