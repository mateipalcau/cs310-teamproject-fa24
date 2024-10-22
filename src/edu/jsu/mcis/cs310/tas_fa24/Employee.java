package edu.jsu.mcis.cs310.tas_fa24;

import java.time.LocalDateTime;

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
    
    //to do toString function
    
    
}
