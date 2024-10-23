package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.Badge;
import edu.jsu.mcis.cs310.tas_fa24.Employee;
import edu.jsu.mcis.cs310.tas_fa24.Shift;
import edu.jsu.mcis.cs310.tas_fa24.Department;
import edu.jsu.mcis.cs310.tas_fa24.EmployeeType;
import edu.jsu.mcis.cs310.tas_fa24.Punch;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
            
            if(rs.next()){
                String description = rs.getString("description");
                LocalTime shiftstart = rs.getTime("shiftstart").toLocalTime();
                LocalTime shiftend = rs.getTime("shiftstop").toLocalTime();
                LocalTime lunchstart = rs.getTime("lunchstart").toLocalTime();
                LocalTime endlunch = rs.getTime("lunchstop").toLocalTime();
                
                shift = new Shift(id, description, shiftstart, shiftend, lunchstart, endlunch);
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
