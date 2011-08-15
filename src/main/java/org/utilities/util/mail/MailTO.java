/*
 * Simple POJO for carrying data meant for sending mails.
 */

package org.utilities.util.mail;

import java.util.Properties;

/**
 *
 * @author bhaskar
 */
public class MailTO {
    private String[] to;
    private String from;
    private String subject;
    private String message;
    private String fullName;
    private Properties mailConfigurations;

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the mailConfigurations
     */
    public Properties getMailConfigurations() {
        return mailConfigurations;
    }

    /**
     * @param mailConfigurations the mailConfigurations to set
     */
    public void setMailConfigurations(Properties mailConfigurations) {
        this.mailConfigurations = mailConfigurations;
    }

    /**
     * @return the to
     */
    public String[] getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String[] to) {
        this.to = to;
    }
}
