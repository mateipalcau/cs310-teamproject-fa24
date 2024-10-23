package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.Badge;
import edu.jsu.mcis.cs310.tas_fa24.Shift;
import java.sql.*;
import java.util.HashMap;

public class ShiftDAO {
    private final DAOFactory daoFactory;
    
    public ShiftDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Shift find(int id) {
        Shift shift = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = daoFactory.getConnection();

            String query = "SELECT * FROM shift WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                HashMap<String, String> shiftData = new HashMap<>();
                shiftData.put("id", Integer.toString(rs.getInt("id")));
                shiftData.put("description", rs.getString("description"));
                shiftData.put("startTime", rs.getString("shiftstart"));
                shiftData.put("stopTime", rs.getString("shiftstop"));
                shiftData.put("lunchStart", rs.getString("lunchstart"));
                shiftData.put("lunchStop", rs.getString("lunchstop"));
                shiftData.put("lunchDuration", Integer.toString(rs.getInt("lunchthreshold")));
                shiftData.put("shiftDuration", calculateShiftDuration(rs.getTime("shiftstart"), rs.getTime("shiftstop")));
                shift = new Shift(shiftData);
            }

        } catch (SQLException e) {
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e){
            }
        }
        return shift;
    }
    
    public Shift find(Badge badge) {
        Shift shift = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        
        try {
            conn = daoFactory.getConnection();

            String query = "SELECT shiftid FROM employee WHERE badgeid = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, badge.getId());
            rs = ps.executeQuery();

            if (rs.next()) {
                int shiftId = rs.getInt("shiftid");
                shift = find(shiftId);
            }

        } catch (SQLException e) {
            
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
            }
        }

        return shift;
    }
        private String calculateShiftDuration(Time start, Time stop) {
        int duration = (int)((stop.getTime() - start.getTime()) / (1000 * 60));
        return Integer.toString(duration);
    }
}
