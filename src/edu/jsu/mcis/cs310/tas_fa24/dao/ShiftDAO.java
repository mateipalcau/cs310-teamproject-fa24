package edu.jsu.mcis.cs310.tas_fa24.dao;
import edu.jsu.mcis.cs310.tas_fa24.Shift;
import java.sql.*;
import java.time.*;
import java.util.HashMap;

public class ShiftDAO {
    
    private static final String QUERY = "";//find shift sql query here
    private final DAOFactory daoFactory;
    
    public Shift find(HashMap<String, LocalTime> data)//set parameter to defining data field
    {
        Shift shift = null;
        String startShift = data.get("start").toString();
        PreparedStatement ps = null;
        ResultSet rs = null;
        

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY);
                ps.setString(1, startShift);//key

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {

                        String description = rs.getString("description");
                        shift = new Shift(startShift, description);//key

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

        return shift;
    }
    
    
}