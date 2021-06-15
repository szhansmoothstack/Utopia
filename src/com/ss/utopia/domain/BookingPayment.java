package com.ss.utopia.domain;

public class BookingPayment {
    private int bookingId;
    private String stripeId;
    private int refunded;

    public BookingPayment(int booking_id, String stripeId, int refunded) {
        this.bookingId = booking_id;
        this.stripeId = stripeId;
        this.refunded = refunded;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getStripeId() {
        return stripeId;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }

    public int getRefunded() {
        return refunded;
    }

    public void setRefunded(int refunded) {
        this.refunded = refunded;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        final BookingPayment other = (BookingPayment) o;
        return other.getBookingId() == (this.getBookingId())
                && other.getStripeId().equals(this.getStripeId())
                && other.getRefunded()==(this.getRefunded());
    }
}
