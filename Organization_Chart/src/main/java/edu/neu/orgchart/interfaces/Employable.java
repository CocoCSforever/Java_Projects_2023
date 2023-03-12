package edu.neu.orgchart.interfaces;

import edu.neu.orgchart.entities.Address;
import edu.neu.orgchart.entities.Person;

import java.math.BigDecimal;

/**
 * An interface to replace anyone who is employable. The functions here should apply to anyone who is employable.
 */
// share behaviors between objects
public interface Employable {
    //public no matter what we add as identifier
    /**
     * Gets the base pay for this employable object, represented as a big decimal to make sure we can represent currency.
     * @return The base pay for this employable object.
     */
    BigDecimal getYearlyBasePay();

    /**
     * Gets a bonus percentage that the employable object gets. The percentage should be between 0 and 100 as a double.
     * @return A double between 0 and 100 that returns the bonus percentage.
     */
    double getYearlyBonusPercentage();

    /**
     * Returns the address where the employable object lives, their residence should be included in this address.
     * @return Returns the address where this employable object lives.
     */
    Address getResidence();

    /**
     * Gets an emergency contact for the employable object. This can represent any person and may or may not be an employee.
     * @return A person representing the emergency contact.
     */
    Person getEmergencyContact();
}
