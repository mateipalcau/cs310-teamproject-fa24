package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.Badge;
import edu.jsu.mcis.cs310.tas_fa24.EventType;
import edu.jsu.mcis.cs310.tas_fa24.Punch;
import edu.jsu.mcis.cs310.tas_fa24.Department;
import edu.jsu.mcis.cs310.tas_fa24.Employee;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PunchDAO {
    private static final String QUERY_CREATE = "INSERT INTO event (terminalid, badgeid, timestamp, eventtypeid) VALUES (?, ?, ?, ?)";
    
    private final DAOFactory daoFactory; 
    
    PunchDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;
    }
    
    public Punch find(int id) throws SQLException {
        String query = "SELECT * FROM event WHERE id = ?";
        
        Connection conn = null;
        Punch punch = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = daoFactory.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();

            if (rs.next()) {
                int terminalId = rs.getInt("terminalid");
                BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
                Badge badge = badgeDAO.find(rs.getString("badgeid"));
                LocalDateTime originalTimestamp = rs.getTimestamp("timestamp").toLocalDateTime();
                EventType punchType = EventType.values()[rs.getInt("eventtypeid")];

                punch = new Punch(id, terminalId, badge, originalTimestamp, punchType);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        return punch;
    }
    
    
    public int create(Punch punch) throws SQLException{
        int result = 0;
        int punch_terminalid = punch.getTerminalId();
        
        Badge badge = punch.getBadge();
        String badge_id = badge.getId();
        
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        Employee employee = employeeDAO.find(badge);
        
        Department department = employee.getDepartment();
        int department_id = department.getTerminalId();
                
  
        
        if(punch_terminalid == 0 || punch_terminalid == department_id){
            
            PreparedStatement ps = null;
            ResultSet rs = null;
                    
            try{
                Connection conn = daoFactory.getConnection();
                        
                if(conn.isValid(0)){
                    ps = conn.prepareStatement(QUERY_CREATE,Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, punch_terminalid);
                    ps.setString(2, badge_id);
                    ps.setTimestamp(3, Timestamp.valueOf(punch.getOriginalTimestamp()));
                            //ps.setInt(4, punch.getPunchType().toString());
                    if(punch.getPunchType().toString().compareToIgnoreCase("Clock OUT")==0){
                        ps.setInt(4, 0);
                    }else if(punch.getPunchType().toString().compareToIgnoreCase("CLOCK IN")==0){
                        ps.setInt(4, 1);
                    }else{
                        ps.setInt(4, 2);
                    }
                            
                    int updateCount = ps.executeUpdate();
                            
                    if (updateCount > 0){
                        rs = ps.getGeneratedKeys();
                                
                        if (rs.next()){
                            result = rs.getInt(1);
                        }
                    }
                }
                        
            }
            catch (Exception e) { e.printStackTrace(); }
        
            finally {
            
                if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
                if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
            }
            //exception, directly insert the punch in event table
            
            
        }else{
             result = 0;

        } 
        return result;
        
    }
    
    public ArrayList<Punch> list(Badge badge, LocalDate timestamp) throws SQLException{
        ArrayList<Punch> arr = new ArrayList<Punch>();
        
        String query = "SELECT * FROM event WHERE badgeid = ? ORDER BY timestamp";
        
        PunchDAO punchDAO = daoFactory.getPunchDAO();
        Punch punch = null;
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            conn = daoFactory.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, badge.getId());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int punch_id = rs.getInt("id");
                punch = punchDAO.find(punch_id);
                
                if(punch.getOriginalTimestamp().toLocalDate().compareTo(timestamp)==0){
                    arr.add(punch);
                }
                //this if adds the first punch from the next day, but if I add it to the code, it doesn`t pass the tests
                /*if(punch.getOriginalTimestamp().toLocalDate().compareTo(timestamp.plusDays(1))==0){
                    arr.add(punch);
                    break;
                }*/
            }
            
        }
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        return arr;
        
    }
    
    public ArrayList<Punch> list(Badge badge, LocalDate begin, LocalDate end) throws SQLException{
        PunchDAO punchDAO = daoFactory.getPunchDAO();
        ArrayList<Punch> arr = new ArrayList<Punch>();
        ArrayList<Punch> arr_full = new ArrayList<Punch>();
        
        while(begin.isBefore(end) || begin.isEqual(end)){
            arr = punchDAO.list(badge, begin);
            arr_full.addAll(arr);
            begin = begin.plusDays(1);
        }
        return arr_full;
    }
}