package com.ss.utopia.dao;

import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO extends BaseDAO<Flight>{

    public FlightDAO (Connection conn) {
        super(conn);
    }

    public void addFlight (Flight flight) throws SQLException {
        int newID = saveWithPk("insert into flight (route_id, airplane_id, " +
                "departure_time, reserved_seats, seat_price) values (?,?,?,?,?)", new Object[] {
                flight.getRouteId(),flight.getAirplane_id(),flight.getDeparture_time(),
                flight.getReservedSeats(), flight.getSeatPrice()
        });
        flight.setId(newID);
    }

    public void updateFlight (Flight flight) throws SQLException {
        save("update flight set route_id = ?, airplane_id = ?," +
                        "departure_time = ?, reserved_seats = ?, seat_price = ? where id = ?",
                new Object[] {flight.getRouteId(),flight.getAirplane_id(),flight.getDeparture_time(),
                        flight.getReservedSeats(), flight.getSeatPrice(), flight.getId()});
    }

    public void deleteFlight (int id) throws SQLException {
        save ("delete from flight where id = ?", new Object[]{id});
    }

    public Flight readFlightById (int id) throws SQLException, ClassNotFoundException {
        return read("select * from flight, where id = ?", new Object[] {id}).get(0);
    }

    public List<Flight> readFlightByRoute (Route route) throws SQLException, ClassNotFoundException {
        return read("select * from flight, where route_id = ?", new Object[] {route.getId()});
    }

    public List<Flight> readAllFlights() throws SQLException, ClassNotFoundException {
        return read ("select * from flight", null);
    }

    @Override
    public List<Flight> extractData(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        List<Flight> flights = new ArrayList<>();

        while (resultSet.next()){
            Flight flight = new Flight(resultSet.getInt("id"),
                    resultSet.getInt ("route_id"), resultSet.getInt ("airplane_id"),
                    resultSet.getTimestamp("departure_time"), resultSet.getInt ("reserved_seats"),
                    resultSet.getFloat("seat_price"));
            flights.add(flight);
        }
        return flights;
    }
}
