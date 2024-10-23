package edu.jsu.mcis.cs310.tas_fa24;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Employee {
    final private int id;
    final private String firstname;
    final private String middlename;
    final private String lastname;
    final private LocalDateTime active; 
    final private Badge badge;
    final private Department department;
    final private Shift shift;
    final private EmployeeType employeetype;
    
    
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
    
    public int getId() {
        return id;
    }
    public String getFirstname(){
        return firstname;
    }
    public String getMiddlename(){
        return middlename;
    }
    public String getLastname(){
        return lastname;
    }
    public LocalDateTime getActive(){
        return active;
    }
    public Badge getBadge(){
        return badge;
    }
    public Department getDepartment(){
        return department;
    }
    public Shift getShift(){
        return shift;
    }
    public EmployeeType getEmployeetype(){
        return employeetype;
    }
    
    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        
        s.append("ID #").append(Integer.toString(id)).append(": ");
        s.append(lastname).append(", ").append(firstname).append(" ").append(middlename.substring(0,1)).append(" ");
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
