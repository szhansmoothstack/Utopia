package com.ss.utopia.dao;

import com.ss.utopia.domain.AirplaneType;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirportDAOTest {
    ConnectionUtil connectionUtil = new ConnectionUtil();
    AirportDAO airportDAO;
    @Test
    void generalTest() throws SQLException {
        Connection conn = null;
        try{
            conn = connectionUtil.getConnection();
            airportDAO = new AirportDAO(conn);
            Airport a = new Airport("ABC", "TestCity", null);
            airportDAO.addAirport(a);
            List<Airport> airports = airportDAO.readAllAirports();
            assertTrue(airports.contains(a));

            a.setCity("Testcity2");
            airportDAO.updateAirport(a);
            airports = airportDAO.readAllAirports();
            assertEquals("Testcity2", airports.get(airports.indexOf(a)).getCity());

            airportDAO.deleteAirport(a.getAirportCode());
            airports = airportDAO.readAllAirports();
            assertFalse (airports.contains(a));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            assert conn != null;
            conn.rollback();
        } finally {
            assert conn != null;
            conn.close();
        }
    }

}