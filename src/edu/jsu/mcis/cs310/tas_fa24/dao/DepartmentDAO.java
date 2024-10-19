package edu.jsu.mcis.cs310.tas_fa24.dao;
import edu.jsu.mcis.cs310.tas_fa24.Department;
import java.sql.*;
import java.util.ArrayList;
public class DepartmentDAO {
    
    private final DAOFactory daoFactory;
    
    DepartmentDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public Department find(int id) throws SQLException{
        Department depart = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        
        try {
            conn = daoFactory.getConnection();
            
            String query = "SELECT * FROM department WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                int departId = rs.getInt("id");
                String description = rs.getString("description");
                int terminalId = rs.getInt("terminalid");
                
                depart = new Department(id, description, terminalId);
            }
            
        }
        
        finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            
        }
        return depart;
       
    }
}
