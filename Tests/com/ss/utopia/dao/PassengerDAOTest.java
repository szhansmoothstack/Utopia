package com.ss.utopia.dao;

import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Passenger;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PassengerDAOTest {
    ConnectionUtil connectionUtil = new ConnectionUtil();
    PassengerDAO passengerDAO;
    @Test
    void generalTest() throws SQLException {
        Connection conn = null;
        try{
            conn = connectionUtil.getConnection();
            passengerDAO = new PassengerDAO(conn);
            Passenger a = new Passenger(100,32 , "Vida" ,
                    "Lahde" , Date.valueOf("1987-06-04") ,
                    "Female" , "8565 San Juan Street Lynnwood, WA 98037");
            passengerDAO.addPassenger(a);
            List<Passenger> passengers = passengerDAO.readAllPassengers();
            assertTrue(passengers.contains(a));

            a.setAddress("Testcity2");
            passengerDAO.updatePassenger(a);
            passengers = passengerDAO.readAllPassengers();
            assertEquals("Testcity2", passengers.get(passengers.indexOf(a)).getAddress());

            passengerDAO.deletePassenger(a);
            passengers = passengerDAO.readAllPassengers();
            assertFalse (passengers.contains(a));
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