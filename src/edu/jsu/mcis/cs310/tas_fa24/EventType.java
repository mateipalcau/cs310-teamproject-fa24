package edu.jsu.mcis.cs310.tas_fa24;

/**
 * Represents the types of events in the Time and Attendance System.
 * These events define actions that employees can perform, such as clocking in or out.
 */
public enum EventType {

    /**
     * Event type representing when an employee clocks out.
     */
    CLOCK_OUT("CLOCK OUT"),

    /**
     * Event type representing when an employee clocks in.
     */
    CLOCK_IN("CLOCK IN"),

    /**
     * Event type representing when an employee times out due to inactivity or other reasons.
     */
    TIME_OUT("TIME OUT");

    /**
     * A description of the event type.
     */
    private final String description;

    /**
     * Constructor to initialize the event type with a description.
     * 
     * @param d the description of the event type
     */
    private EventType(String d) {
        description = d;
    }

    /**
     * Returns the description of the event type as a string.
     * 
     * @return the description of the event type
     */
    @Override
    public String toString() {
        return description;
    }
}
