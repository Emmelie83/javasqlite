package com.emmeliejohansson.data.entities;

public class Customer {
    private int customerId;
    private String firstName = "";
    private String lastName = "";
    private String streetAddress = "";
    private String postalCode = "";
    private String city = "";
    private String country = "";
    private String telephone = "";
    private String email = "";
    private String vip = "";

    public Customer(String firstName, String lastName, String streetAddress, String postalCode, String city, String country, String telephone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.telephone = telephone;
        this.email = email;
    }

    public Customer(int customerId, String firstName, String lastName, String streetAddress, String postalCode, String city, String country, String telephone, String email, String vip) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.telephone = telephone;
        this.email = email;
        this.vip = vip;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getVip() {
        return vip;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }
}
