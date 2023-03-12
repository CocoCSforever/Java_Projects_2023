package edu.neu.orgchart;

import edu.neu.orgchart.entities.Address;
import edu.neu.orgchart.entities.IndividualContributor;
import edu.neu.orgchart.entities.Manager;
import edu.neu.orgchart.entities.Employee;
import edu.neu.orgchart.search.FilterBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/*Notes:
1. add OrgChart constructor, initialize employees to an empty set(if null, cannot add e to the set)
2. add "setDirects(null)" and "setIsManager" to Manager constructor
Org.addEmployee() -> for(Employee e: manager.getDirects()) -> for each should guarantee the set is not null
3. check if employee has a manager, if null, cannot call addDirect on null
Org.addEmployee() -> employee.getManager().addDirect(employee)
4. org.removeEmployee(e), if e.getManager()==null, cannot call addDirect(e) or removeDirect(e)
 */
class OrgChartTest {
    OrgChart org;
    Address testAddress = new Address("1", "streetName1", "unit1", "city1", "state1");
    Manager a1 = new Manager("Bob", null,"Boss", testAddress);
    Manager b1 = new Manager("Mike", null,"Op", testAddress);
    Manager b2 = new Manager("Max", null,"Money", testAddress);
    Manager b3 = new Manager("Tom", null,"Tech", testAddress);
    Manager b4 = new Manager("Paula", null,"People", testAddress);
    Manager c1 = new Manager("Brad", null,"Pitt", testAddress);
    Manager c2 = new Manager("Kim", null,"Katrall", testAddress);
    IndividualContributor d1 = new IndividualContributor("Vinayak", null,"Rao", testAddress);
    IndividualContributor d2 = new IndividualContributor("Ian", null,"Gorton", testAddress);

    @BeforeEach
    void setUp() {
        org = new OrgChart();
        b1.setManager(a1);
        b2.setManager(a1);
        b3.setManager(a1);
        b4.setManager(a1);
        c1.setManager(b1);
        c2.setManager(b1);
        d1.setManager(c1);
        d2.setManager(c1);
    }

    // help to set the org as sample
    void setUpOrg(){
        org.addEmployee(a1);
        org.addEmployee(b1); org.addEmployee(b2);
        org.addEmployee(b3); org.addEmployee(b4);
        org.addEmployee(c1); org.addEmployee(c2);
        org.addEmployee(d1); org.addEmployee(d2);
    }

    @Test
    void addSingleEmployee() {
        org.addEmployee(a1);
        assertNull(a1.getManager());
        assertEquals(1, org.getEmployees().size());
        assertEquals(a1, org.getEmployees().iterator().next());
        org.addEmployee(b1);
        assertEquals(2, org.getEmployees().size());
        assertEquals(a1, b1.getManager());
        assertEquals(1, org.getDirectsCount(a1));
        assertEquals(0, org.getDirectsCount(b1));
        assertEquals(b1, a1.getDirects().iterator().next());
        org.addEmployee(c1);
        assertEquals(3, org.getEmployees().size());
        assertEquals(b1, c1.getManager());
        assertEquals(1, org.getDirectsCount(b1));
        assertEquals(0, org.getDirectsCount(c1));
        assertEquals(c1, b1.getDirects().iterator().next());

    }

    @Test
    void addMultipleEmployees() {
        setUpOrg();
        assertEquals(9, org.getEmployees().size());
        assertTrue(org.removeEmployee(b1));
        assertEquals(2, b1.getDirects().size());
        org.addEmployee(b1);
        HashSet<Employee> testSet1 = new HashSet<>();
        testSet1.add(b1); testSet1.add(b2);
        testSet1.add(b3); testSet1.add(b4);
        assertNull(a1.getManager());
        assertEquals(testSet1, a1.getDirects());
        HashSet<Employee> testSet2 = new HashSet<>();
        testSet2.add(c1); testSet2.add(c2);
        assertEquals(a1, b1.getManager());
        assertEquals(testSet2, b1.getDirects());
        HashSet<Employee> testSet3 = new HashSet<>();
        testSet3.add(d1); testSet3.add(d2);
        assertEquals(b1, c1.getManager());
        assertEquals(testSet3, c1.getDirects());
        assertEquals(c1, d1.getManager());
        assertFalse(d1.getIsManager());
    }

    @Test
    void removeSingleEmployee() {
        setUpOrg();
        assertTrue(org.removeEmployee(b1));
        assertEquals(8, org.getEmployees().size());
        HashSet<Employee> testSet1 = new HashSet<>();
        testSet1.add(b2); testSet1.add(b3);
        testSet1.add(b4); testSet1.add(c1);
        testSet1.add(c2);
        assertEquals(5, a1.getDirects().size());
        assertEquals(testSet1, a1.getDirects());
        assertFalse(org.removeEmployee(b1));
        assertEquals(a1, c1.getManager());
        assertEquals(a1, c2.getManager());

        assertTrue(org.removeEmployee(d2));
        assertEquals(7, org.getEmployees().size());
        HashSet<Employee> testSet2 = new HashSet<>();
        testSet2.add(d1);
        assertEquals(1, c1.getDirects().size());
        assertEquals(testSet2, c1.getDirects());
        assertFalse(org.removeEmployee(d2));
        assertEquals(c1, d1.getManager());
    }

    @Test
    void removeMultipleEmployees() {
        setUpOrg();
        assertTrue(org.removeEmployee(b1));
        assertTrue(org.removeEmployee(d1));
        assertTrue(org.removeEmployee(a1));
        assertEquals(6, org.getEmployees().size());
        assertNull(b2.getManager());
        assertNull(b3.getManager());
        assertNull(b4.getManager());
        assertNull(c1.getManager());
        assertNull(c2.getManager());
        assertEquals(d2, c1.getDirects().iterator().next());
    }

    @Test
    void filterSingleAttribute() {
        setUpOrg();
        FilterBuilder.Filter filter = new FilterBuilder().city("city1").build();
        assertEquals(org.getEmployees(), org.filterEmployee(filter));
        filter = new FilterBuilder().reportsTo(a1).build();

        HashSet<Employee> testSet1 = new HashSet<>();
        testSet1.add(b1); testSet1.add(b2);
        testSet1.add(b3); testSet1.add(b4);
        assertEquals(testSet1, org.filterEmployee(filter));

        filter = new FilterBuilder().reportsTo(b1).build();
        HashSet<Employee> testSet2 = new HashSet<>();
        testSet2.add(c1); testSet2.add(c2);
        assertEquals(testSet2, org.filterEmployee(filter));

        filter = new FilterBuilder().reportsTo(c1).build();
        HashSet<Employee> testSet3 = new HashSet<>();
        testSet3.add(d1); testSet3.add(d2);
        assertEquals(testSet3, org.filterEmployee(filter));

        filter = new FilterBuilder().reportsTo(b4).build();
        HashSet<Employee> testSet4 = new HashSet<>();
        assertEquals(testSet4, org.filterEmployee(filter));
    }

    @Test
    void filterMultipleAttributes() {
        setUpOrg();
        FilterBuilder.Filter filter = new FilterBuilder().city("city1").state("state1").reportsTo(a1).payEqualTo(new BigDecimal(1000)).build();
        HashSet<Employee> testSet1 = new HashSet<>();
        testSet1.add(b1); testSet1.add(b2);
        testSet1.add(b3); testSet1.add(b4);
        assertEquals(testSet1, org.filterEmployee(filter));

        filter = new FilterBuilder().city("city1").state("state1").reportsTo(a1).bonusEqualTo(0.1).build();
        assertEquals(testSet1, org.filterEmployee(filter));

        filter = new FilterBuilder().city("city1").reportsTo(a1).payEqualTo(new BigDecimal(100)).build();
        HashSet<Employee> testSet2 = new HashSet<>();
        assertEquals(testSet2, org.filterEmployee(filter));
    }

    @Test
    void getDirectsCount() {
        setUpOrg();
        assertEquals(4, org.getDirectsCount(a1));
        assertEquals(2, org.getDirectsCount(b1));
        assertEquals(2, org.getDirectsCount(c1));
    }

    @Test
    void getEmployees() {
        setUpOrg();
        assertEquals(9, org.getEmployees().size());
        assertTrue(org.getEmployees().contains(a1));
        assertTrue(org.getEmployees().contains(b3));
        assertTrue(org.getEmployees().contains(c2));
        assertTrue(org.getEmployees().contains(d1));
    }
}