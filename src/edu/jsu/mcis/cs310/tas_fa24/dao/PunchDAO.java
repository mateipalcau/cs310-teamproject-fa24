package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.Badge;
import edu.jsu.mcis.cs310.tas_fa24.EventType;
import edu.jsu.mcis.cs310.tas_fa24.Punch;
import java.sql.*;
import java.time.LocalDateTime;

public class PunchDAO {
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
}