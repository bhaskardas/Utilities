/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.utilities.util.mail;

import org.utilities.util.exceptions.mail.MailerException;

/**
 *
 * @author bhaskar
 */
public interface EMail extends Message{

    interface EMailSender extends EMail{
        void sendMail() throws MailerException;
    }

    interface EMailReceiver extends EMail{
        void receiveMail();
    }
}
