package com.ss.utopia.dao;

import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO extends BaseDAO<Route>{

    public RouteDAO(Connection conn) {
        super(conn);
    }

    public void addRoute (Route route) throws SQLException {
        save("insert into route (origin_id, destination_id) values (?, ?)", new Object[] {
                route.getOrigin(), route.getDestination()
        });
    }

    public void updateRoute (Route route) throws SQLException {
        save("update route set origin_id = ?, destination_id = ? where id = ?",
                new Object[] {route.getOrigin(),
                route.getDestination(), route.getId()});
    }

    public void deleteRoute (Route route) throws SQLException {
        save ("delete from route where id = ?", new Object[]{route.getId()});
    }

    public List<Route> readAllRoute() throws SQLException, ClassNotFoundException {
        return read ("select * from route", null);
    }

    public Route readRouteById(int id) throws SQLException, ClassNotFoundException {
        return read ("select * from route where id = ?", new Object[] {id}).get(0);
    }

    public List<Route> readRoutesByAirportCode (String airportCode) throws SQLException, ClassNotFoundException {
        return read ("select * from route where destination_id = ? or origin_id = ?",
                new Object[] {airportCode, airportCode});
    }

    public boolean doesExist(Route route) throws SQLException, ClassNotFoundException {
        return readAllRoute().contains(route);
    }

    @Override
    public List<Route> extractData(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        List<Route> routes = new ArrayList<>();

        while (resultSet.next()){
            Route route = new Route(resultSet.getInt("id"),resultSet.getString("origin_id"),
                    resultSet.getString("destination_id"));
            routes.add(route);
        }
        return routes;
    }
}
