package com.emmeliejohansson.data.entities;

public class Booking {

    private int bookingId;
    private String bookingDate;
    private String pickupDate;
    private String returnDate;
    private int pricePerDay;
    private int customerId;
    private String carRegNr;

    public Booking(String bookingDate, String pickupDate, String returnDate, int pricePerDay, int customerId, String carRegNr) {
        this.bookingDate = bookingDate;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.pricePerDay = pricePerDay;
        this.customerId = customerId;
        this.carRegNr = carRegNr;
    }

    public Booking(int bookingId, String bookingDate, String pickupDate, String returnDate, int pricePerDay, int customerId, String carRegNr) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.pricePerDay = pricePerDay;
        this.customerId = customerId;
        this.carRegNr = carRegNr;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getCarRegNr() {
        return carRegNr;
    }

    public void setCarRegNr(String carRegNr) {
        this.carRegNr = carRegNr;
    }

    public int getCustomerId() {
        return customerId;
    }
}
