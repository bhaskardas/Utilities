/**
 * Copyright Super Sales Corporation 2009,  
 * All rights reserved.
 */

package org.utilities.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.utilities.util.exceptions.PropertiesException;
import org.utilities.util.exceptions.PropertyFileNotFoundException;

/**
 * This class serves the purpose of reading from a properties file. 
 * Functions are designed to read a single key=value pair or the 
 * entire file at one go. Once read, the application has been 
 * designed to keep the read files in memory to save costly 
 * I/O time until the application exits or ends.
 * 
 * @author bhaskar.das
 * @created on 5th Jan 2010
 * @version: 1.0, 1.1
 * @changeLog:
 * 		as on 02 Oct 2011 - By bhaskar.das
 * 							1. Changed the implementation of the
 * 							class.
 * 							2. Added Custom Exception Hierarchy
 */
public class PropertiesUtil {
    /**
     * The map variable to store all the properties file.
     */
    private static Map<String, Properties> propFileMap = new HashMap<String, Properties>();
    
    /**
     * master property file path.
     */
    private static final String PARENT_PROPERTY_FILE_PATH = "org/utilities/config/master-config.properties";
    private static Logger logger = Logger.getLogger(PropertiesUtil.class);

    static {
        try {
			loadAllPropertiesFile();
		} catch (PropertiesException e) {
			e.printStackTrace();
		}
    }

    /**
     * This function can be used when an entry with the key fileName is
     * present in the master-config.properties file.
     * Returns the value associated with the specified key as parameter.
     * If the value corresponding to the specified key is not found, then
     * null is returned. The client program should therefore, check for 
     * null values returned.
     * 
     * @param fileName
     *              fileName without the extension and the package hierarchy. 
     *              Just the name of the file.
     * @param key
     *          the name from the name-value pair for which the value is required.
     * @return
     * 		value for the key specified.
     * @throws PropertiesException
     * 		When master-config.properties is not found. Also when property file 
     * 		with the the fileName parameter is not found.
     */
    public static String readProperty(String fileName, String key) throws PropertiesException {
        if (propFileMap.containsKey(fileName) && propFileMap.get(fileName) != null) {
            return propFileMap.get(fileName).getProperty(key);
        } else {
        	StringBuilder errorBuilder = new StringBuilder();
        	errorBuilder.append("fileName parameter with the name: ")
        				.append(fileName)
        				.append(" does not exist. ")
        				.append("Check ")
        				.append(fileName)
        				.append(" entry in master-config.properties file.");
            throw new PropertyFileNotFoundException(errorBuilder.toString());
        }
    }

    /**
     * This function is to be used when the property file to be loaded 
     * and read is not present in the master-config.properties file, ie.,
     * no entry exists for the fileName parameter in the 
     * master-config.properties file.
     * 
     * @param fileName
     *              fileName with extension
     * @param packageName
     *              package hierarchy of the file to be loaded separated by '/'
     * @param key 
     * 				key for which the value is required.
     * @return 
 * 				the value for the key specified
     * @throws PropertiesException
     */
    public static String readProperty(String fileName, String packageName, String key) throws PropertiesException {
    	try{
    		return loadPropertiesFile(packageName + fileName).getProperty(key);
    	} catch (PropertiesException e) {
			e.printStackTrace();
			throw e;
		}
    }
    
    /**
     * To be used when the fileName entry is present
     * in the master-config.properties file as a key,
     * for which a value exists.
     * Reads a property file with the name as <b>fileName</b>
     * (not a fully qualified name). If the fileName entry
     * is not found in master-config.properties file,
     * PropertyFileNotFoundException is thrown.
     * @param fileName
     *              file name without the extension
     * @return
     * 		map (key=value pairs) representation of the entire property file.
     * @throws PropertiesException
     * 			if the property file is not found; also for
     * 			all other types of exception.
     */
    public static HashMap<String, String> readPropertyFile(String fileName) throws PropertiesException {
        
        if (propFileMap.containsKey(fileName) && propFileMap.get(fileName) != null) {
            return populatePropertiesMap(propFileMap.get(fileName));
        } else {
        	StringBuilder errorBuilder = new StringBuilder();
        	errorBuilder.append("fileName parameter with the name: ")
			.append(fileName)
			.append(" does not exist. ")
			.append("Check ")
			.append(fileName)
			.append(" entry in master-config.properties file.");
            throw new PropertyFileNotFoundException(errorBuilder.toString());
        }
    }

    /**
     * To be used when there is no corresponding entry
     * for the property file with the fullyQualifedName as
     * follows: packageName +fileName in the
     * master-config.properties file.
     * Reads a property file with the combination of the 
     * specified fileName and the packageName parameter.
     * If the package of the property file is the default
     * package, empty should be passed. The fileName should
     * contain the extension; other wise PropertiesException
     * will be thrown.
     * If no value with the fileName (packageName + fileName)
     * is found, an empty map is returned.
     * 
     * @param fileName
     *              file name with extension
     * @param packageName
     *              package hierarchy of the file separated by '/'
     * @return 
     * 			map (key=value pairs) representation of the entire property file.
     * @throws PropertiesException
     * 			if the property file is not found; also for
     * 			all other types of exception.
     */
     public static HashMap<String, String> readPropertyFile(String fileName, String packageName) 
     							throws PropertiesException {
    	 try{
    		 return populatePropertiesMap(loadPropertiesFile(packageName + fileName));
    	 }catch (PropertiesException e) {
			e.printStackTrace();
			throw e;
		}
     }

    /**
     * This function is responsible for loading all
     * the properties file into the <b>propFileMap</b>
     * map. It reads the master-config.properties file
     * to identify the number of property files used
     * in the entire application. To load a specific
     * property file, make an entry into the
     * master-config.properties.
     * The <b>propFileMap</b> has the format:
     * 'file-name-without-the-extension' as key
     * and Properties object containing the entire
     * file contents as value of the map.
     * 
     * @throws PropertiesException
     * 				if no master-config.poperty file is found
     * 				or if the master-config.poperty file is empty.
     *				or if there is any I/O exception while loading
     *				the file from disk.
     */
    private static void loadAllPropertiesFile() throws PropertiesException {
        InputStream inputStream = getInputStream(PARENT_PROPERTY_FILE_PATH);
        Properties masterProp = null;
		
        try {
			masterProp = loadProperties(inputStream);
		} catch (PropertiesException e) {
			if(logger.isDebugEnabled())
				e.printStackTrace();
			throw e;
		}
        
        if (masterProp == null) {
        	throw new PropertiesException("master-config.properties received in PropertiesUtil.loadAllPropertiesFile() is null.");
        }
        
        if(masterProp.size() == 0){
        	throw new PropertiesException("master-config.properties received in PropertiesUtil.loadAllPropertiesFile() is empty.");
        }
        
        String key = null;
        Iterator<Object> propItr = masterProp.keySet().iterator();
        while (propItr.hasNext()) {
            key = propItr.next().toString();
            propFileMap.put(key, loadPropertiesFile(masterProp.getProperty(key)));
        }
    }
    
    /**
     * 
     * @param fullyQualifiedFileName
     *                      fully qualified name of the file.
     * @throws PropertiesException                   
     */
    private static Properties loadPropertiesFile(String fullyQualifiedFileName) throws PropertiesException{
        return loadProperties(getInputStream(fullyQualifiedFileName));
    }
    
    /**
     * 
     * @param fullyQualifiedFileName
     * @return
     */
    private static InputStream getInputStream(String fullyQualifiedFileName){
    	return PropertiesUtil.class.getClassLoader().getResourceAsStream(fullyQualifiedFileName);
    }
    
    /**
     * 
     * @param inputStream
     * @return
     * @throws PropertiesException
     */
    private static Properties loadProperties(InputStream inputStream) 
    			throws PropertiesException{
    	if(inputStream == null){
    		throw new PropertyFileNotFoundException("Property File Not Found");
    	}
    	
    	Properties properties = new Properties();
    	try {
			properties.load(inputStream);
		} catch (IOException e) {
			if(logger.isDebugEnabled())
				e.printStackTrace();
			throw new PropertiesException(e.getMessage(), e);
		}
    	return properties;
    }

    /**
     * 
     * @param prop
     * @return
     * @throws PropertiesException
     */
    private static HashMap<String, String> populatePropertiesMap(Properties prop) throws PropertiesException {
    	try{
    		HashMap<String, String> configMap = new HashMap<String, String>();;
            String key = null;
            Iterator<Object> propItr = prop.keySet().iterator();
            while (propItr.hasNext()) {
                key = propItr.next().toString();
                configMap.put(key, prop.getProperty(key));
            }
            return configMap;
    	}catch (Exception e) {
    		if(logger.isDebugEnabled())
    			e.printStackTrace();
			throw new PropertiesException(e.getMessage(), e);
		}
    }
}