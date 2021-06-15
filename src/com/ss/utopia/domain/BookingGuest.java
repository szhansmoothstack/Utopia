package com.ss.utopia.domain;

public class BookingGuest {
    private int bookingId;
    private String contactEmail;
    private String contactPhone;

    public BookingGuest(int bookingId, String contactEmail, String contactPhone) {
        this.bookingId = bookingId;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        final BookingGuest other = (BookingGuest) o;
        return other.getBookingId() == (this.getBookingId())
                && other.getContactEmail().equals(this.getContactEmail())
                && other.getContactPhone().equals(this.getContactPhone());
    }
}
