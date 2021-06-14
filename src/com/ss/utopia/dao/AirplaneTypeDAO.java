package com.ss.utopia.dao;

import com.ss.utopia.domain.AirplaneType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneTypeDAO extends BaseDAO<AirplaneType> {

    public AirplaneTypeDAO(Connection conn) {
        super(conn);
    }

    public void addAirplaneType (AirplaneType airplaneType) throws SQLException {
        save("insert into airplane_type values (?, ?, ?)", new Object[] {
                airplaneType.getId(), airplaneType.getMaxCapacity(), airplaneType.getName()
        });
    }

    public void updateAirplaneType (AirplaneType airplaneType) throws SQLException {
        save("update airplane_type set max_capacity = ?, name = ? where id = ?",
                new Object[] {airplaneType.getMaxCapacity(), airplaneType.getName(),
                        airplaneType.getId()});
    }

    public void deleteAirplaneType (AirplaneType airplaneType) throws SQLException {
        save ("delete from airplane_type where id = ?", new Object[]{airplaneType.getId()});
    }

    public List<AirplaneType> readAllAirplaneTypes() throws SQLException, ClassNotFoundException {
        return read ("select * from airplane_type", null);
    }

    public AirplaneType readById (int id) throws SQLException, ClassNotFoundException {
        return read ("select * from airplane_type where id = ?", new Object[] {id}).get(0);
    }

    @Override
    public List<AirplaneType> extractData(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        List<AirplaneType> airplaneTypes = new ArrayList<>();

        while (resultSet.next()){
            AirplaneType airplaneType = new AirplaneType(resultSet.getInt ("id"),
                    resultSet.getInt("max_capacity"), resultSet.getString("name"));
            airplaneTypes.add(airplaneType);
        }
        return airplaneTypes;
    }
}
