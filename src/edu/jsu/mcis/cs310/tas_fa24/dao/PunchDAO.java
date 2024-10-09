package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.EventType;
import java.time.LocalDateTime;
import edu.jsu.mcis.cs310.tas_fa24.Badge;
import edu.jsu.mcis.cs310.tas_fa24.PunchAdjustmentType;
import edu.jsu.mcis.cs310.tas_fa24.Punch;
import java.sql.*;


public class PunchDAO {
    private static final String QUERY_FIND = "SELECT * FROM event WHERE id = ?";
    
    private final DAOFactory daoFactory;
    
    PunchDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;
    }
    
    public Punch find(int id) {

        Punch punch = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND);
                ps.setInt(1, id);

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {


                        String terId = rs.getString("terminalid");
                        //to complete here, ask about second Punch Constructor
                        

                        
                        int terminalId = rs.getInt("terminalid");
                        Badge badge=(Badge)rs.getObject("badgeid");
                        
                        LocalDateTime originalTimeStamp=(LocalDateTime)rs.getObject("timestamp");
                        
                        EventType punchtype=(EventType)rs.getObject("eventtypeid");
                        
                        punch = new Punch(id,terminalId,badge,originalTimeStamp,punchtype);
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

        return punch;

    }

}