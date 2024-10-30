package edu.jsu.mcis.cs310.tas_fa24;

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

    //New Punch
    public Punch(int terminalid, Badge badge, EventType punchType) {
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchType = punchType;
        this.originalTimestamp = LocalDateTime.now().withSecond(0).withNano(0);  
        this.adjustedTimestamp = null;  
    }
    
    //Existing punch
    public Punch(int id, int terminalid, Badge badge, LocalDateTime originaltimestamp, EventType punchtype) {
        this.id = id;
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchType = punchtype;
        this.originalTimestamp = originaltimestamp;
    }

    public int getId() {
        return this.id;
    }

    public int getTerminalId() {
        return this.terminalid;
    }

    public Badge getBadge() {
        return this.badge;
    }

    public EventType getPunchType() {
        return this.punchType;
    }
    public LocalDateTime getOriginalTimestamp(){
        return this.originalTimestamp;
    }

    public String printOriginal() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
        String formattedDate = originalTimestamp.format(dateFormatter);
        //All this is doing is casting the Day to uppercase. It defaults to Ull instead of UUU 
        return String.format("#%s %s: %s", badge.getId(), punchType, formattedDate.substring(0, 3).toUpperCase() + formattedDate.substring(3));
}

    public String printAdjusted() {
        if (adjustedTimestamp == null) {
            return "Adjusted timestamp not available.";
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
        String formattedDate = adjustedTimestamp.format(dateFormatter);
        return String.format("#%s %s: %s", badge.getId(), punchType, formattedDate.substring(0, 3).toUpperCase() + formattedDate.substring(3));
    }
    
    public void adjust(Shift s){
        LocalDateTime adjust = null;
        adjust = this.originalTimestamp;
        
        
        
        
        if(this.originalTimestamp.toLocalTime().compareTo(s.getShiftStart())<0 && this.originalTimestamp.toLocalTime().plusMinutes(s.getRoundInterval()).compareTo(s.getShiftStart())>0){
            adjust = adjust.withHour(s.getShiftStart().getHour());
            adjust = adjust.withMinute(s.getShiftStart().getMinute());
            adjust = adjust.withSecond(s.getShiftStart().getSecond());
        }else if(this.originalTimestamp.toLocalTime().compareTo(s.getShiftStop())>0 && this.originalTimestamp.toLocalTime().minusMinutes(s.getRoundInterval()).compareTo(s.getShiftStop())<0){
            adjust = adjust.withHour(s.getShiftStop().getHour());
            adjust = adjust.withMinute(s.getShiftStop().getMinute());
            adjust = adjust.withSecond(s.getShiftStop().getSecond());
        }else if(this.punchType.toString().compareTo("CLOCK OUT") == 0 && this.originalTimestamp.toLocalTime().compareTo(s.getLunchStart())>0 && this.originalTimestamp.toLocalTime().compareTo(s.getLunchStop())<0){
            adjust = adjust.withHour(s.getLunchStart().getHour());
            adjust = adjust.withMinute(s.getLunchStart().getMinute());
            adjust = adjust.withSecond(s.getLunchStart().getSecond());
        }else if(this.punchType.toString().compareTo("CLOCK IN") == 0 && this.originalTimestamp.toLocalTime().compareTo(s.getLunchStart())>0 && this.originalTimestamp.toLocalTime().compareTo(s.getLunchStop())<0){
            adjust = adjust.withHour(s.getLunchStop().getHour());
            adjust = adjust.withMinute(s.getLunchStop().getMinute());
            adjust = adjust.withSecond(s.getLunchStop().getSecond());
        }else if(this.punchType.toString().compareTo("CLOCK IN") == 0 && this.originalTimestamp.toLocalTime().compareTo(s.getShiftStart())>0 && this.originalTimestamp.toLocalTime().minusMinutes(s.getGracePeriod()).compareTo(s.getShiftStart())<0){
            adjust = adjust.withHour(s.getShiftStart().getHour());
            adjust = adjust.withMinute(s.getShiftStart().getMinute());
            adjust = adjust.withSecond(s.getShiftStart().getSecond());
        }else if(this.punchType.toString().compareTo("CLOCK OUT") == 0 && this.originalTimestamp.toLocalTime().compareTo(s.getShiftStop())<0 && this.originalTimestamp.toLocalTime().plusMinutes(s.getGracePeriod()).compareTo(s.getShiftStop())>0){
            adjust = adjust.withHour(s.getShiftStop().getHour());
            adjust = adjust.withMinute(s.getShiftStop().getMinute());
            adjust = adjust.withSecond(s.getShiftStop().getSecond());
        }
        
        this.adjustedTimestamp = adjust;
        
    }
}