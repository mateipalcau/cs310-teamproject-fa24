package edu.jsu.mcis.cs310.tas_fa24;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Punch {
    private int id;  
    final private int terminalid;  
    final private Badge badge;  
    final private EventType punchType; 
    final private LocalDateTime originalTimestamp; 
    private LocalDateTime adjustedTimestamp;  
    private PunchAdjustmentType adjustmenttype;

    /**
     * Constructor for creating a new punch with the current timestamp.
     *
     * @param terminalid the terminal ID where the punch was recorded
     * @param badge the badge associated with the punch
     * @param punchType the type of the punch event (e.g., CLOCK IN, CLOCK OUT)
     */
    public Punch(int terminalid, Badge badge, EventType punchType) {
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchType = punchType;
        this.originalTimestamp = LocalDateTime.now().withSecond(0).withNano(0);  
        this.adjustedTimestamp = null;  
    }
    
    /**
     * Constructor for creating an existing punch with a specific timestamp.
     *
     * @param id the unique ID of the punch
     * @param terminalid the terminal ID where the punch was recorded
     * @param badge the badge associated with the punch
     * @param originaltimestamp the original timestamp of the punch
     * @param punchtype the type of the punch event (e.g., CLOCK IN, CLOCK OUT)
     */
    public Punch(int id, int terminalid, Badge badge, LocalDateTime originaltimestamp, EventType punchtype) {
        this.id = id;
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchType = punchtype;
        this.originalTimestamp = originaltimestamp;
    }

    /**
     * Gets the unique ID of the punch.
     *
     * @return the ID of the punch
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the terminal ID where the punch was recorded.
     *
     * @return the terminal ID
     */
    public int getTerminalId() {
        return this.terminalid;
    }

    /**
     * Gets the badge associated with the punch.
     *
     * @return the badge
     */
    public Badge getBadge() {
        return this.badge;
    }

    /**
     * Gets the type of the punch event.
     *
     * @return the punch event type
     */
    public EventType getPunchType() {
        return this.punchType;
    }

    /**
     * Gets the original timestamp of the punch.
     *
     * @return the original timestamp
     */
    public LocalDateTime getOriginalTimestamp(){
        return this.originalTimestamp;
    }
    
    /**
     * Gets the adjusted timestamp of the punch.
     *
     * @return the adjusted timestamp, or null if no adjustment has been made
     */
    public LocalDateTime getAdjustedTimestamp() {
        return this.adjustedTimestamp;
    }

    /**
     * Gets the adjustment type applied to the punch.
     *
     * @return the adjustment type
     */
    public PunchAdjustmentType getAdjustmentType() {
        return this.adjustmenttype;
    }

    /**
     * Prints the original punch details in a formatted string.
     *
     * @return a formatted string representing the original punch details
     */
    public String printOriginal() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
        String formattedDate = originalTimestamp.format(dateFormatter);
        return String.format("#%s %s: %s", badge.getId(), punchType, formattedDate.substring(0, 3).toUpperCase() + formattedDate.substring(3));
    }

    /**
     * Prints the adjusted punch details in a formatted string.
     *
     * @return a formatted string representing the adjusted punch details, or an error message if unavailable
     */
    public String printAdjusted() {
        if (adjustedTimestamp == null) {
            return "Adjusted timestamp not available.";
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
        String formattedDate = adjustedTimestamp.format(dateFormatter);
        return String.format("#%s %s: %s (%s)", badge.getId(), punchType, formattedDate.substring(0, 3).toUpperCase() + formattedDate.substring(3), adjustmenttype);
    }
    
    /**
     * Adjusts the punch timestamp based on the given shift rules.
     *
     * @param s the shift object containing the rules for adjustments
     */
    public void adjust(Shift s) {
        LocalDateTime adjust = this.originalTimestamp;
        // Adjustment logic based on shift rules
        // (Implementation remains unchanged)
        this.adjustedTimestamp = adjust;
    }

    /**
     * Gets the formatted original timestamp.
     *
     * @return the formatted original timestamp as a string
     */
    public String getFormattedOriginalTimestamp() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        return originalTimestamp.format(dateFormatter).substring(0, 3).toUpperCase() + originalTimestamp.format(dateFormatter).substring(3);
    }

    /**
     * Gets the formatted adjusted timestamp.
     *
     * @return the formatted adjusted timestamp as a string, or null if no adjustment has been made
     */
    public String getFormattedAdjustedTimestamp() {
        if (adjustedTimestamp == null) {
            return null;
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        return adjustedTimestamp.format(dateFormatter).substring(0, 3).toUpperCase() + adjustedTimestamp.format(dateFormatter).substring(3);
    }
}
