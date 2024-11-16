package edu.jsu.mcis.cs310.tas_fa24.dao;

import java.io.*;
import java.util.Properties;

/**
 * The {@code DAOProperties} class provides a utility for loading and accessing 
 * database connection properties from a configuration file.
 * 
 * <p>This class reads properties from a file named {@code dao.properties}, which 
 * is expected to be in the class path. It supports property keys with prefixes, 
 * allowing for scoped configurations for different contexts or environments.</p>
 * 
 * <p>The properties file must follow the standard Java {@link Properties} file 
 * format. The keys are expected to follow the pattern {@code prefix.key}, where 
 * {@code prefix} is specified at run time.</p>
 * 
 * <p>Example properties file content:</p>
 * <pre>
 * db.url=jdbc:mysql://localhost:3306/mydatabase
 * db.username=myusername
 * db.password=mypassword
 * </pre>
 * 
 * <p>When using a prefix of {@code db}, the keys {@code url}, {@code username}, 
 * and {@code password} will be accessible via this class.</p>
 * 
 * @author 
 * @version 1.0
 */
public class DAOProperties {

    /** The name of the properties file to load. */
    private static final String PROPERTIES_FILE = "dao.properties";

    /** The {@link Properties} instance for storing loaded properties. */
    private static final Properties PROPERTIES = new Properties();

    /** The prefix used to scope property keys. */
    private final String prefix;

    // Static initializer block to load the properties file
    static {

        try {

            InputStream file = DAOProperties.class.getResourceAsStream(PROPERTIES_FILE);
            PROPERTIES.load(file);

        } catch (IOException e) {
            throw new DAOException(e.getMessage());
        }

    }

    /**
     * Constructs a {@code DAOProperties} instance with the specified prefix.
     * 
     * @param prefix the prefix used to scope property keys
     */
    public DAOProperties(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Retrieves a property value using the specified key and the current prefix.
     * 
     * <p>The full property key is constructed by combining the prefix and the 
     * specified key (e.g., {@code prefix.key}). If the property does not exist 
     * or is empty, {@code null} is returned.</p>
     * 
     * @param key the key for the desired property (without prefix)
     * @return the value of the property, or {@code null} if not found or empty
     */
    public String getProperty(String key) {

        String fullKey = prefix + "." + key;
        String property = PROPERTIES.getProperty(fullKey);

        if (property == null || property.trim().length() == 0) {
            property = null;
        }

        return property;

    }

}
