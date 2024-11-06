package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.*;
import java.time.LocalDate;
import java.sql.*;


public class AbsenteeismDAO {
    
        private final DAOFactory daoFactory;
        private static final String QUERY_FIND = "SELECT * FROM absenteeism WHERE id = ?";

    
    AbsenteeismDAO(DAOFactory daoFactory){
        this.daoFactory = daoFactory;
    }
    public Absenteeism find(Employee employee, LocalDate date) throws SQLException
    {
        Absenteeism absent = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = employee.getId();
        
        try
        {
            Connection conn = daoFactory.getConnection();
            if(conn.isValid(0))
            {
                ps = conn.prepareStatement(QUERY_FIND);
                ps.setString(1,Integer.toString(id));
                
                boolean hasresults = ps.execute();
                
                if(hasresults) {
                    rs = ps.getResultSet();
                    
                    while(rs.next())
                    {
                        //look at badge and take in timestamp like description
                    }
                }
            }
        }
        catch(SQLException e)
        {
            
        }
    }
}
