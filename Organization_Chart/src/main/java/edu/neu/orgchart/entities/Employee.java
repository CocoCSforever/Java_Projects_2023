package edu.neu.orgchart.entities;

import edu.neu.orgchart.interfaces.Employable;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * This class represents an employee, there are two types of employees in other classes. Individual Contributors(IC) and
 * Managers. Feel free to modify this class as needed to support the assignment. It extends Person which represents a
 * generic person and implements Employable. You are required to implement the functions here. These functions should
 * work for IC's or managers which is why they are at this level.
 */
public abstract class Employee extends Person implements Employable {
    private BigDecimal yearlyBasePay;
    private double yearlyBonusPercentage;
    private UUID id;
    private boolean isManager;
    private Manager manager;

    protected Employee(String firstName, String middleName, String lastName, Address address){
        super(firstName, middleName, lastName, address);
        id = UUID.randomUUID();
        yearlyBasePay = new BigDecimal(1000);
        yearlyBonusPercentage = 0.1;
    }

    @Override
    public BigDecimal getYearlyBasePay() {
        return yearlyBasePay;
    }

    @Override
    public double getYearlyBonusPercentage() {
        return yearlyBonusPercentage;
    }

    @Override
    public Address getResidence() {
        return getAddress();
    }

    @Override
    public Person getEmergencyContact() {
        return null;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() { return manager;}

    public void setIsManager(boolean i) { isManager = i;}

    public boolean getIsManager() { return isManager;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                '}';
    }
}