package telran.employee.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.employee.dao.Company;
import telran.employee.dao.CompanyImpl;
import telran.employee.model.Employee;
import telran.employee.model.Manager;
import telran.employee.model.SalesManager;
import telran.employee.model.WageEmployee;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    private Company company;
    private Employee[] employees;
    private final int capacity = 5;
    private final Comparator<Employee> comparator = (e1,e2) -> Integer.compare(e1.getId(),e2.getId());

 @BeforeEach
    void setUp() {
        company = new CompanyImpl(capacity);
        employees = new Employee[4];
        employees[0] = new Manager(1000, "John","Smith", 182, 20_000, 20);
        employees[1] = new WageEmployee(2000, "Mary", "Smith", 182, 40);
        employees[2] = new SalesManager(3000, "Peter", "Jackson", 182, 40_000, 10);
        employees[3] = new SalesManager(4000, "Rabindranate", "Agrawal", 91, 80_000, 10);
        for(int i = 0; i < employees.length;i++){
            company.addEmployee(employees[i]);
        }
    }

    @Test
    void testAddEmployee() {
        assertFalse(company.addEmployee(null));
        assertFalse(company.addEmployee(employees[2]));
        Employee employee = new SalesManager(5000, "Peter", "Jackson", 182, 40_000, 10);
        assertTrue(company.addEmployee(employee));
        assertEquals(5,company.quantity());
        employee = new SalesManager(6000, "Peter", "Jackson", 182, 40_000, 10);
        assertFalse(company.addEmployee(employee));
        }

    @Test
    void testRemoveEmployee() {
        Employee employee = company.removeEmployee(3000);
        assertEquals(employees[2], employee);
        assertEquals(3, company.quantity());
        assertNull(company.removeEmployee(3000));


    }

    @Test
    void testFindEmployee() {
        assertEquals(employees[2], company.findEmployee(3000));
        assertNull(company.findEmployee(5000));
    }

    @Test
    void testQuantity() {
        assertEquals(4, company.quantity());
    }

    @Test
    void testTotalSalary() {
        assertEquals(44380.0, company.totalSalary(), 0.01);
    }

    @Test
    void testAvgSalary() {
        assertEquals(44380.0 / 4, company.avgSalary(), 0.01);
    }

    @Test
    void testTotalSales() {
        assertEquals(120_000, company.totalSales(), 0.01);
    }

    @Test
    void testPrintEmployees() {
        company.printEmployees();
    }

    @Test
    void testFindEmployeesHoursGreaterThan(){
     Employee[] actual = company.findEmployeesHoursGreaterThan(100);
        Arrays.sort(actual,comparator);
     Employee[] expected = {employees[0], employees[1], employees[2]};
     assertArrayEquals(expected, actual);

    }

    @Test
    void testFindEmployeesSalaryBetween(){
        Employee[] actual = company.findEmployeesSalaryBetween(5000, 8000);
        Employee[] expected = {employees[1], employees[2]};
        Arrays.sort(actual,comparator);
        assertArrayEquals(expected, actual);
    }
}