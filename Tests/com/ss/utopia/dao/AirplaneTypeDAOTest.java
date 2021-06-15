package com.ss.utopia.dao;

import com.ss.utopia.domain.AirplaneType;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirplaneTypeDAOTest {
    ConnectionUtil connectionUtil = new ConnectionUtil();
    AirplaneTypeDAO airplaneTypeDAO;

    @Test
    void generalTest() throws SQLException {
        Connection conn = null;
    try{
        conn = connectionUtil.getConnection();
        airplaneTypeDAO = new AirplaneTypeDAO(conn);
        AirplaneType a = new AirplaneType(787, 300, "Boeing");
        airplaneTypeDAO.addAirplaneType(a);
        List<AirplaneType> airplaneTypes = airplaneTypeDAO.readAllAirplaneTypes();
        assertTrue(airplaneTypes.contains(a));

        a.setMaxCapacity(350);
        airplaneTypeDAO.updateAirplaneType(a);
        airplaneTypes = airplaneTypeDAO.readAllAirplaneTypes();
        assertEquals(350, airplaneTypes.get(airplaneTypes.indexOf(a)).getMaxCapacity());

        AirplaneType other = airplaneTypeDAO.readById(a.getId());
        assertEquals(other, a);

        airplaneTypeDAO.deleteAirplaneType(a);
        airplaneTypes = airplaneTypeDAO.readAllAirplaneTypes();
        assertFalse (airplaneTypes.contains(a));
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