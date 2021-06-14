package com.ss.utopia.service;

import com.ss.utopia.dao.*;
import com.ss.utopia.domain.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminServices {

    ConnectionUtil connectionUtil = new ConnectionUtil();

    /**
     * Route Section
     */

    public void addRoute(Route route) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            RouteDAO routeDAO = new RouteDAO(conn);
            routeDAO.addRoute(route);
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

    public void deleteRoute(Route route) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            RouteDAO routeDAO = new RouteDAO(conn);
            routeDAO.deleteRoute(route);
            conn.commit();
        } catch (SQLException | ClassNotFoundException e) {
            assert false;
            e.printStackTrace();
            conn.rollback();
        } finally {
            assert conn != null;
            conn.close();
        }
    }

    public List<Route> readRoutes() throws SQLException {
        Connection conn = null;
        List<Route> result = new ArrayList<>();
        try {
            conn = connectionUtil.getConnection();
            RouteDAO routeDAO = new RouteDAO(conn);
            result = routeDAO.readAllRoute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            assert conn != null;
            conn.rollback();
        } finally {
            assert conn != null;
            conn.close();
        }
        return result;
    }

    public Route readRouteById(int id) throws SQLException {
        Connection conn = null;
        Route result = null;
        try {
            conn = connectionUtil.getConnection();
            RouteDAO routeDAO = new RouteDAO(conn);
            result = routeDAO.readRouteById(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            assert conn != null;
            conn.rollback();
        } finally {
            assert conn != null;
            conn.close();
        }
        return result;
    }

    /**
     * Flight section
     */
    public void addFlight(Flight flight) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            FlightDAO flightDAO = new FlightDAO(conn);
            flightDAO.addFlight(flight);
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

    public void deleteFlight(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            FlightDAO flightDAO = new FlightDAO(conn);
            flightDAO.deleteFlight(id);
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

    public List<Flight> readAllFlights() throws SQLException {
        Connection conn = null;
        List<Flight> result = new ArrayList<>();
        try {
            conn = connectionUtil.getConnection();
            FlightDAO flightDAO = new FlightDAO(conn);
            result = flightDAO.readAllFlights();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            assert conn != null;
            conn.rollback();
        } finally {
            assert conn != null;
            conn.close();
        }
        return result;
    }

    public Flight readFlightById(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            FlightDAO flightDAO = new FlightDAO(conn);
            return flightDAO.readFlightById(id);
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

    public void updateFlight(Flight flight) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            FlightDAO flightDAO = new FlightDAO(conn);
            flightDAO.updateFlight(flight);
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
     * Airplane Type Section
     */

    public AirplaneType readAirplaneTypeById(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            AirplaneTypeDAO airplaneTypeDAO = new AirplaneTypeDAO(conn);
            return airplaneTypeDAO.readById(id);
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

    public void addSeats(int id, int seats) throws SQLException {
        Connection conn = null;
        try {
            int originalSeats = readAirplaneTypeById(id).getMaxCapacity();
            String originalName = readAirplaneTypeById(id).getName();
            conn = connectionUtil.getConnection();
            AirplaneTypeDAO airplaneTypeDAO = new AirplaneTypeDAO(conn);
            airplaneTypeDAO.updateAirplaneType(new AirplaneType(id,
                    originalSeats + seats, originalName));
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

    public List<AirplaneType> readAllAirplaneTypes() throws SQLException {
        Connection conn = null;
        List<AirplaneType> result = new ArrayList<>();
        try {
            conn = connectionUtil.getConnection();
            AirplaneTypeDAO airplaneTypeDAO = new AirplaneTypeDAO(conn);
            result = airplaneTypeDAO.readAllAirplaneTypes();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            assert conn != null;
            conn.rollback();
        } finally {
            assert conn != null;
            conn.close();
        }
        return result;
    }

    public void updateSeats(int id, int seats) throws SQLException {
        Connection conn = null;
        try {
            String originalName = readAirplaneTypeById(id).getName();
            conn = connectionUtil.getConnection();
            AirplaneTypeDAO airplaneTypeDAO = new AirplaneTypeDAO(conn);
            airplaneTypeDAO.updateAirplaneType(new AirplaneType(id, seats, originalName));
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

    public void deleteSeats(int id, int seats) throws SQLException {
        Connection conn = null;
        try {
            int originalSeats = readAirplaneTypeById(id).getMaxCapacity();
            String originalName = readAirplaneTypeById(id).getName();
            conn = connectionUtil.getConnection();
            AirplaneTypeDAO airplaneTypeDAO = new AirplaneTypeDAO(conn);
            airplaneTypeDAO.updateAirplaneType(new AirplaneType(id,
                    originalSeats - seats, originalName));
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
     * Booking and Passenger section
     */

    public int addBooking(String confirmation) throws SQLException {
        Connection conn = null;
        int newId = -1;
        try {
            conn = connectionUtil.getConnection();
            BookingDAO bookingDAO = new BookingDAO(conn);
            newId = bookingDAO.addBooking(new Booking(-1,
                    1, confirmation));
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

    public void updateBooking(Booking booking) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            BookingDAO bookingDAO = new BookingDAO(conn);
            bookingDAO.updateBookingId(booking);
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

    public Booking readBookingById(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            BookingDAO bookingDAO = new BookingDAO(conn);
            return bookingDAO.readBookingsByBookingId(id);
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

    public void deleteBooking(Booking booking) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            BookingDAO bookingDAO = new BookingDAO(conn);
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

    public List<Booking> readAllBookings() throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            BookingDAO bookingDAO = new BookingDAO(conn);
            return bookingDAO.readAllBookings();
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

    public void addPassenger(Passenger passenger) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            PassengerDAO passengerDAO = new PassengerDAO(conn);
            passengerDAO.addPassenger(passenger);
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

    public void updatePassenger(Passenger passenger) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            PassengerDAO passengerDAO = new PassengerDAO(conn);
            passengerDAO.updatePassenger(passenger);
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

    public Passenger readPassengerByBookingId(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            PassengerDAO passengerDAO = new PassengerDAO(conn);
            return passengerDAO.readPassengerByBookingId(id);
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

    public void deletePassenger(Passenger passenger) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            PassengerDAO passengerDAO = new PassengerDAO(conn);
            passengerDAO.deletePassenger(passenger);
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

    public List<Passenger> readAllPassengers() throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            PassengerDAO passengerDAO = new PassengerDAO(conn);
            return passengerDAO.readAllPassengers();
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

    /**
     * Traveler and Employee Management Section
     */
    public void addUser(User user) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            UserDAO userDAO = new UserDAO(conn);
            userDAO.addUser(user);
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

    public void updateUser(User user) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            UserDAO userDAO = new UserDAO(conn);
            userDAO.updateUser(user);
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

    public void deleteUser(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            UserDAO userDAO = new UserDAO(conn);
            userDAO.deleteUser(id);
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

    public List<User> readAllTravelers() throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            UserDAO userDAO = new UserDAO(conn);
            return userDAO.readAllTravelers();
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

    public List<User> readAllEmployee() throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            UserDAO userDAO = new UserDAO(conn);
            return userDAO.readAllEmployees();
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

    /**
     * Airport Management Section
     */

    public void addAirport(Airport airport) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            AirportDAO airportDAO = new AirportDAO(conn);
            airportDAO.addAirport(airport);
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

    public void updateAirport(Airport airport) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            AirportDAO airportDAO = new AirportDAO(conn);
            airportDAO.updateAirport(airport);
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

    public void deleteAirport(String id) throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            AirportDAO airportDAO = new AirportDAO(conn);
            airportDAO.deleteAirport(id);
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
