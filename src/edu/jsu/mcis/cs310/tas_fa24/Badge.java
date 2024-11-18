package edu.jsu.mcis.cs310.tas_fa24;

/**
 * Represents an identification badge used within the Time and Attendance System.
 * Each badge has a unique identifier and a description.
 */
public class Badge {

    /**
     * The unique identifier of the badge.
     */
    private final String id;

    /**
     * A brief description of the badge.
     */
    private final String description;

    /**
     * Constructs a Badge object with the specified identifier and description.
     * 
     * @param id the unique identifier of the badge
     * @param description a brief description of the badge
     */
    public Badge(String id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * Returns the unique identifier of the badge.
     * 
     * @return the badge ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the description of the badge.
     * 
     * @return the badge description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the badge, including its ID and description.
     * 
     * @return a formatted string representing the badge
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append('#').append(id).append(' ');
        s.append('(').append(description).append(')');

        return s.toString();
    }

}
