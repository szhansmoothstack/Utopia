package com.ss.utopia.domain;

public class BookingAgent {
    private int bookingId;
    private int agentId;

    public BookingAgent(int bookingId, int agentId) {
        this.bookingId = bookingId;
        this.agentId = agentId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    @Override
    public boolean equals (Object o){
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        final BookingAgent other = (BookingAgent) o;
        return other.getAgentId() == (this.getAgentId())
                && other.getBookingId() ==(this.getBookingId());
    }
}
