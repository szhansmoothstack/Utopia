package com.ss.utopia.dao;

import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportDAO extends BaseDAO<Airport>{

    public AirportDAO(Connection conn) {
        super(conn);
    }

    public void addAirport (Airport airport) throws SQLException {
        save("insert into airport values (?, ?)", new Object[] {
                airport.getAirportCode(), airport.getCity()
        });
    }

    public void updateAirport (Airport airport) throws SQLException {
        save("update airport set city = ? where iata_id = ?",
                new Object[] {airport.getCity(), airport.getAirportCode()});
    }

    public void deleteAirport (String id) throws SQLException {
        save ("delete from airport where iata_id = ?", new Object[]{id});
    }

    public List<Airport> readAllAirports() throws SQLException, ClassNotFoundException {
        return read ("select * from airport", null);
    }

    @Override
    public List<Airport> extractData(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        List<Airport> airports = new ArrayList<>();

        while (resultSet.next()){
            Airport airport = new Airport(resultSet.getString ("iata_id"),
                    resultSet.getString("city"),null);
            airports.add(airport);
        }
        return airports;
    }
}
