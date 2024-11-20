package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.Badge;
import edu.jsu.mcis.cs310.tas_fa24.Shift;
import java.sql.*;
import java.util.HashMap;

/**
 * The {@code ShiftDAO} class provides data access operations for retrieving {@code Shift} 
 * objects from the database. This class interacts with the "shift" and "employee" tables 
 * to fetch shift details either by shift ID or associated badge ID.
 * @author Garret Howell
 * @version 1.0
 */
public class ShiftDAO {

    private final DAOFactory daoFactory;

    /**
     * Constructs a {@code ShiftDAO} with the specified {@code DAOFactory}.
     *
     * @param daoFactory the factory object used to manage connections and other DAOs
     */
    ShiftDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Retrieves a {@code Shift} from the database using the given shift ID.
     *
     * @param id the ID of the shift to retrieve
     * @return the {@code Shift} object corresponding to the given ID, or {@code null} 
     *         if no such shift exists
     * @throws SQLException if a database access error occurs
     */
    public Shift find(int id) throws SQLException {
        Shift shift = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = daoFactory.getConnection();
            String query = "SELECT * FROM shift WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
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
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return shift;
    }

    /**
     * Retrieves a {@code Shift} from the database using the given {@code Badge}.
     *
     * <p>The method finds the associated employee using the badge ID, retrieves the 
     * corresponding shift ID, and then fetches the {@code Shift} using the shift ID.</p>
     *
     * @param badge the {@code Badge} object used to locate the associated employee
     * @return the {@code Shift} object associated with the employee, or {@code null} 
     *         if no such employee or shift exists
     * @throws SQLException if a database access error occurs
     */
    public Shift find(Badge badge) throws SQLException {
        ShiftDAO shiftDAO = daoFactory.getShiftDAO();
        String badge_id = badge.getId();
        Shift shift = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = daoFactory.getConnection();
            String query = "SELECT * FROM employee WHERE badgeid = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, badge_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                int shift_id = rs.getInt("shiftid");
                shift = shiftDAO.find(shift_id);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return shift;
    }
}

