package edu.jsu.mcis.cs310.tas_fa24;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Shift {
    final private int id;
    final private String description;
    final private LocalTime shiftstart;
    final private LocalTime shiftend;
    final private LocalTime startlunch;
    final private LocalTime endlunch;
    
    public Shift(int id, String description, LocalTime shiftstart, LocalTime shiftend, LocalTime startlunch, LocalTime endlunch){
        this.id = id;
        this.description = description;
        this.shiftstart = shiftstart;
        this.shiftend = shiftend;
        this.startlunch = startlunch;
        this.endlunch = endlunch;
    }
    
    public int getId(){
        return id;
    }
    public String getDescription(){
        return description;
    }
    public LocalTime getShiftstart(){
        return shiftstart;
    }
    public LocalTime getShiftend(){
        return shiftend;
    }
    public LocalTime getStartluch(){
        return startlunch;
    }
    public LocalTime getEndluch(){
        return endlunch;
    }
    
    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        
        s.append(description).append(": ");
        int time;
        s.append(shiftstart.toString()).append(" - ").append(shiftend.toString()).append(" ");
        time=((shiftend.getHour()-shiftstart.getHour())*60)+shiftend.getMinute()-shiftstart.getMinute();
        s.append("(").append(Integer.toString(time)).append(" minutes)").append("; ");
        s.append("Lunch: ").append(startlunch.toString()).append(" - ").append(endlunch.toString()).append(" ");
        int time2;
        time2=((endlunch.getHour()-startlunch.getHour())*60)+endlunch.getMinute()-startlunch.getMinute();
        s.append("(").append(Integer.toString(time2)).append(" minutes)");
        
        

        return s.toString();

    }
}
