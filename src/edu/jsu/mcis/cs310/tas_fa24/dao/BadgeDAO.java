package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.Badge;
import java.sql.*;

/**
 * The {@code BadgeDAO} class provides data access methods for retrieving 
 * {@link Badge} objects from the database. This class interacts with the 
 * database to perform operations on the {@code badge} table.
 * 
 * <p>This class is part of the DAO (Data Access Object) pattern used to
 * abstract and encapsulate access to the database. It ensures that all
 * database interactions related to badges are handled consistently.</p>
 * 
 * @author 
 * @version 1.0
 */
public class BadgeDAO {

    /** SQL query to find a badge by its ID. */
    private static final String QUERY_FIND = "SELECT * FROM badge WHERE id = ?";

    /** Reference to the {@link DAOFactory} for database connection management. */
    private final DAOFactory daoFactory;

    /**
     * Constructs a {@code BadgeDAO} with the specified {@link DAOFactory}.
     * 
     * @param daoFactory the DAOFactory instance used to manage database connections
     */
    BadgeDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Finds and retrieves a {@link Badge} object from the database using the specified ID.
     * 
     * <p>This method executes a SQL query to fetch a badge record with the given ID.
     * If a matching record is found, a {@link Badge} object is created and returned.
     * If no record is found, {@code null} is returned.</p>
     * 
     * @param id the ID of the badge to retrieve
     * @return a {@link Badge} object if found, or {@code null} if no matching record exists
     * @throws DAOException if a database access error occurs
     */
    public Badge find(String id) {
        Badge badge = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {
                ps = conn.prepareStatement(QUERY_FIND);
                ps.setString(1, id);

                boolean hasresults = ps.execute();

                if (hasresults) {
                    rs = ps.getResultSet();

                    while (rs.next()) {
                        String description = rs.getString("description");
                        badge = new Badge(id, description);
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

        return badge;
    }
}
