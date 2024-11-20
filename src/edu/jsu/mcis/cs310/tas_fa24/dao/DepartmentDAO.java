package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.Department;
import java.sql.*;

/**
 * The {@code DepartmentDAO} class provides data access operations for managing {@code Department} objects
 * in the database. It allows for retrieving department records by their unique ID.
 * 
 * <p>Dependencies:
 * <ul>
 *     <li>{@code DAOFactory} for obtaining database connections</li>
 * </ul>
 * </p>
 * 
 * @author [Garret Howell]
 */
public class DepartmentDAO {

    private final DAOFactory daoFactory;

    /**
     * Constructs a {@code DepartmentDAO} with the specified {@code DAOFactory}.
     *
     * @param daoFactory the factory object used to manage database connections
     */
    DepartmentDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Retrieves a {@code Department} from the database using the given department ID.
     *
     * @param id the ID of the department to retrieve
     * @return the {@code Department} object corresponding to the given ID, or {@code null}
     *         if no such department exists
     * @throws SQLException if a database access error occurs
     */
    public Department find(int id) throws SQLException {
        Department depart = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = daoFactory.getConnection();
            String query = "SELECT * FROM department WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                int departId = rs.getInt("id");
                String description = rs.getString("description");
                int terminalId = rs.getInt("terminalid");
                depart = new Department(departId, description, terminalId);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        return depart;
    }
}
