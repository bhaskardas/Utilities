/*
 * This class serves the purpose of reading from a properties file. 
 * Functions are designed to reada single key=value pair or the entire 
 * at one go.
 */
package org.utilities.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Bhaskar Das
 * @created on 5th Jan'10
 */
public class PropertiesUtil {

    private static final String PROPERTY_FILE_PATH = "org/utilities/config/";
    private static final String PROPERTY_FILE_EXTENSION = ".properties";
    private static final String PARENT_PROPERTY_FILE_PATH = "org/utilities/config/master-config.properties";
    /**
     * The map variable to store all the properties file.
     */
    private static HashMap<String, Properties> propFileMap = new HashMap<String, Properties>();

    static {
        try {
            loadAllPropertiesFile();
        } catch (IOException ex) {
            Logger.getLogger(PropertiesUtil.class.getName()).log(Level.SEVERE, "Property File not found", ex);
        }
    }

    /**
     * 
     * @param fileName
     *              fileName without the extension. Just the name of the file.
     * @param key
     *          the name from the name-value pair for which the value is required.
     * @return
     */
    public static String readProperty(String fileName, String key) throws IOException, Exception {
        String value = null;
        if (propFileMap == null) {
            loadAllPropertiesFile();
        }
        if (propFileMap != null && propFileMap.size() > 0) {
            if (propFileMap.get(fileName) != null && propFileMap.containsKey(fileName)) {
                value = propFileMap.get(fileName).getProperty(key);
                if (value == null) {
                    throw new Exception("No value exist for the 'key' in the name-value pair for the specified 'fileName'");
                }
            } else {
                throw new Exception("File name passed does not exist. "
                        + "Check 'fileName' entry in master-config.properties or use the procedure 'readPropertyFile().'");
            }
        } else {
            throw new Exception("Cannot find master-config.properties in the package hierarchy 'org/utilities/config'");
        }

        return value;
    }

    /**
     * 
     * @param fileName
     *              fileName with extension
     * @param packageName
     *              package hierarchy of the file to load separated by '/'
     * @param key key
     *              for which the value is required.
     * @return
     */
    public static String readProperty(String fileName, String packageName, String key) throws IOException, Exception {
        Properties properties = loadPropertiesFile(packageName + fileName);
        if (properties != null) {
            return properties.getProperty(key);
        } else {
            //TODO: Custom exception : PropertyFileNotFoundException
            throw new Exception("Property file not found");
        }
    }

    /**
     * 
     * @param fileName
     *              file name without the extension
     * @return
     */
    public static HashMap<String, String> readPropertyFile(String fileName) throws IOException, Exception {
        HashMap<String, String> propMap = null;
        if (propFileMap == null) {
            loadAllPropertiesFile();
        }
        if (propFileMap != null && propFileMap.size() > 0) {
            if (propFileMap.get(fileName) != null && propFileMap.containsKey(fileName)) {
                propMap = populatePropertiesMap(propFileMap.get(fileName));
            } else {
                throw new Exception("Property file entry does not exist in the master-config.properties.");
            }
        } else {
            throw new Exception("Cannot find master-config.properties in the package hierarchy 'org/utilities/config'");
        }

        return propMap;
    }

    /**
     * 
     * @param fileName
     *              file name with extension
     * @param packageName
     *              package hierarchy of the file separated by '/'
     * @return
     * @throws IOException
     * @throws Exception
     */
     public static HashMap<String, String> readPropertyFile(String fileName, String packageName) throws IOException, Exception {
        Properties properties = loadPropertiesFile(packageName + fileName);
        if (properties != null) {
            return populatePropertiesMap(properties);
        } else {
            //TODO: Custom exception : PropertyFileNotFoundException
            throw new Exception("Property file not found");
        }
     }

    /**
     * 
     * @param qualifiedFileName
     *                      fully qualified name of the file.
     * @throws java.io.IOException
     */
    private static Properties loadPropertiesFile(String qualifiedFileName) throws IOException {
        Properties prop = null;
        InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(qualifiedFileName);
        if (inputStream != null) {
            prop = new Properties();
            prop.load(inputStream);
        }

        return prop;
    }

    /**
     * 
     * @throws java.io.IOException
     */
    private static void loadAllPropertiesFile() throws IOException {
        Properties masterProp = null;
        InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(PARENT_PROPERTY_FILE_PATH);
        if (inputStream != null) {
            masterProp = new Properties();
            masterProp.load(inputStream);
        }

        if (masterProp != null) {
            String key = null;
            Iterator<Object> propItr = masterProp.keySet().iterator();
            while (propItr.hasNext()) {
                key = propItr.next().toString();
                propFileMap.put(key, loadPropertiesFile(masterProp.getProperty(key)));
            }
        }
    }

    /**
     * 
     * @param prop
     * @return
     * @throws java.lang.Exception
     */
    private static HashMap<String, String> populatePropertiesMap(Properties prop) throws Exception {
        HashMap<String, String> configMap = null;
        if (prop != null) {
            String key = null;
            configMap = new HashMap<String, String>();
            Iterator<Object> propItr = prop.keySet().iterator();
            while (propItr.hasNext()) {
                key = propItr.next().toString();
                configMap.put(key, prop.getProperty(key));
            }
        }

        return configMap;
    }
}
