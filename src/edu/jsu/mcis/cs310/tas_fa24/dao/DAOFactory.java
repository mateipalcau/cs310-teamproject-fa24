package edu.jsu.mcis.cs310.tas_fa24.dao;

import java.sql.*;

/**
 * The {@code DAOFactory} class provides a centralized factory for creating and managing 
 * DAO (Data Access Object) instances. It also establishes and manages the database 
 * connection used by these DAOs.
 * 
 * <p>This class uses a configuration prefix to load database connection properties 
 * (e.g., URL, username, password) and initializes a single {@link Connection} instance 
 * that is shared among all DAOs created by this factory.</p>
 * 
 * <p>The DAOFactory provides access to specific DAOs, including:</p>
 * <ul>
 * <li>{@link BadgeDAO}</li>
 * <li>{@link ShiftDAO}</li>
 * <li>{@link PunchDAO}</li>
 * <li>{@link DepartmentDAO}</li>
 * <li>{@link EmployeeDAO}</li>
 * <li>{@link AbsenteeismDAO}</li>
 * </ul>
 * 
 * @author Matthew Bright
 * @version 1.0
 */
public final class DAOFactory {

    /** The property name for the database URL. */
    private static final String PROPERTY_URL = "url";
    
    /** The property name for the database username. */
    private static final String PROPERTY_USERNAME = "username";
    
    /** The property name for the database password. */
    private static final String PROPERTY_PASSWORD = "password";

    /** The database URL. */
    private final String url;

    /** The database username. */
    private final String username;

    /** The database password. */
    private final String password;

    /** The single {@link Connection} instance used by the factory. */
    private Connection conn = null;

    /**
     * Constructs a {@code DAOFactory} instance using the specified configuration prefix.
     * 
     * <p>The configuration prefix is used to load database connection properties 
     * (e.g., URL, username, and password) via the {@link DAOProperties} class. A 
     * {@link Connection} to the database is initialized during construction.</p>
     * 
     * @param prefix the configuration prefix for loading database properties
     * @throws DAOException if a database connection error occurs
     */
    public DAOFactory(String prefix) {

        DAOProperties properties = new DAOProperties(prefix);

        this.url = properties.getProperty(PROPERTY_URL);
        this.username = properties.getProperty(PROPERTY_USERNAME);
        this.password = properties.getProperty(PROPERTY_PASSWORD);

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * Retrieves the database connection managed by this factory.
     * 
     * @return the {@link Connection} instance
     */
    public Connection getConnection() {
        return conn;
    }

    /**
     * Creates and returns an instance of {@link BadgeDAO}.
     * 
     * @return a {@link BadgeDAO} instance
     */
    public BadgeDAO getBadgeDAO() {
        return new BadgeDAO(this);
    }

    /**
     * Creates and returns an instance of {@link ShiftDAO}.
     * 
     * @return a {@link ShiftDAO} instance
     */
    public ShiftDAO getShiftDAO() {
        return new ShiftDAO(this);
    }

    /**
     * Creates and returns an instance of {@link PunchDAO}.
     * 
     * @return a {@link PunchDAO} instance
     */
    public PunchDAO getPunchDAO() {
        return new PunchDAO(this);
    }

    /**
     * Creates and returns an instance of {@link DepartmentDAO}.
     * 
     * @return a {@link DepartmentDAO} instance
     */
    public DepartmentDAO getDepartmentDAO() {
        return new DepartmentDAO(this);
    }

    /**
     * Creates and returns an instance of {@link EmployeeDAO}.
     * 
     * @return a {@link EmployeeDAO} instance
     */
    public EmployeeDAO getEmployeeDAO() {
        return new EmployeeDAO(this);
    }

    /**
     * Creates and returns an instance of {@link AbsenteeismDAO}.
     * 
     * @return an {@link AbsenteeismDAO} instance
     */
    public AbsenteeismDAO getAbsenteeismDAO() {
        return new AbsenteeismDAO(this);
    }
}
