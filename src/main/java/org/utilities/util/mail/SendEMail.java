/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utilities.util.mail;

import java.io.UnsupportedEncodingException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.utilities.util.exceptions.mail.MailerException;

/**
 *
 * @author bhaskar
 */
public class SendEMail implements EMail.EMailSender {

    private Logger logger = Logger.getLogger(SendEMail.class);
    private MailTO mailTO;

    public SendEMail() {
    }

    public SendEMail(MailTO mailTO) {
        this.mailTO = mailTO;
    }

    @Override
    public void sendMail() throws MailerException{

        logger.info("Starting the process of sending mail to super sales corporation.");
        Session session = Session.getDefaultInstance(
                mailTO.getMailConfigurations(),
                new MailAuthenticator(
                "super.sales@superconduits.com",
                "supersales@1st"));
        logger.info("Mail Session successfully created.");
        try {
            Transport.send(preparePayload(session));
            logger.info("Transport success...");
        } catch (MessagingException ex) {
            if(logger.isDebugEnabled())
                ex.printStackTrace();
            throw new MailerException(ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            if(logger.isDebugEnabled())
                ex.printStackTrace();
            throw new MailerException(ex.getMessage());
        }
    }

    /**
     * 
     * @param session
     * @return
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    private MimeMessage preparePayload(Session session)
            throws UnsupportedEncodingException, MessagingException {

        logger.info("Preparing mail message payload.");
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setRecipients(Message.RecipientType.TO, setRecipients());

        mimeMessage.setFrom(new InternetAddress(mailTO.getFrom(), mailTO.getFullName()));
        mimeMessage.setSubject(mailTO.getSubject());
        mimeMessage.setContent(mailTO.getMessage(), "text/html");

        logger.info("Transporting...");
        return mimeMessage;
    }

    /**
     * 
     * @return
     * @throws AddressException
     */
    private InternetAddress[] setRecipients() throws AddressException {
        logger.info("setting the recipients of the mail.");
        InternetAddress addresTo[] = new InternetAddress[mailTO.getTo().length];

        for (int i = 0; i < mailTO.getTo().length; i++) {
            addresTo[i] = new InternetAddress(mailTO.getTo()[i]);
        }

        return addresTo;
    }

    /**
     * 
     */
    private class MailAuthenticator extends Authenticator {

        private String userName;
        private String password;

        /**
         * 
         * @param userName
         * @param password
         */
        public MailAuthenticator(String userName, String password) {
            logger.debug("SETTING Authenticator object.");
            this.userName = userName;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userName, password);
        }


    }

    
    //TODO: Write test case
//    public static void main(String[] args) {
//        try {
//            StringBuilder htmlBodyCache = new StringBuilder();
//
//            htmlBodyCache.append("<html><head><meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' />")
//            .append("<title>New Query Posted</title>")
//            .append("</head><body>")
//            .append("<table width='100%' cellpadding='1' cellspacing='0' border='0'>")
//            .append("<tr><td colspan='4' nowrap='true'>New Query Posted on www.superconduits.com</td></tr>")
//            .append("<tr><td colspan='4' nowrap='true'>&nbsp;</td></tr>")
//            .append("<tr><td style='background-color:#F16824;border-bottom: 2px solid #66B821;padding: 4px;font-weight:bold;' colspan='4' width='100%' nowrap='true'>Query Details</td></tr>")
//            .append("<tr><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;font-weight:bold;' align='right' width='20%' nowrap='true'>Name:</td><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;' width='30%' colspan='3' nowrap='true'>" + "Bhaskar Das" + "</td>")
//            .append("<tr><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;font-weight:bold;' align='right' width='20%' nowrap='true'>Company Name:</td><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;' width='30%' nowrap='true'>" + "TestCompany" + "</td><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;font-weight:bold;' align='right' width='20%'>Company Website:</td><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;' width='30%'>" + "www.testCompany.com" + "</td></tr>")
//            .append("<tr><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;font-weight:bold;' align='right' width='20%' nowrap='true'>Phone Number:</td><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;' width='30%' colspan='3' nowrap='true'>" + "2342342" + "</td>")
//            .append("<tr><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;font-weight:bold;' align='right' width='20%' nowrap='true'>Email ID:</td><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;' width='30%' colspan='3' nowrap='true'>" + "anybody@xyz.com" + "</td>")
//            .append("<tr><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;font-weight:bold;' align='right' width='20%' nowrap='true'>Where did you hear about us?</td><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;' width='30%' colspan='3' nowrap='true'>" + "advertissing" + "</td>")
//            .append("<tr><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;font-weight:bold;' align='right' width='20%' nowrap='true'>Subject:</td><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;' width='30%' colspan='3' nowrap='true'>" + "Testing" + "</td>")
//            .append("<tr><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;font-weight:bold;' align='right' width='20%' nowrap='true'>Date Posted:</td><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;' width='30%' colspan='3' nowrap='true'>" + new Date() + "</td>")
//            .append("<tr><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;font-weight:bold;' align='right' width='20%' nowrap='true'>Query:</td><td style='background-color:#faf6ed;border-bottom: 1px dotted #58595B;padding: 4px;' width='30%' colspan='3' nowrap='true'>" + "No Query" + "</td>")
//            .append("<tr><td colspan='4' nowrap='true'>&nbsp;</td></tr>")
//            .append("<tr><td colspan='4' nowrap='true'>(Note: This is an autogenerated mail from www.superconduits.com. For replying use the Email-ID provided in the mail.)</td></tr>")
//            .append("</table></body></html>");
//
//            MailTO mailTO = new MailTO();
//            mailTO.setSubject("Testing");
//            mailTO.setMessage(htmlBodyCache.toString());
//            mailTO.setFrom("anybody@xyz.com");
//            mailTO.setFullName("Bhaskar Das");
//
//            Properties properties = null;
//            InputStream inputStream = SendEMail.class.getClassLoader()
//                .getResourceAsStream("org/utilities/util/mail-config.properties");
//
//            if(inputStream != null){
//                System.out.println("aasdasd_______________________>>>>>");
//                properties = new Properties();
//                try {
//                    properties.load(inputStream);
//                    mailTO.setMailConfigurations(properties);
//                    String to[] = properties.get("recipient-email-id").toString().split(",");
//                    mailTO.setTo(to);
//                } catch (IOException ex) {
//                    Logger.getLogger(SendEMail.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//
//
//            new SendEMail(mailTO).sendMail();
//        } catch (AddressException ex) {
//            Logger.getLogger(SendEMail.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MessagingException ex) {
//            Logger.getLogger(SendEMail.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(SendEMail.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
