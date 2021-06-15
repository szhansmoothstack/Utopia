package com.ss.utopia.service;

import com.ss.utopia.dao.*;
import com.ss.utopia.domain.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TravelerServices {
    ConnectionUtil connectionUtil = new ConnectionUtil();

    public boolean checkMembership(String username) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            UserDAO userDAO = new UserDAO(conn);
            return userDAO.readAllUsername(username).size() != 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            assert conn != null;
            conn.rollback();
        } finally {
            assert conn != null;
            conn.close();
        }
        return false;
    }

    public boolean checkRoute (Route route) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            RouteDAO routeDAO = new RouteDAO(conn);
            return routeDAO.doesExist(route);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            assert conn != null;
            conn.rollback();
        } finally {
            assert conn != null;
            conn.close();
        }
        return false;
    }

    public List<Flight> readFlightsByRoute (Route route) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            FlightDAO flightDAO = new FlightDAO(conn);
            return flightDAO.readFlightByRoute(route);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            assert conn != null;
            conn.rollback();
        } finally {
            assert conn != null;
            conn.close();
        }
        return null;
    }

    public List<Route> readAllRoutes () throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            RouteDAO routeDAO = new RouteDAO(conn);
            return routeDAO.readAllRoute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            assert conn != null;
            conn.rollback();
        } finally {
            assert conn != null;
            conn.close();
        }
        return null;
    }

    public void deleteBooking(String confirmationCode) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            BookingDAO bookingDAO = new BookingDAO(conn);
            Booking booking = bookingDAO.readBookingsByBookingConfirmation(confirmationCode);
            bookingDAO.deleteBooking(booking);
            conn.commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            assert conn != null;
            conn.rollback();
        } finally {
            assert conn != null;
            conn.close();
        }
    }

    /**
     * Add new booking to both booking and flight_booking table
     */

    public int addBooking(String confirmation, int flightId) throws SQLException {
        Connection conn = null;
        int newId = -1;
        try {
            conn = connectionUtil.getConnection();
            BookingDAO bookingDAO = new BookingDAO(conn);
            FlightBookingsDAO flightBookingsDAO = new FlightBookingsDAO(conn);
            newId = bookingDAO.addBooking(new Booking(-1,
                    1, confirmation));
            flightBookingsDAO.addFlightBooking(new FlightBookings(newId, flightId));
            conn.commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            assert conn != null;
            conn.rollback();
        } finally {
            assert conn != null;
            conn.close();
        }
        return newId;
    }

    /**
     * Airport manipulation section
     */
    public List<Airport> readAllAirport() throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            AirportDAO airportDAO = new AirportDAO(conn);
            return airportDAO.readAllAirports();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            assert conn != null;
            conn.rollback();
        } finally {
            assert conn != null;
            conn.close();
        }
        return null;
    }
}
