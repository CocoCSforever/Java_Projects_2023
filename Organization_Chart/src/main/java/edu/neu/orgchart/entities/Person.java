package edu.neu.orgchart.entities;

import java.util.Objects;

/**
 * Represents a generic person with their personal information. All employees are people. The Employee class extends
 * person. You are not allowed to remove or modify items in this class. You use this class in various places in this
 * assignment, you may add to it, but you may not modify or remove items.
 */
public class Person {
    // subclass can only use getter to have access to private variables
    // protected for package
    private String firstName;
    private String middleName;
    private String lastName;
    private Address address;

    // subclass may call the constructor and doesn't require the constructor to be public,
    // but we cannot instantiate Person(abstract).
    protected Person(String firstName, String middleName, String lastName, Address address){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(getFirstName(), person.getFirstName()) && Objects.equals(getMiddleName(), person.getMiddleName()) && Objects.equals(getLastName(), person.getLastName()) && Objects.equals(getAddress(), person.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getMiddleName(), getLastName(), getAddress());
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                '}';
    }
}
