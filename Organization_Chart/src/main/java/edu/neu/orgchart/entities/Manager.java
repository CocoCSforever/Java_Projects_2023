package edu.neu.orgchart.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a manager who manages managers and individual contributors (IC's). You must implement the function that
 * returns a set of employees, manager and IC's that report to this manager.
 */
public class Manager extends Employee {
    private Set<Employee> directs;
    public Manager(String firstName, String middleName, String lastName, Address address){
        super(firstName, middleName, lastName, address);
        setIsManager(true);
        setDirects(null);
    }
    public Set<Employee> getDirects() { return directs; }

    public void setDirects(HashSet<Employee> directs) {
        if(directs == null || directs.size()== 0){this.directs = new HashSet<>();}
        else{ this.directs = directs; }
    }
    public void addDirects(HashSet<Employee> directs) {
        this.directs.addAll(directs);
    }
    public void addDirect(Employee e) { directs.add(e); }

    public boolean removeDirect(Employee e) { return directs.remove(e); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager manager)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getDirects(), manager.getDirects());
    }

    // two sets that contain the same elements but in a different order will have different hash codes.
    // thus if add getDirects into hash function, will get different results for the same manager.
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), getDirects());
//    }
}
