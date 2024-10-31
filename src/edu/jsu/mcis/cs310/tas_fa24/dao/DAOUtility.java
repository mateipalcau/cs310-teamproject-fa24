package edu.jsu.mcis.cs310.tas_fa24.dao;

import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import com.github.cliftonlabs.json_simple.*;
import edu.jsu.mcis.cs310.tas_fa24.Punch;

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
}