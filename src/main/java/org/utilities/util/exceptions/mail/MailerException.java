/*
 * Top level exception class for handling email related exceptions.
 */

package org.utilities.util.exceptions.mail;

/**
 *
 * @author bhaskar
 */
public class MailerException extends Exception{
    public MailerException() {
    }

    public MailerException(String message) {
        super(message);
    }
}
