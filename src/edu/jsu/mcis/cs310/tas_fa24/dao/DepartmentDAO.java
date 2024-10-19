package edu.jsu.mcis.cs310.tas_fa24.dao;
import edu.jsu.mcis.cs310.tas_fa24.Department;
import java.sql.*;
import java.util.ArrayList;
public class DepartmentDAO {
    
    private final DAOFactory daoFactory;
    
    DepartmentDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public Department find(int id) {
        Department depart = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        
        try {

            conn = daoFactory.getConnection();
        } catch (Exception e) {
        }
        return depart;
       
    }
}
