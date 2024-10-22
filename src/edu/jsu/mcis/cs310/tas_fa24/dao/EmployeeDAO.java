package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.Badge;
import edu.jsu.mcis.cs310.tas_fa24.Employee;
import edu.jsu.mcis.cs310.tas_fa24.Shift;
import edu.jsu.mcis.cs310.tas_fa24.Department;
import edu.jsu.mcis.cs310.tas_fa24.EmployeeType;
import edu.jsu.mcis.cs310.tas_fa24.Punch;
import java.sql.*;
import java.time.LocalDateTime;

public class EmployeeDAO {
    
    private final DAOFactory daoFactory;
    
    EmployeeDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public Employee find(int id) throws SQLException{
        Employee employee = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        
        try{
            conn = daoFactory.getConnection();
            
            String query = "SELECT * FROM department WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            
            if(rs.next()){
                String firstname = rs.getString("firstname");
                String middlename = rs.getString("middlename");
                String lastname = rs.getString("lastname");
                LocalDateTime active = rs.getTimestamp("active").toLocalDateTime();
                BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
                Badge badge = badgeDAO.find(rs.getString("badgeid"));
                DepartmentDAO departmentDAO = daoFactory.getDepartmentDAO();
                Department department = departmentDAO.find(rs.getInt("departmentid"));
                //next 2 lines should be added after Shift.java and ShiftDAO are completed
                ShiftDAO shiftDAO = daoFactory.getShiftDAO();
                Shift shift = shiftDAO.find(rs.getInt("shiftid"));
                EmployeeType employeetype = EmployeeType.values()[rs.getInt("employeetypeid")];
                
                employee = new Employee(id, firstname, middlename, lastname, active, badge, department, shift, employeetype);
                
            }
        }
        
        finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            
        }
        return employee;
    }
}
