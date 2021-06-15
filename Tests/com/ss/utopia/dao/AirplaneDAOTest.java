package com.ss.utopia.dao;

import com.ss.utopia.domain.Airplane;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class AirplaneDAOTest {
    ConnectionUtil connectionUtil = new ConnectionUtil();
    AirplaneDAO airplaneDAO;

    @Test
    void generalTest() throws SQLException {
        Connection conn = null;
        try {
            conn = connectionUtil.getConnection();
            airplaneDAO = new AirplaneDAO(conn);
            Airplane a = new Airplane(-1, 74);
            airplaneDAO.addAirplane(a);
            List<Airplane> airplanes = airplaneDAO.readAllAirplanes();
            //Tests add and read methods
            Assertions.assertTrue (airplanes.contains(a));

            a.setTypeID(42);
            airplaneDAO.updateAirplane(a);
            a.setTypeID(74);
            airplanes = airplaneDAO.readAllAirplanes();
            Assertions.assertFalse (airplanes.contains(a));

            airplaneDAO.deleteAirplane(a);
            airplanes = airplaneDAO.readAllAirplanes();
            Assertions.assertFalse (airplanes.contains(a));
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