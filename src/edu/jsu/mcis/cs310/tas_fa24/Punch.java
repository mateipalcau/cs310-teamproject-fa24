
package edu.jsu.mcis.cs310.tas_fa24;

import edu.jsu.mcis.cs310.tas_fa24.EventType;
import java.time.LocalDateTime;
import edu.jsu.mcis.cs310.tas_fa24.Badge;
import edu.jsu.mcis.cs310.tas_fa24.PunchAdjustmentType;
import java.sql.*;


public class Punch {
    private Integer id;  
    private int terminalid;  
    private Badge badge;  
    private EventType punchType; 
    private LocalDateTime originalTimestamp; 
    private LocalDateTime adjustedTimestamp;  
    private PunchAdjustmentType adjustmentType;

    public void Punch(int terminalid, Badge badge, EventType punchType) {
        this.terminalid = terminalid;
        this.badge = badge;
        // this.punchType = punchtype;
        this.originalTimestamp = LocalDateTime.now();  
        this.adjustedTimestamp = null;  
        this.adjustmentType = null;  
        
    }
    public void Punch(int id, int terminalid, Badge badge, LocalDateTime originaltimestamp, EventType punchtype) {
        this.id = id;
        this.terminalid = terminalid;
        this.badge=badge;
        this.punchType = punchtype;
        this.originalTimestamp = originaltimestamp;
        
    }
    
    public Integer getId(){
        return this.id;
    }
    public int getTerminalId(){
        return this.terminalid;
    }
    public Badge getBadge(){
        return this.badge;
    }
    public EventType getPunchType(){
        return this.punchType;
    }
    public LocalDateTime getOriginalTimestamp(){
        return this.originalTimestamp;
    }
    public LocalDateTime getAdjustedTimestamp(){
        return this.adjustedTimestamp;
    }
    public PunchAdjustmentType getAdjustmentType(){
        return this.adjustmentType;
    }
    public String printOriginal() {

        StringBuilder s = new StringBuilder();
        s.append("terminal id: ");
        s.append('#').append(this.terminalid).append(' ');
        
        s.append("badge id: ");
        s.append('#').append(this.badge).append(' ');
        
        s.append("timestamp: ");
        //not sure if the conversion will work
        s.append(this.originalTimestamp.toString()).append(' ');
        
        //s.append("event type: ");
        //s.append(this.punchtype.toString()).append(' ');

        return s.toString();

    }
    
    @Override
    public String toString(){
       return printOriginal();
       //to add here printAdjusted when it will be done
    }
}