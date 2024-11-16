package edu.jsu.mcis.cs310.tas_fa24.dao;

/**
 * The {@code DAOException} class represents a custom unchecked exception 
 * used in the DAO (Data Access Object) layer to handle database-related errors.
 * 
 * <p>This exception extends {@link RuntimeException}, allowing it to be 
 * thrown without requiring explicit handling by calling methods. It is 
 * typically used to wrap {@link SQLException} or other exceptions that occur 
 * during database operations, providing a more specific context for these errors.</p>
 * 
 * @version 1.0
 */
public class DAOException extends RuntimeException {

    /**
     * Constructs a new {@code DAOException} with the specified detail message.
     * 
     * @param message the detail message, providing context for the exception
     */
    public DAOException(String message) {
        super(message);
    }
}
