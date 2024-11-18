package edu.jsu.mcis.cs310.tas_fa24;

/**
 * Represents a department within the Time and Attendance System.
 * Each department has a unique identifier, a description, and an associated terminal ID.
 */
public class Department {

    /**
     * The unique identifier for the department.
     */
    final private int id;

    /**
     * A brief description of the department.
     */
    final private String description;

    /**
     * The terminal ID associated with the department.
     */
    final private int terminalId;

    /**
     * Constructs a Department object with the specified attributes.
     * 
     * @param id the unique identifier of the department
     * @param description the description of the department
     * @param terminalId the terminal ID associated with the department
     */
    public Department(int id, String description, int terminalId) {
        this.id = id;
        this.description = description;
        this.terminalId = terminalId;
    }

    /**
     * Returns the unique identifier of the department.
     * 
     * @return the department ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the description of the department.
     * 
     * @return the department description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the terminal ID associated with the department.
     * 
     * @return the terminal ID of the department
     */
    public int getTerminalId() {
        return terminalId;
    }

    /**
     * Returns a string representation of the department, including its ID, 
     * description, and associated terminal ID.
     * 
     * @return a formatted string representing the department
     */
    @Override
    public String toString() {
        return String.format("#%d (%s), Terminal ID: %d", id, description, terminalId);
    }
}
