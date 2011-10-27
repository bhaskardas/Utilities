package org.utilities.util.exceptions;

/**
 * 
 * @author bhaskardas
 *
 */
public class PropertyFileNotFoundException extends PropertiesException{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public PropertyFileNotFoundException() {
		super();
	}

	/**
	 * 
	 * @param message
	 */
    public PropertyFileNotFoundException(String message) {
        super(message);
    }
    
    /**
     * 
     * @param message
     * @param e
     */
    public PropertyFileNotFoundException(String message, Throwable e){
    	super(message, e);
    }
}