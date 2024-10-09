package edu.jsu.mcis.cs310.tas_fa24.dao;
import java.time.*;
import java.util.HashMap;

public class ShiftDAO {
    public LocalTime startShift, endShift, lunchDuration, shiftDuration;
    
    public ShiftDAO(HashMap<String, LocalTime> data)
    {
        startShift = data.get("start");
        endShift = data.get("end");
        lunchDuration = data.get("lunch");
        shiftDuration = data.get("shift");
    }
}
