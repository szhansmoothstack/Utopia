package com.ss.utopia.domain;

public class FlightBookings {
    private int flightId;
    private int bookingId;

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public FlightBookings(int flightId, int bookingId) {
        this.flightId = flightId;
        this.bookingId = bookingId;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        final FlightBookings other = (FlightBookings) o;
        return other.getFlightId() == (this.getFlightId())
                && other.getBookingId()==(this.getBookingId());
    }
}
