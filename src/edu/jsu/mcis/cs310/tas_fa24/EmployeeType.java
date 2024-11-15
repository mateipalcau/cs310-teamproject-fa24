package edu.jsu.mcis.cs310.tas_fa24;

/**
 * Represents the types of employees in the Time and Attendance System.
 * This enum defines whether an employee is part-time or full-time.
 */
public enum EmployeeType {

    /**
     * Employee type representing a temporary or part-time employee.
     */
    PART_TIME("Temporary / Part-Time"),

    /**
     * Employee type representing a full-time employee.
     */
    FULL_TIME("Full-Time");

    /**
     * A description of the employee type.
     */
    private final String description;

    /**
     * Constructor to initialize the employee type with a description.
     * 
     * @param d the description of the employee type
     */
    private EmployeeType(String d) {
        description = d;
    }

    /**
     * Returns the description of the employee type as a string.
     * 
     * @return the description of the employee type
     */
    @Override
    public String toString() {
        return description;
    }
}
