package com.ss.utopia.domain;

import java.sql.Timestamp;

public class Flight {
    private int id;
    private int routeId;
    private int airplaneID;
    private Timestamp departureTime;
    private int reservedSeats;
    private float seatPrice;

    public Flight(int id, int routeId, int airplane_id, Timestamp departure_time, int reservedSeats, float seat_price) {
        this.id = id;
        this.routeId = routeId;
        this.airplaneID = airplane_id;
        this.departureTime = departure_time;
        this.reservedSeats = reservedSeats;
        this.seatPrice = seat_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getAirplane_id() {
        return airplaneID;
    }

    public void setAirplane_id(int airplane_id) {
        this.airplaneID = airplane_id;
    }

    public Timestamp getDeparture_time() {
        return departureTime;
    }

    public void setDeparture_time(Timestamp departure_time) {
        this.departureTime = departure_time;
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(int reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public float getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(float seatPrice) {
        this.seatPrice = seatPrice;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", routeId=" + routeId +
                ", airplaneID=" + airplaneID +
                ", departureTime=" + departureTime +
                ", reservedSeats=" + reservedSeats +
                ", seatPrice=" + seatPrice +
                '}';
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        final Flight other = (Flight) o;
        return other.getId() == (this.getId())
                && other.getRouteId()==(this.getRouteId())
                && other.getAirplane_id()==(this.getAirplane_id())
                && other.getDeparture_time().equals(this.getDeparture_time())
                && other.getReservedSeats()==(this.getReservedSeats())
                && other.getSeatPrice()==(this.getSeatPrice());
    }
}
