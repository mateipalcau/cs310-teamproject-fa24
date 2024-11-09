package edu.jsu.mcis.cs310.tas_fa24.dao;

import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import com.github.cliftonlabs.json_simple.*;
import edu.jsu.mcis.cs310.tas_fa24.EventType;
import edu.jsu.mcis.cs310.tas_fa24.Punch;
import edu.jsu.mcis.cs310.tas_fa24.Shift;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 
 * Utility class for DAOs.  This is a final, non-constructable class containing
 * common DAO logic and other repeated and/or standardized code, refactored into
 * individual static methods.
 * 
 */
public final class DAOUtility {
    public static String getPunchListAsJSON(ArrayList<Punch> dailypunchlist) {
        ArrayList<HashMap<String, String>> jsonData = new ArrayList<>();
        
        for(Punch punch : dailypunchlist) {
            HashMap<String,String> punchData = new HashMap<>();
            
            punchData.put("id", String.valueOf(punch.getId()));
            punchData.put("badgeid", punch.getBadge().getId());
            punchData.put("terminalid", String.valueOf(punch.getTerminalId()));
            punchData.put("punchtype", punch.getPunchType().toString());
        
            if (punch.getAdjustmentType() != null) {
                punchData.put("adjustmenttype", punch.getAdjustmentType().toString());
        }
            punchData.put("originaltimestamp", punch.getFormattedOriginalTimestamp());
            punchData.put("adjustedtimestamp", punch.getFormattedAdjustedTimestamp());
            jsonData.add(punchData);
        }
        return Jsoner.serialize(jsonData);
    }
    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift) {
        ArrayList<Integer> totalMinutes = new ArrayList<Integer>();
        int sum=0;
        
        boolean clockInFound = false;
        Punch clockInPunch = null;

        for (Punch punch : dailypunchlist) {   
            if (punch.getPunchType() == EventType.CLOCK_IN) {
                clockInPunch = punch;
                clockInFound = true;
            } 
            else if (punch.getPunchType() == EventType.CLOCK_OUT && clockInFound) {
                int minutesWorked = (int) ChronoUnit.MINUTES.between(clockInPunch.getAdjustedTimestamp(), punch.getAdjustedTimestamp());
                totalMinutes.add(minutesWorked);
                clockInFound = false;
            } 
            else if (punch.getPunchType() == EventType.TIME_OUT) {
                clockInFound = false;
            }
        }
        for(int i: totalMinutes){
           if (i > shift.getLunchThreshold() && !hasClockOutDuringLunch(dailypunchlist, shift)) {
               i -= shift.getLunchDuration();
               sum=sum+i;
           }else{
               sum=sum+i;
           }
        }
        return sum;
    }

    //method that checks if a lunch break was actually taken
    private static boolean hasClockOutDuringLunch(ArrayList<Punch> dailypunchlist, Shift shift) {
        for (int i = 0; i < dailypunchlist.size() - 1; i++) {
            Punch current = dailypunchlist.get(i);
            Punch next = dailypunchlist.get(i + 1);
            //this checks if a clock out is followed by a clock in during lunch
            if (current.getPunchType() == EventType.CLOCK_OUT &&
                current.getAdjustedTimestamp().toLocalTime().isBefore(shift.getLunchStop()) &&
                next.getPunchType() == EventType.CLOCK_IN &&
                next.getAdjustedTimestamp().toLocalTime().isAfter(shift.getLunchStart()) && 
                next.getAdjustedTimestamp().toLocalDate().getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 &&
                current.getAdjustedTimestamp().toLocalDate().getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0) {
                //lunch break was taken
                return true;  
            }
        }
        //no lunch break was taken
        return false;
    }
    
    public static BigDecimal calculateAbsenteeism(ArrayList<Punch> punchlist, Shift s) {
        int totalMinutesWorked = DAOUtility.calculateTotalMinutes(punchlist, s);
        System.out.println("Total Minutes Worked: " + totalMinutesWorked); //Debugging output
    
        int standardMinutes = 2400; //40 hours a week converted to minutes
        System.out.println("Standard Minutes: " + standardMinutes); //Debugging output
    
        double difference = standardMinutes - totalMinutesWorked;
        double percentage = (difference * 100.0) / standardMinutes;
   
        BigDecimal result = BigDecimal.valueOf(percentage).setScale(2, RoundingMode.HALF_UP);
        System.out.println("Calculated Absenteeism Percentage (Rounded): " + result); //Debugging output

        return result;
    }
}