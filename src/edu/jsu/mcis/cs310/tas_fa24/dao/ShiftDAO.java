package edu.jsu.mcis.cs310.tas_fa24.dao;
import edu.jsu.mcis.cs310.tas_fa24.Badge;
import java.sql.*;
import java.time.*;
import java.util.HashMap;

public class ShiftDAO {
    private LocalTime startShift, endShift, lunchDuration, shiftDuration;
    
    private static final String QUERY = "";//find shift sql query here
    
    private final DAOFactory daoFactory;

    
    public ShiftDAO(HashMap<String, LocalTime> data, DAOFactory daoFactory)
    {
        startShift = data.get("start");
        endShift = data.get("end");
        lunchDuration = data.get("lunch");
        shiftDuration = data.get("shift");

        
        this.daoFactory = daoFactory;
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
    
    public ShiftDAO find()//set parameter to defining data field
    {
        Badge badge = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY);
                ps.setString(1, id);//key

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {

                        String description = rs.getString("description");
                        badge = new Badge(id, description);//key

                    }

                }

            }

        } catch (SQLException e) {

            throw new DAOException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }

        }

        return badge;
    }
    
    
}