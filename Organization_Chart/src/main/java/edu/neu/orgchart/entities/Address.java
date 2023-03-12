package edu.neu.orgchart.entities;

import java.util.Objects;

/**
 * Represents an address in the US, a US address includes an address number, a street, optionally a unit number,
 * city, and State.
 * You use this class in various places in this assignment, you may add to it, but you may not modify or remove items.
 */
public class Address {
    private String addressNo;
    private String streetName;
    private String unitNo;
    private String city;
    private String state;

    public Address(String addressNo, String streetName, String unitNo, String city, String state) {
        this.addressNo = addressNo;
        this.streetName = streetName;
        this.unitNo = unitNo;
        this.city = city;
        this.state = state;
    }

    public String getAddressNo() { return addressNo; }

    public void setAddressNo(String addressNo) {
        this.addressNo = addressNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(getAddressNo(), address.getAddressNo()) && Objects.equals(getStreetName(), address.getStreetName()) && Objects.equals(getUnitNo(), address.getUnitNo()) && Objects.equals(getCity(), address.getCity()) && Objects.equals(getState(), address.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddressNo(), getStreetName(), getUnitNo(), getCity(), getState());
    }
}
