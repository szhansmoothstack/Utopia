package com.ss.utopia.domain;

import java.util.List;

public class Airport {
    public Airport(String airportCode, String city, List<Route> routeList) {
        this.airportCode = airportCode;
        this.city = city;
        this.routeList = routeList;
    }

    private String airportCode;
    private String city;
    private List<Route> routeList;

    public Airport() {
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportCode='" + airportCode + '\'' +
                ", city='" + city + '\'' +
                ", routeList=" + routeList +
                '}';
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

}
