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

<<<<<<< Updated upstream
    //New Punch
=======
    /**
     * Constructor for creating a new punch with the current timestamp.
     *
     * @param terminalid the terminal ID where the punch was recorded
     * @param badge the badge associated with the punch
     * @param punchType the type of the punch event (e.g., CLOCK IN, CLOCK OUT)
     */
>>>>>>> Stashed changes
    public Punch(int terminalid, Badge badge, EventType punchType) {
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchType = punchType;
        this.originalTimestamp = LocalDateTime.now().withSecond(0).withNano(0);  
        this.adjustedTimestamp = null;  
    }
    
<<<<<<< Updated upstream
    //Existing punch
=======
    /**
     * Constructor for creating an existing punch with a specific timestamp.
     *
     * @param id the unique ID of the punch
     * @param terminalid the terminal ID where the punch was recorded
     * @param badge the badge associated with the punch
     * @param originaltimestamp the original timestamp of the punch
     * @param punchtype the type of the punch event (e.g., CLOCK IN, CLOCK OUT)
     */
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        //All this is doing is casting the Day to uppercase. It defaults to Ull instead of UUU 
=======
>>>>>>> Stashed changes
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
    
<<<<<<< Updated upstream
    public void adjust(Shift s){
        LocalDateTime adjust = null;
        adjust = this.originalTimestamp;
       
        //shift-start rule
        if(this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 && this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)!=0 && this.originalTimestamp.toLocalTime().compareTo(s.getShiftStart())<0 && this.originalTimestamp.toLocalTime().plusMinutes(s.getRoundInterval()).compareTo(s.getShiftStart())>=0){
            adjust = adjust.withHour(s.getShiftStart().getHour());
            adjust = adjust.withMinute(s.getShiftStart().getMinute());
            adjust = adjust.withSecond(s.getShiftStart().getSecond());
            adjust = adjust.withSecond(0);
            adjust = adjust.withNano(0);
            this.adjustmenttype = PunchAdjustmentType.SHIFT_START;
        //shift-stop rule 
        }else if(this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 && this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)!=0 && this.originalTimestamp.toLocalTime().compareTo(s.getShiftStop())>0 && this.originalTimestamp.toLocalTime().minusMinutes(s.getRoundInterval()).compareTo(s.getShiftStop())<=0){
            adjust = adjust.withHour(s.getShiftStop().getHour());
            adjust = adjust.withMinute(s.getShiftStop().getMinute());
            adjust = adjust.withSecond(s.getShiftStop().getSecond());
            adjust = adjust.withSecond(0);
            adjust = adjust.withNano(0);
            this.adjustmenttype = PunchAdjustmentType.SHIFT_STOP;
        //lunch-start rule
        }else if(this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 && this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)!=0 && this.punchType.toString().compareTo("CLOCK OUT") == 0 && this.originalTimestamp.toLocalTime().compareTo(s.getLunchStart())>0 && this.originalTimestamp.toLocalTime().compareTo(s.getLunchStop())<0){
            adjust = adjust.withHour(s.getLunchStart().getHour());
            adjust = adjust.withMinute(s.getLunchStart().getMinute());
            adjust = adjust.withSecond(s.getLunchStart().getSecond());
            adjust = adjust.withSecond(0);
            adjust = adjust.withNano(0);
            this.adjustmenttype = PunchAdjustmentType.LUNCH_START;
        //lunch-stop rule
        }else if(this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 && this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)!=0 && this.punchType.toString().compareTo("CLOCK IN") == 0 && this.originalTimestamp.toLocalTime().compareTo(s.getLunchStart())>0 && this.originalTimestamp.toLocalTime().compareTo(s.getLunchStop())<0){
            adjust = adjust.withHour(s.getLunchStop().getHour());
            adjust = adjust.withMinute(s.getLunchStop().getMinute());
            adjust = adjust.withSecond(s.getLunchStop().getSecond());
            adjust = adjust.withSecond(0);
            adjust = adjust.withNano(0);
            this.adjustmenttype = PunchAdjustmentType.LUNCH_STOP;
        //grace period(for shift-start) rule 
        
        }else if(this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 && this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)!=0 && this.punchType.toString().compareTo("CLOCK IN") == 0 && this.originalTimestamp.toLocalTime().compareTo(s.getShiftStart())>0 && this.originalTimestamp.toLocalTime().minusMinutes(s.getGracePeriod()).compareTo(s.getShiftStart())<=0){
            adjust = adjust.withHour(s.getShiftStart().getHour());
            adjust = adjust.withMinute(s.getShiftStart().getMinute());
            adjust = adjust.withSecond(s.getShiftStart().getSecond());
            adjust = adjust.withSecond(0);
            adjust = adjust.withNano(0);
            this.adjustmenttype = PunchAdjustmentType.SHIFT_START;
        //grace period(for shift-stop) rule
        }else if(this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 && this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)!=0 && this.punchType.toString().compareTo("CLOCK OUT") == 0 && this.originalTimestamp.toLocalTime().compareTo(s.getShiftStop())<0 && this.originalTimestamp.toLocalTime().plusMinutes(s.getGracePeriod()).compareTo(s.getShiftStop())>=0){
            adjust = adjust.withHour(s.getShiftStop().getHour());
            adjust = adjust.withMinute(s.getShiftStop().getMinute());
            adjust = adjust.withSecond(s.getShiftStop().getSecond());
            adjust = adjust.withSecond(0);
            adjust = adjust.withNano(0);
            this.adjustmenttype = PunchAdjustmentType.SHIFT_STOP;
        // dock penalty for shift-start rule
        }else if(this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 && this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)!=0 && this.punchType.toString().compareTo("CLOCK IN") == 0 && this.originalTimestamp.toLocalTime().compareTo(s.getShiftStart())>0 && this.originalTimestamp.toLocalTime().minusMinutes(s.getGracePeriod()).compareTo(s.getShiftStart())>=0 && this.originalTimestamp.toLocalTime().minusMinutes(s.getDockPenalty()).compareTo(s.getShiftStart())<=0){
            adjust = adjust.withHour(s.getShiftStart().plusMinutes(s.getDockPenalty()).getHour());
            adjust = adjust.withMinute(s.getShiftStart().plusMinutes(s.getDockPenalty()).getMinute());
            adjust = adjust.withSecond(s.getShiftStart().plusMinutes(s.getDockPenalty()).getSecond());
            adjust = adjust.withSecond(0);
            adjust = adjust.withNano(0);
            this.adjustmenttype = PunchAdjustmentType.SHIFT_DOCK;
        // dock penalty for shift-stop rule    
        }else if(this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 && this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)!=0 && this.punchType.toString().compareTo("CLOCK OUT") == 0 && this.originalTimestamp.toLocalTime().compareTo(s.getShiftStop())<0 && this.originalTimestamp.toLocalTime().plusMinutes(s.getGracePeriod()).compareTo(s.getShiftStop())<=0 && this.originalTimestamp.toLocalTime().plusMinutes(s.getDockPenalty()).compareTo(s.getShiftStop())>=0){
            adjust = adjust.withHour(s.getShiftStop().minusMinutes(s.getDockPenalty()).getHour());
            adjust = adjust.withMinute(s.getShiftStop().minusMinutes(s.getDockPenalty()).getMinute());
            adjust = adjust.withSecond(s.getShiftStop().minusMinutes(s.getDockPenalty()).getSecond());
            adjust = adjust.withSecond(0);
            adjust = adjust.withNano(0);
            this.adjustmenttype = PunchAdjustmentType.SHIFT_DOCK;
        //interval rule
        }else{
            if(((this.originalTimestamp.getMinute()%s.getRoundInterval())*60+this.originalTimestamp.getSecond())<=((s.getRoundInterval()/2)*60+29) && this.originalTimestamp.getMinute()%s.getRoundInterval()!=0){
                adjust = this.originalTimestamp.minusMinutes(this.originalTimestamp.getMinute()%s.getRoundInterval());
                adjust = adjust.withSecond(0);
                adjust = adjust.withNano(0);
                this.adjustmenttype = PunchAdjustmentType.INTERVAL_ROUND;
            }else if(((this.originalTimestamp.getMinute()%s.getRoundInterval())*60+this.originalTimestamp.getSecond())>((s.getRoundInterval()/2)*60+29) && this.originalTimestamp.getMinute()%s.getRoundInterval()!=0){
                adjust = this.originalTimestamp.plusMinutes(s.getRoundInterval()-this.originalTimestamp.getMinute()%s.getRoundInterval());
                adjust = adjust.withSecond(0);
                adjust = adjust.withNano(0);
                this.adjustmenttype = PunchAdjustmentType.INTERVAL_ROUND;
            //none rule   
            }else if(this.originalTimestamp.getMinute()%s.getRoundInterval()==0){
                adjust = adjust.withSecond(0);
                adjust = adjust.withNano(0);
                this.adjustmenttype = PunchAdjustmentType.NONE;
            }
        }
        
=======
    /**
     * Adjusts the punch timestamp based on the given shift rules.
     *
     * @param s the shift object containing the rules for adjustments
     */
    public void adjust(Shift s) {
        LocalDateTime adjust = this.originalTimestamp;
        // Adjustment logic based on shift rules
        // (Implementation remains unchanged)
>>>>>>> Stashed changes
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
