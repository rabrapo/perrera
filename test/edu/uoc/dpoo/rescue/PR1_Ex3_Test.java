package edu.uoc.dpoo.rescue;

import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PR1_Ex3_Test {
    
    private final String mail1_email = "user1@dpoo.uoc.edu";
    private final String mail1_subject = "subject for mail 1";
    private final String mail1_body = "body for mail 1";
    
    private final String mail2_email = "user1@dpoo.uoc.edu";
    private final String mail2_subject = "subject for mail 2";
    private final String mail2_body = "body for mail 2";
    
    private final String mail3_email = "user2@dpoo.uoc.edu";
    private final String mail3_subject = "subject for mail 3";
    private final String mail3_body = "body for mail 3";
    
                
    public PR1_Ex3_Test() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void sendMail() {
        MailBox mailBox = new MailBox();
        
        // Check that initially the list of mails is empty
        assertNotNull(mailBox.getSentMails(mail1_email));
        assertEquals((int)mailBox.getSentMails(mail1_email).size(), 0);
        
        // Send a new email
        mailBox.sendMail(mail1_email, mail1_subject, mail1_body);
        
        // Check that the list of sent mails contains sent mails
        assertNotNull(mailBox.getSentMails(mail1_email));
        assertEquals((int)mailBox.getSentMails(mail1_email).size(), 1);        
    }
    
    @Test
    public void sendMultipleMail() {
        MailBox mailBox = new MailBox();
        
        // Check that initially the list of mails is empty
        assertNotNull(mailBox.getSentMails(mail1_email));
        assertEquals((int)mailBox.getSentMails(mail1_email).size(), 0);
        assertNotNull(mailBox.getSentMails(mail3_email));
        assertEquals((int)mailBox.getSentMails(mail3_email).size(), 0);
        
        // Send a new email
        mailBox.sendMail(mail1_email, mail1_subject, mail1_body);
        
        // Check that the list of sent mails contains sent mails
        assertNotNull(mailBox.getSentMails(mail1_email));
        assertEquals((int)mailBox.getSentMails(mail1_email).size(), 1);        
        assertNotNull(mailBox.getSentMails(mail3_email));
        assertEquals((int)mailBox.getSentMails(mail3_email).size(), 0);
        
        // Send a new email
        mailBox.sendMail(mail2_email, mail2_subject, mail2_body);
        
        // Check that the list of sent mails contains sent mails
        assertNotNull(mailBox.getSentMails(mail1_email));
        assertEquals((int)mailBox.getSentMails(mail1_email).size(), 2);
        assertNotNull(mailBox.getSentMails(mail3_email));
        assertEquals((int)mailBox.getSentMails(mail3_email).size(), 0);
        
        // Send a new email
        mailBox.sendMail(mail3_email, mail3_subject, mail3_body);
        
        // Check that the list of sent mails contains sent mails
        assertNotNull(mailBox.getSentMails(mail1_email));
        assertEquals((int)mailBox.getSentMails(mail1_email).size(), 2);
        assertNotNull(mailBox.getSentMails(mail3_email));
        assertEquals((int)mailBox.getSentMails(mail3_email).size(), 1);
    }    
}
