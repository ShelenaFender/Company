package telran.employee.dao;

import telran.employee.model.Employee;
import telran.employee.model.SalesManager;

import java.util.Arrays;
import java.util.function.Predicate;

public class CompanyImpl implements Company{
    private Employee[] employees;
    private int size;

    public CompanyImpl(int capacity){
        employees = new Employee[capacity];

    }

    @Override
    public boolean addEmployee(Employee employee) {
        if (employee == null || size == employees.length || findEmployee(employee.getId()) != null){
            return false;
        }
        employees[size++] = employee;
        return true;
    }

    @Override
    public Employee removeEmployee(int id) {
        Employee victim = null;
        for (int i = 0; i < size; i++) {
            if (employees[i].getId() == id) {
                victim = employees[i];
                System.arraycopy(employees, i + 1, employees, i, size - i - 1);
                    employees[--size] = null;
                break;
            }
        }
        return victim;
    }

    @Override
    public Employee findEmployee(int id) {
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] != null && employees[i].getId() == id){
                return employees[i];
            }
        }
        return null;
    }

    @Override
    public int quantity() {
        return size;
    }

    @Override
    public double totalSalary() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += employees[i].calcSalary();

        }
        return sum;
    }


    @Override
    public double totalSales() {
        double sum = 0;
        double totalSales;
        for (int i = 0; i < size; i++) {
            if (employees[i] instanceof SalesManager) {
                SalesManager salesManager = (SalesManager) employees[i];
                sum += salesManager.getSalesValue();
            }
        }
        return sum;
    }

    @Override
    public void printEmployees() {
        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i]);
        }

    }

    @Override
    public Employee[] findEmployeesHoursGreaterThan(int hours) {
        return findEmployeesByPredicate(e -> e.getHours() > hours);
    }

    @Override
    public Employee[] findEmployeesSalaryBetween(int minSalary, int maxSalary) {
        Predicate<Employee> predicate = e -> e.calcSalary() >= minSalary && e.calcSalary() < maxSalary;

        return findEmployeesByPredicate(predicate);
    }

    private Employee[] findEmployeesByPredicate(Predicate<Employee> predicate){
        Employee[] res = new Employee[size];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if(predicate.test(employees[i])){
                res[j++] = employees[i];
            }
        }

        return Arrays.copyOf(res, j);
    }

}
