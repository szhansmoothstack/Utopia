package com.ss.utopia.domain;

public class Booking {
    private int id;
    private int isActive;
    private String confirmationCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public Booking(int id, int is_active, String confirmationCode) {
        this.id = id;
        this.isActive = is_active;
        this.confirmationCode = confirmationCode;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", confirmationCode='" + confirmationCode + '\'' +
                '}';
    }

    @Override
    public boolean equals (Object o){
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        final Booking other = (Booking) o;
        return other.getId() == (this.getId())
                && other.getIsActive() == (this.getIsActive())
                && other.getConfirmationCode().equals(this.getConfirmationCode());
    }
}
