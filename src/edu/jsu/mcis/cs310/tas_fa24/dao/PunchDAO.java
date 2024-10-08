
package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.EventType;
import java.time.LocalDateTime;
import edu.jsu.mcis.cs310.tas_fa24.Badge;
import edu.jsu.mcis.cs310.tas_fa24.PunchAdjustmentType;

public class PunchDAO {
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
    public void Punch(int id, int terminalid, Badge badge, LocalDateTime originaltimestamp, EventType punchType,LocalDateTime originalTimestamp, LocalDateTime adjustedTimestamp, 
        PunchAdjustmentType adjustmentType) {
        this.id = id;
        this.terminalid = terminalid;
        // this.punchType = punchtype;
        this.originalTimestamp = originalTimestamp;
        this.adjustedTimestamp = adjustedTimestamp;
        this.adjustmentType = adjustmentType;
    }
}
