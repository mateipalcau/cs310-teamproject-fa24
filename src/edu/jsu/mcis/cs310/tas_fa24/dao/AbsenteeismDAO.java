package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;

/**
 * Data Access Object (DAO) for managing absenteeism data in the database.
 * 
 * <p>This class provides methods for retrieving and storing absenteeism records 
 * for employees, including functionality to find, create, and update records 
 * in the database.</p>
 * 
 * <p>Queries are executed using prepared statements to prevent SQL injection 
 * and to ensure robust database operations.</p>
 * 
 * @author Matei P
 * @version 1.0
 */
public class AbsenteeismDAO {

    private final DAOFactory daoFactory;
    private static final String QUERY_FIND = "SELECT * FROM absenteeism WHERE employeeid = ?";
    private static final String QUERY_CREATE = "INSERT INTO absenteeism (employeeid, payperiod, percentage) VALUES (?, ?, ?)";
    private static final String QUERY_UPDATE = "UPDATE absenteeism SET percentage = ?, payperiod=? WHERE employeeid = ?";

    /**
     * Constructs an AbsenteeismDAO with the specified DAOFactory.
     * 
     * @param daoFactory the factory for obtaining database connections
     */
    AbsenteeismDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Retrieves an absenteeism record for a specific employee and date.
     * 
     * @param employee the employee whose absenteeism record is to be retrieved
     * @param date the date for which the absenteeism record is to be retrieved
     * @return an {@code Absenteeism} object containing the record, or {@code null} if no record is found
     * @throws SQLException if a database access error occurs
     */
    public Absenteeism find(Employee employee, LocalDate date) throws SQLException {
        Absenteeism absent = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        int id = employee.getId();

        try {
            conn = daoFactory.getConnection();

            if (conn.isValid(0)) {
                ps = conn.prepareStatement(QUERY_FIND);
                ps.setString(1, Integer.toString(id));

                rs = ps.executeQuery();

                if (rs.next()) {
                    double percentage = rs.getDouble("percentage");
                    BigDecimal p = BigDecimal.valueOf(percentage);
                    absent = new Absenteeism(employee, date, p);
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
            if (rs != null) {
                try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
            }
            if (ps != null) {
                try { ps.close(); } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return absent;
    }

    /**
     * Creates or updates an absenteeism record in the database.
     * 
     * <p>If a record already exists for the employee and the specified pay period, 
     * the record is updated. Otherwise, a new record is created.</p>
     * 
     * @param absent the absenteeism record to be created or updated
     * @return {@code true} if the record was successfully created or updated; {@code false} otherwise
     * @throws SQLException if a database access error occurs
     */
    public boolean create(Absenteeism absent) throws SQLException {
        boolean result = false;
        AbsenteeismDAO absenteeismDAO = daoFactory.getAbsenteeismDAO();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        Employee absent_employee = absent.getEmployee();
        LocalDate absent_date = absent.getStartPayPeriod();
        BigDecimal absent_percentage = absent.getPercentage();

        Absenteeism absent2 = absenteeismDAO.find(absent_employee, absent_date);

        try {
            conn = daoFactory.getConnection();
            if (conn.isValid(0)) {
                if (absent_employee.getId() == absent2.getEmployee().getId() &&
                    absent.getStartPayPeriod().compareTo(absent2.getStartPayPeriod()) == 0) {
                    ps = conn.prepareStatement(QUERY_UPDATE);
                    ps.setDouble(1, absent.getPercentage().doubleValue());
                    ps.setDate(2, Date.valueOf(absent.getStartPayPeriod()));
                    ps.setInt(3, absent.getEmployee().getId());
                } 
                else if (absent_employee.getId() != absent2.getEmployee().getId() &&
                         absent.getStartPayPeriod().compareTo(absent2.getStartPayPeriod()) != 0) {
                    ps = conn.prepareStatement(QUERY_CREATE);
                    ps.setInt(1, absent.getEmployee().getId());
                    ps.setDate(2, Date.valueOf(absent.getStartPayPeriod()));
                    ps.setDouble(3, absent.getPercentage().doubleValue());
                }

                int updateCount = ps.executeUpdate();

                if (updateCount > 0) {
                    result = true;
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
            if (rs != null) {
                try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
            }
            if (ps != null) {
                try { ps.close(); } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return result;
    }
}

