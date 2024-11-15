package edu.jsu.mcis.cs310.tas_fa24;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an employee in the Time and Attendance System.
 * This class contains details about an employee, such as their personal
 * information, department, shift, and employment type.
 */
public class Employee {

    /**
     * The unique identifier of the employee.
     */
    final private int id;

    /**
     * The first name of the employee.
     */
    final private String firstname;

    /**
     * The middle name of the employee.
     */
    final private String middlename;

    /**
     * The last name of the employee.
     */
    final private String lastname;

    /**
     * The date and time when the employee became active.
     */
    final private LocalDateTime active;

    /**
     * The badge associated with the employee.
     */
    final private Badge badge;

    /**
     * The department to which the employee belongs.
     */
    final private Department department;

    /**
     * The shift assigned to the employee.
     */
    final private Shift shift;

    /**
     * The type of employment (e.g., full-time or part-time).
     */
    final private EmployeeType employeetype;

    /**
     * Constructs an Employee object with the specified attributes.
     * 
     * @param id the unique identifier of the employee
     * @param firstname the first name of the employee
     * @param middlename the middle name of the employee
     * @param lastname the last name of the employee
     * @param active the date and time when the employee became active
     * @param badge the badge associated with the employee
     * @param department the department to which the employee belongs
     * @param shift the shift assigned to the employee
     * @param employeetype the type of employment
     */
    public Employee(int id, String firstname, String middlename, String lastname, LocalDateTime active, Badge badge, Department department, Shift shift, EmployeeType employeetype) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.active = active;
        this.badge = badge;
        this.department = department;
        this.shift = shift;
        this.employeetype = employeetype;
    }

    /**
     * Returns the unique identifier of the employee.
     * 
     * @return the employee ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the first name of the employee.
     * 
     * @return the first name of the employee
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Returns the middle name of the employee.
     * 
     * @return the middle name of the employee
     */
    public String getMiddlename() {
        return middlename;
    }

    /**
     * Returns the last name of the employee.
     * 
     * @return the last name of the employee
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Returns the date and time when the employee became active.
     * 
     * @return the activation date and time of the employee
     */
    public LocalDateTime getActive() {
        return active;
    }

    /**
     * Returns the badge associated with the employee.
     * 
     * @return the badge of the employee
     */
    public Badge getBadge() {
        return badge;
    }

    /**
     * Returns the department to which the employee belongs.
     * 
     * @return the department of the employee
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Returns the shift assigned to the employee.
     * 
     * @return the shift of the employee
     */
    public Shift getShift() {
        return shift;
    }

    /**
     * Returns the type of employment of the employee.
     * 
     * @return the employment type of the employee
     */
    public EmployeeType getEmployeetype() {
        return employeetype;
    }

    /**
     * Returns a string representation of the employee, including their
     * personal information, department, badge ID, and activation date.
     * 
     * @return a formatted string representing the employee
     */
    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        s.append("ID #").append(Integer.toString(id)).append(": ");
        s.append(lastname).append(", ").append(firstname).append(" ").append(middlename.substring(0, 1)).append(" ");
        s.append("(#").append(badge.getId()).append("), ");
        s.append("Type: ").append(employeetype);
        s.append(", Department: ");
        s.append(department.getDescription());
        s.append(", Active: ");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
        String formattedDate = active.format(dateFormatter);
        s.append(formattedDate);

        return s.toString();
    }
}
