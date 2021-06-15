package com.ss.utopia.dao;

import com.ss.utopia.domain.Airplane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneDAO extends BaseDAO<Airplane> {

    public AirplaneDAO(Connection conn) {
        super(conn);
    }

    public void addAirplane(Airplane a) throws SQLException {
        int newId = saveWithPk("insert into airplane (type_id) values (?)", new Object[]{
                a.getTypeID()
        });
        a.setId(newId);
    }

    public void updateAirplane(Airplane a) throws SQLException {
        save("update airplane set type_id = ? where id = ?",
                new Object[]{a.getTypeID(),
                        a.getId()});
    }

    public void deleteAirplane(Airplane a) throws SQLException {
        save("delete from airplane where id = ?", new Object[]{a.getId()});
    }

    public List<Airplane> readAllAirplanes() throws SQLException, ClassNotFoundException {
        return read("select * from airplane", null);
    }

    @Override
    public List<Airplane> extractData(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        List<Airplane> airplanes = new ArrayList<>();

        while (resultSet.next()) {
            Airplane airplane = new Airplane(resultSet.getInt("id"),
                    resultSet.getInt("type_id"));
            airplanes.add(airplane);
        }
        return airplanes;
    }
}
