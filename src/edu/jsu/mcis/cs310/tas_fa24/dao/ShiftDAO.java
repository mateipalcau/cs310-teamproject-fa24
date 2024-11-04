package edu.jsu.mcis.cs310.tas_fa24.dao;
import edu.jsu.mcis.cs310.tas_fa24.Badge;
import edu.jsu.mcis.cs310.tas_fa24.Shift;
import java.sql.*;
import java.util.HashMap;

public class ShiftDAO {
    private final DAOFactory daoFactory;
    
    ShiftDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public Shift find(int id) throws SQLException{
        Shift shift = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        
        try{
            conn = daoFactory.getConnection();
            String query = "SELECT * FROM shift WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                HashMap<String, String> shiftData = new HashMap<>();
                shiftData.put("id", Integer.toString(rs.getInt("id")));
                shiftData.put("description", rs.getString("description"));
                shiftData.put("shiftstart", rs.getString("shiftstart"));
                shiftData.put("shiftstop", rs.getString("shiftstop"));
                shiftData.put("roundinterval", Integer.toString(rs.getInt("roundinterval")));
                shiftData.put("graceperiod", Integer.toString(rs.getInt("graceperiod")));
                shiftData.put("dockpenalty", Integer.toString(rs.getInt("dockpenalty")));
                shiftData.put("lunchstart", rs.getString("lunchstart"));
                shiftData.put("lunchstop", rs.getString("lunchstop"));
                shiftData.put("lunchthreshold", Integer.toString(rs.getInt("lunchthreshold")));
                shift = new Shift(shiftData);
            }
        }
        finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();   
        }
        return shift;
    }
    
    public Shift find(Badge badge) throws SQLException{
        ShiftDAO shiftDAO = daoFactory.getShiftDAO();
        String badge_id = badge.getId();
        Shift shift = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        
        try{
            conn = daoFactory.getConnection();
            String query = "SELECT * FROM employee WHERE badgeid = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1,badge_id);
            rs = ps.executeQuery();
            
            if(rs.next()){
                int shift_id = rs.getInt("shiftid");
                shift = shiftDAO.find(shift_id);  
            }
        }
        finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return shift;
    }
}
