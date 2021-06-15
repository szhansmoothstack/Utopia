package com.ss.utopia.domain;

import java.sql.Date;

public class Passenger {
    private int id;
    private int bookingId;
    private String givenName;
    private String familyName;
    private Date dob;
    private String gender;
    private String address;

    public Passenger(int id, int bookingId, String givenName, String familyName, Date dob, String gender, String address) {
        this.id = id;
        this.bookingId = bookingId;
        this.givenName = givenName;
        this.familyName = familyName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", bookingId=" + bookingId +
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        final Passenger other = (Passenger) o;
        return other.getId() == (this.getId());
    }
}
