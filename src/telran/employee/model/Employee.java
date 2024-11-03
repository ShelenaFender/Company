package telran.employee.model;

public abstract class Employee {
    protected static double minWage = 30;
    protected final int id;
    protected String firstName;
    protected String lastName;
    protected double hours;



    public Employee(int id, String firstName, String lastName, double hours, double minWage) {
        Employee.minWage = minWage;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hours = hours;
    }

    public static double getMinWage() {
        return minWage;
    }

    public static void setMinWage(double minWage) {
        Employee.minWage = minWage;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public abstract double calcSalary();



    protected double fixSalary(double salary){
        if (salary < hours * minWage){
            salary = hours * minWage;
        }
        return salary;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hours=" + hours + '\'' +
                ", salary=" + calcSalary();
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Employee employee)) return false;
//      if(!(object instanceof Employee)) {
//      return false;
//      }
//        Employee employee = (Employee) object;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
