package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.Badge;
import edu.jsu.mcis.cs310.tas_fa24.Employee;
import edu.jsu.mcis.cs310.tas_fa24.Shift;
import edu.jsu.mcis.cs310.tas_fa24.Department;
import edu.jsu.mcis.cs310.tas_fa24.EmployeeType;
import java.sql.*;
import java.time.LocalDateTime;

/**
 * The {@code EmployeeDAO} class provides data access operations for managing {@code Employee} objects
 * in the database. It allows for retrieving employee records by their ID or associated {@code Badge}.
 * 
 * <p>Dependencies:
 * <ul>
 *     <li>{@code BadgeDAO} for retrieving badge details</li>
 *     <li>{@code DepartmentDAO} for retrieving department details</li>
 *     <li>{@code ShiftDAO} for retrieving shift details</li>
 * </ul>
 * </p>
 * 
 * @author Garret Howell
 */
public class EmployeeDAO {

    private final DAOFactory daoFactory;

    /**
     * Constructs an {@code EmployeeDAO} with the specified {@code DAOFactory}.
     *
     * @param daoFactory the factory object used to manage database connections and other DAOs
     */
    EmployeeDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Retrieves an {@code Employee} from the database using the given employee ID.
     *
     * @param id the ID of the employee to retrieve
     * @return the {@code Employee} object corresponding to the given ID, or {@code null} 
     *         if no such employee exists
     * @throws SQLException if a database access error occurs
     */
    public Employee find(int id) throws SQLException {
        Employee employee = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = daoFactory.getConnection(); 
            String query = "SELECT * FROM employee WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                String firstname = rs.getString("firstname");
                String middlename = rs.getString("middlename");
                String lastname = rs.getString("lastname");
                LocalDateTime active = rs.getTimestamp("active").toLocalDateTime();
                BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
                Badge badge = badgeDAO.find(rs.getString("badgeid"));
                DepartmentDAO departmentDAO = daoFactory.getDepartmentDAO();
                Department department = departmentDAO.find(rs.getInt("departmentid"));
                ShiftDAO shiftDAO = daoFactory.getShiftDAO();
                Shift shift = shiftDAO.find(rs.getInt("shiftid"));
                EmployeeType employeetype = EmployeeType.values()[rs.getInt("employeetypeid")];
                employee = new Employee(id, firstname, middlename, lastname, active, badge, department, shift, employeetype);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return employee;
    }

    /**
     * Retrieves an {@code Employee} from the database using the associated {@code Badge}.
     *
     * @param badge the {@code Badge} object associated with the employee
     * @return the {@code Employee} object corresponding to the given badge, or {@code null} 
     *         if no such employee exists
     * @throws SQLException if a database access error occurs
     */
    public Employee find(Badge badge) throws SQLException {
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        String badge_id = badge.getId();
        Employee employee = null;
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
                int id = rs.getInt("id");
                employee = employeeDAO.find(id);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return employee;
    }
}
