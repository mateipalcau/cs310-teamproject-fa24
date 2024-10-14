package edu.jsu.mcis.cs310.tas_fa24.dao;
import java.time.*;
import java.util.HashMap;

public class ShiftDAO {
    private LocalTime startShift, endShift, lunchDuration, shiftDuration;
    
    public ShiftDAO(HashMap<String, LocalTime> data)
    {
        startShift = data.get("start");
        endShift = data.get("end");
        lunchDuration = data.get("lunch");
        shiftDuration = data.get("shift");
    }
    
    public HashMap getShift()
    {   
        HashMap<String, LocalTime> shiftData = new HashMap<>();
        shiftData.put("start", startShift);
        shiftData.put("end", endShift);
        shiftData.put("lunch", lunchDuration);
        shiftData.put("shift", shiftDuration);
        return shiftData;
    }
    
    public void setShift(HashMap<String, LocalTime> data)
    {
        startShift = data.get("start");
        endShift = data.get("end");
        lunchDuration = data.get("lunch");
        shiftDuration = data.get("shift");
    }
    
    @Override
    public String toString()
    {
        String test = "Shift 1: ";
        test += "Shift 1: " + startShift + " - " + endShift + " (" + shiftDuration + "); ";
        return test;
    }
    
    public ShiftDAO find()
    {
        ShiftDAO testShift = new ShiftDAO();
        //connect to database
        //edit find method in badgedao
        return testShift;
    }
    
    
}
