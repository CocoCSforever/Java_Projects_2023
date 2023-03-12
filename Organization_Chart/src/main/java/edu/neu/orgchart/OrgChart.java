package edu.neu.orgchart;

import edu.neu.orgchart.entities.Employee;

import java.util.HashSet;
import java.util.Set;

import edu.neu.orgchart.entities.Manager;
import edu.neu.orgchart.search.FilterBuilder;

/**
 * A class that represents an org chart for a company. It starts with the CEO and moves down from there. Each manager
 * can have multiple manager and individual contributors (IC's). Each employee has an address, pay, bonus, and
 * emergency contact.  The org chart visually looks something like this.
 *
 *                                            Bob Boss (CEO)
 *                                            /        |    \
 *                                          / |        |     \
 *                                        /   |        |      \
 *                                      /     |        |       \
 *                                    /       |        |        \
 *                                Mike Op  Max Money Tom Tech  Paula People
 *                                 (COO)     (CFO)    (CTO)     (CPO)
 *                               /     \
 *                              /       \
 *                             /         \
 *                            /           \
 *                           /             \
 *                      Brad Pitt       Kim Katrall
 *                      (VP)                (VP)
 *                     /     |
 *                    /      |
 *                   /       |
 *                  /        |
 *            Vinayak Rao   Ian Gorton
 *              (SDE)         (SDE)
 */
public class OrgChart  {
    private Set<Employee> employees;

    public OrgChart() { this.employees = new HashSet<>(); }

    /**
     * Adds an employee to the org chart, you are required to implement this method.
     * @param employee The employee to add, could be a manager or an individual contributor
     */
    public void addEmployee(Employee employee) {
        if(employee.getIsManager()){
            Manager manager = (Manager)employee;
            for(Employee e: manager.getDirects()){
                e.getManager().removeDirect(e); // remove e from previous manager's directs set
                e.setManager(manager); // set e's manager to this manager
            }
        }
        if(employee.getManager() != null){employee.getManager().addDirect(employee);} // add this employee to its manager's directs set
        employees.add(employee); // add employee to org's set
    }

    /**
     * Removes an employee from the org chart, you are required to implement this method.
     * @param employee The employee to remove, could be a manager or an individual contributor.
     * @return Returns true if the removal succeeds, false if the employee doesn't exist.
     */
    public boolean removeEmployee(Employee employee) {
        Manager manager = employee.getManager();
        if(manager == null) {
            if(employee.getIsManager()){
                for(Employee e: ((Manager)employee).getDirects()){
                    e.setManager(null);
                }
            }
        }else{
            manager.removeDirect(employee);
            if(employee.getIsManager()){
                for(Employee e: ((Manager)employee).getDirects()){
                    e.setManager(manager);
                    manager.addDirect(e);
                }
            }
        }
        return employees.remove(employee); // set.remove will return true/false
    }

    /**
     * Returns a set of employees that meet the criteria passed in. Each of the criteria is an exact equal to except
     * pay and bonus which are greater than and less than based on the variable name. Any criteria which is null can be
     * ignored and should not be filtered on. The filter has multiple files defined in the FilterBuilder class.
     * @return A set of employees that matches the filters passed in.
     */

    public Set<Employee> filterEmployee(FilterBuilder.Filter filter) {
        HashSet<Employee> res = new HashSet<>();
        for(Employee e: employees){
            if(filter.getCity() != null && filter.getCity() != e.getAddress().getCity()) continue;
            if(filter.getState() != null && filter.getState() != e.getAddress().getState()) continue;
            if(filter.getPayLessThan() != null && filter.getPayLessThan().compareTo(e.getYearlyBasePay()) <= 0) continue;
            if(filter.getPayEqualTo() != null && filter.getPayEqualTo().compareTo(e.getYearlyBasePay()) != 0) continue;
            if(filter.getPayGreaterThan() != null && filter.getPayGreaterThan().compareTo(e.getYearlyBasePay()) >= 0) continue;
            if(filter.getBonusLessThan() != null && filter.getBonusLessThan().compareTo(e.getYearlyBonusPercentage()) <= 0) continue;
            if(filter.getBonusEqualTo() != null && filter.getBonusEqualTo().compareTo(e.getYearlyBonusPercentage()) != 0) continue;
            if(filter.getBonusGreaterThan() != null && filter.getBonusGreaterThan().compareTo(e.getYearlyBonusPercentage()) >= 0) continue;
            if(filter.getReportsTo() != null && !filter.getReportsTo().equals(e.getManager())) continue;
            res.add(e);
        }
        return res;
    }

    /**
     * Gets the number of directs the employee has. For IC's it should return 0 and it should not count the employee
     * themselves
     * @param employee The employee to verify,
     * @return
     */
    public int getDirectsCount(Employee employee) {
        return employee.getIsManager()? ((Manager)employee).getDirects().size():0;
    }

    public Set<Employee> getEmployees(){
        return employees;
    }
}
