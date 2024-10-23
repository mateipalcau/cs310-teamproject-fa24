package edu.jsu.mcis.cs310.tas_fa24;

import java.time.*;
import java.util.HashMap;

public class Shift {
    
    final private int id;
    final private String description;
    final private LocalTime startTime;
    final private LocalTime stopTime;
    final private LocalTime lunchStart;
    final private LocalTime lunchStop;
    final private int lunchDuration;
    final private int shiftDuration;

    public Shift(HashMap<String, String> shiftData) {
        this.id = Integer.parseInt(shiftData.get("id"));
        this.description = shiftData.get("description");
        this.startTime = LocalTime.parse(shiftData.get("startTime"));
        this.stopTime = LocalTime.parse(shiftData.get("stopTime"));
        this.lunchStart = LocalTime.parse(shiftData.get("lunchStart"));
        this.lunchStop = LocalTime.parse(shiftData.get("lunchStop"));
        this.lunchDuration = Integer.parseInt(shiftData.get("lunchDuration"));
        this.shiftDuration = Integer.parseInt(shiftData.get("shiftDuration"));
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getStopTime() {
        return stopTime;
    }

    public LocalTime getLunchStart() {
        return lunchStart;
    }

    public LocalTime getLunchStop() {
        return lunchStop;
    }

    public int getLunchDuration() {
        return lunchDuration;
    }

    public int getShiftDuration() {
        return shiftDuration;
    }

@Override
public String toString() {
    String shiftStart = startTime.toString();
    String shiftEnd = stopTime.toString();
    String lunchStartOutput = lunchStart.toString();
    String lunchEnd = lunchStop.toString();

    int lunchCalc = (int) Duration.between(lunchStart, lunchStop).toMinutes();
    String result = description + ": " + shiftStart + " - " + shiftEnd + " (" + shiftDuration + " minutes); " + "Lunch: " + lunchStartOutput + " - " + lunchEnd + " (" + lunchCalc + " minutes)";

    return result;
}
}
