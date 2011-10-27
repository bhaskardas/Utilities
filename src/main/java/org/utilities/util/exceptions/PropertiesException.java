package org.utilities.util.exceptions;

/**
 * 
 * @author bhaskardas
 *
 */
public class PropertiesException extends Exception{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public PropertiesException() {
		super();
	}

	/**
	 * 
	 * @param message
	 */
    public PropertiesException(String message) {
        super(message);
    }
    
    /**
     * 
     * @param message
     * @param e
     */
    public PropertiesException(String message, Throwable e){
    	super(message, e);
    }
}