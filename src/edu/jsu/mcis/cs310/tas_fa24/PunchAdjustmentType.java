package edu.jsu.mcis.cs310.tas_fa24;

/**
 * Represents the types of adjustments that can be made to a punch time.
 * These adjustments correspond to specific rules or events in a shift, such as
 * aligning punches with shift start or lunch breaks.
 */
public enum PunchAdjustmentType {

    /** No adjustment is made to the punch time. */
    NONE("None"),
    
    /** The punch time is adjusted to the start of the shift. */
    SHIFT_START("Shift Start"),
    
    /** The punch time is adjusted to the end of the shift. */
    SHIFT_STOP("Shift Stop"),
    
    /** The punch time is adjusted due to a dock penalty. */
    SHIFT_DOCK("Shift Dock"),
    
    /** The punch time is adjusted to the start of the lunch break. */
    LUNCH_START("Lunch Start"),
    
    /** The punch time is adjusted to the end of the lunch break. */
    LUNCH_STOP("Lunch Stop"),
    
    /** The punch time is adjusted to the nearest interval boundary. */
    INTERVAL_ROUND("Interval Round");

    private final String description;

    /**
     * Constructs a PunchAdjustmentType with a specified description.
     * 
     * @param d the description of the adjustment type
     */
    private PunchAdjustmentType(String d) {
        description = d;
    }

    /**
     * Returns the string representation of the punch adjustment type.
     * 
     * @return the description of the adjustment type
     */
    @Override
    public String toString() {
        return description;
    }
}
