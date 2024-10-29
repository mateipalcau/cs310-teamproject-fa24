package edu.jsu.mcis.cs310.tas_fa24;

import java.time.*;
import java.util.HashMap;

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

    public Shift(HashMap<String, String> shiftData) {
        this.id = Integer.parseInt(shiftData.get("id"));
        this.description = shiftData.get("description");
        this.shiftstart = LocalTime.parse(shiftData.get("shiftstart"));
        this.shiftstop = LocalTime.parse(shiftData.get("shiftstop"));
        this.lunchstart = LocalTime.parse(shiftData.get("lunchstart"));
        this.lunchstop = LocalTime.parse(shiftData.get("lunchStop"));
        this.lunchduration = Integer.parseInt(shiftData.get("lunchDuration"));
        this.shiftduration = Integer.parseInt(shiftData.get("shiftDuration"));
        this.roundinterval = Integer.parseInt(shiftData.get("roundInterval"));
        this.graceperiod = Integer.parseInt(shiftData.get("gracePeriod"));
        this.dockpenalty = Integer.parseInt(shiftData.get("dockpenalty"));
        this.lunchthreshold = Integer.parseInt(shiftData.get("lunchthreshold"));   
    }
    
    public int getId(){
        return id;
    }
    public String getDescription(){
        return description;
    }
    public LocalTime getShiftStart(){
        return shiftstart;
    }
    public LocalTime getShiftStop(){
        return shiftstop;
    }
    public int getRoundInterval() {
        return roundinterval;
    }
    public int getGracePeriod() {
        return graceperiod;
    }
    public int getDockPenalty() {
        return dockpenalty;
    }
    public LocalTime getLunchStart(){
        return lunchstart;
    }
    public LocalTime getLunchStop(){
        return lunchstop;
    }
   
    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        
        s.append(description).append(": ");
        int time;
        s.append(shiftstart.toString()).append(" - ").append(shiftstop.toString()).append(" ");
        if(shiftstop.getHour() < shiftstart.getHour()) {
            time=((shiftstop.getHour()-shiftstart.getHour())*60)+shiftstop.getMinute()-shiftstart.getMinute()+1440;
            //Look at changing this to a chronounit
        }else{
            time=((shiftstop.getHour()-shiftstart.getHour())*60)+shiftstop.getMinute()-shiftstart.getMinute();
        }
        
        s.append("(").append(Integer.toString(time)).append(" minutes)").append("; ");
        s.append("Lunch: ").append(lunchstart.toString()).append(" - ").append(lunchstop.toString()).append(" ");
        int time2;
        time2=((lunchstop.getHour()-lunchstart.getHour())*60)+lunchstop.getMinute()-lunchstart.getMinute();
        s.append("(").append(Integer.toString(time2)).append(" minutes)");
        
        

        return s.toString();

    }
}
