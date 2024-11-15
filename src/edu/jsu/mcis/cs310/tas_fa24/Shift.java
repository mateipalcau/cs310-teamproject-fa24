package edu.jsu.mcis.cs310.tas_fa24;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

/**
 * Represents a work shift, including its start and stop times, lunch break details,
 * and various timing rules for rounding, grace periods, and dock penalties.
 */
public class Shift {
    
    final private int id;
    final private String description;
    final private LocalTime shiftstart;
    final private LocalTime shiftstop;
    final private int roundinterval;
    final private int graceperiod;
    final private int dockpenalty;
    final private LocalTime lunchstart;
    final private LocalTime lunchstop;
    final private int lunchthreshold;
    final private int lunchduration;
    final private int shiftduration;

    /**
     * Constructs a Shift object using the provided shift data.
     * 
     * @param shiftData a map containing the shift properties as key-value pairs:
     *                  <ul>
     *                  <li>"id" - ID of the shift</li>
     *                  <li>"description" - Description of the shift</li>
     *                  <li>"shiftstart" - Start time of the shift</li>
     *                  <li>"shiftstop" - Stop time of the shift</li>
     *                  <li>"lunchstart" - Start time of the lunch break</li>
     *                  <li>"lunchstop" - Stop time of the lunch break</li>
     *                  <li>"roundinterval" - Interval (in minutes) for rounding punch times</li>
     *                  <li>"graceperiod" - Grace period (in minutes) for late punches</li>
     *                  <li>"dockpenalty" - Dock penalty (in minutes) for late punches</li>
     *                  <li>"lunchthreshold" - Minimum shift duration (in minutes) required for a lunch break</li>
     *                  </ul>
     */
    public Shift(HashMap<String, String> shiftData) {
        this.id = Integer.parseInt(shiftData.get("id"));
        this.description = shiftData.get("description");
        this.shiftstart = LocalTime.parse(shiftData.get("shiftstart"));
        this.shiftstop = LocalTime.parse(shiftData.get("shiftstop"));
        this.lunchstart = LocalTime.parse(shiftData.get("lunchstart"));
        this.lunchstop = LocalTime.parse(shiftData.get("lunchstop"));
    
        this.lunchduration = (int) ChronoUnit.MINUTES.between(lunchstart, lunchstop);
        long duration = ChronoUnit.MINUTES.between(shiftstart, shiftstop);
        if (duration < 0) { // If the stop time is on the next day
            duration += 1440; // Adds 24 hours
        }
        this.shiftduration = (int) duration;
        
        this.roundinterval = Integer.parseInt(shiftData.get("roundinterval"));
        this.graceperiod = Integer.parseInt(shiftData.get("graceperiod"));
        this.dockpenalty = Integer.parseInt(shiftData.get("dockpenalty"));
        this.lunchthreshold = Integer.parseInt(shiftData.get("lunchthreshold"));
    }

    /**
     * Gets the ID of the shift.
     * 
     * @return the shift ID
     */
    public int getId(){
        return id;
    }

    /**
     * Gets the description of the shift.
     * 
     * @return the shift description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Gets the start time of the shift.
     * 
     * @return the shift start time
     */
    public LocalTime getShiftStart(){
        return shiftstart;
    }

    /**
     * Gets the stop time of the shift.
     * 
     * @return the shift stop time
     */
    public LocalTime getShiftStop(){
        return shiftstop;
    }

    /**
     * Gets the rounding interval in minutes.
     * 
     * @return the round interval
     */
    public int getRoundInterval() {
        return roundinterval;
    }

    /**
     * Gets the grace period in minutes for late punches.
     * 
     * @return the grace period
     */
    public int getGracePeriod() {
        return graceperiod;
    }

    /**
     * Gets the dock penalty in minutes for late punches.
     * 
     * @return the dock penalty
     */
    public int getDockPenalty() {
        return dockpenalty;
    }

    /**
     * Gets the start time of the lunch break.
     * 
     * @return the lunch start time
     */
    public LocalTime getLunchStart(){
        return lunchstart;
    }

    /**
     * Gets the stop time of the lunch break.
     * 
     * @return the lunch stop time
     */
    public LocalTime getLunchStop(){
        return lunchstop;
    }

    /**
     * Gets the lunch threshold in minutes, which is the minimum shift duration
     * required for a lunch break.
     * 
     * @return the lunch threshold
     */
    public int getLunchThreshold() {
        return lunchthreshold;
    }

    /**
     * Gets the duration of the lunch break in minutes.
     * 
     * @return the lunch duration
     */
    public int getLunchDuration() {
        return lunchduration;
    }

    /**
     * Returns a string representation of the shift, including its description,
     * start and stop times, duration, and lunch break details.
     * 
     * @return a formatted string representation of the shift
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(description).append(": ");
        s.append(shiftstart.toString()).append(" - ").append(shiftstop.toString()).append(" ");
        s.append("(").append(shiftduration).append(" minutes)").append("; ");
        s.append("Lunch: ").append(lunchstart.toString()).append(" - ").append(lunchstop.toString()).append(" ");
        s.append("(").append(lunchduration).append(" minutes)");
        return s.toString();
    }
}
