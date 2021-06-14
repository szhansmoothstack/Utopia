package com.ss.utopia.domain;

public class BookingUser {
    private int bookingId;
    private int userId;

    public BookingUser(int booking_id, int user_id) {
        this.bookingId = booking_id;
        this.userId = user_id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
