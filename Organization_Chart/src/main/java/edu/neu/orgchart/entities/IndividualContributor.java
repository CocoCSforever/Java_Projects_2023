package edu.neu.orgchart.entities;

/**
 * Represents an individual contributor (IC). IC's have a manager, you need to implement the function returning the IC's
 * manager.
 */
public class IndividualContributor extends Employee{
    public IndividualContributor(String firstName, String middleName, String lastName, Address address){
        super(firstName, middleName, lastName, address);
        setIsManager(false);
    }


}
