package edu.uoc.dpoo.rescue;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PR2_Ex4_Test {
    
    private final String pet1_id = "100-200-300-400-500";
    private final String pet1_name = "Barney";
    private final PetType pet1_type = PetType.DOG;
    private final Boolean pet1_pdd = false;
    
    private final String pet2_id = "101-201-301-401-501";
    private final String pet2_name = "Lena";
    private final PetType pet2_type = PetType.DOG;
    private final Boolean pet2_pdd = true;
    
    private final String pet3_id = "102-202-302-402-502";
    private final String pet3_name = "Garfield";
    private final PetType pet3_type = PetType.CAT;
    private final Boolean pet3_pdd = false;
    
    private final String volunteer1_name = "Robert";
    private final String volunteer1_email = "robert@rescue.dpoo.uoc.edu";
    private final boolean volunteer1_allowPdd = false;
    
    private final String volunteer2_name = "Julia";
    private final String volunteer2_email = "julia@rescue.dpoo.uoc.edu";
    private final boolean volunteer2_allowPdd = true;
    
    private final String shelter1_name = "Susana";
    private final String shelter1_address = "Susana's home";
    private final String shelter1_email = "susana@dpoo.uoc.edu";
    private final boolean shelter1_garden = true;
    private final boolean shelter1_pdd = true;
    private final int shelter1_capacity = 1;
    private final int shelter1_maxDays = 7;
    private final boolean shelter1_vet = true;
    
    private final String shelter2_name = "John";
    private final String shelter2_address = "John's home";
    private final String shelter2_email = "john@dpoo.uoc.edu";
    private final boolean shelter2_garden = false;
    private final boolean shelter2_pdd = false;
    private final int shelter2_capacity = 5;
    private final int shelter2_maxDays = 4;
    private final boolean shelter2_vet = false;
    
    
    PetRescue rescue;
    MailBox mailbox;
        
    public PR2_Ex4_Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {        
        rescue = new PetRescue();
        mailbox = new MailBox();
        
        rescue.addListener(mailbox);
        
        // Check that initially the list of volunteers is empty
        assertNotNull(rescue.getRegisteredVolunteers());
        assertEquals((int)rescue.getRegisteredVolunteers().size(), 0);
        
        try {
            // Add a new volunteer
            rescue.registerVolunteer(volunteer1_name, volunteer1_email, volunteer1_allowPdd);            
            // Add a new volunteer
            rescue.registerVolunteer(volunteer2_name, volunteer2_email, volunteer2_allowPdd);            
        } catch (Throwable t){            
            fail();            
        }        
        
        // Check that the list of volunteers have two elements
        assertNotNull(rescue.getRegisteredVolunteers());
        assertEquals(2, (int)rescue.getRegisteredVolunteers().size());
        
        // Check that initially the list of homes is empty
        assertNotNull(rescue.getHomes());
        assertEquals(0, (int)rescue.getHomes().size());        
        
        // Add a new Shelter home        
        try {            
            Shelter s = rescue.registerShelter(shelter1_name, shelter1_address, shelter1_email, shelter1_garden, shelter1_pdd, shelter1_capacity, shelter1_maxDays, shelter1_vet);
            assertNotNull(s);
            // Add a new types  
            s.addType(PetType.DOG);
            s.addType(PetType.CAT);
            assertEquals(2, (int)s.getAllowed().size());
        } catch (Throwable t){            
            fail();            
        }
        
        // Add a new Shelter home        
        try {            
            Shelter s = rescue.registerShelter(shelter2_name, shelter2_address, shelter2_email, shelter2_garden, shelter2_pdd, shelter2_capacity, shelter2_maxDays, shelter2_vet);
            assertNotNull(s);
            // Add a new type  
            s.addType(PetType.CAT);
            assertEquals(1, (int)s.getAllowed().size());
        } catch (Throwable t){            
            fail();            
        }
        
        // Check list of homes
        assertNotNull(rescue.getHomes());
        assertEquals(2, (int)rescue.getHomes().size());
    }
    
    @After
    public void tearDown() {
    }
    
        
    @Test
    public void Events() {
        
        // Check that initially the list of alerts is empty
        assertNotNull(rescue.getAlerts());
        assertEquals((int)rescue.getAlerts().size(), 0);
        
        // Check that initially the list of mails is empty
        assertNotNull(mailbox.getMails());
        assertEquals((int)mailbox.getMails().size(), 0);
                
        // Add a new rescue
        rescue.newRescue(pet1_type, pet1_id, pet1_name, pet1_pdd);
                
        // Check that the list of alerts have one element
        assertNotNull(rescue.getAlerts());
        assertEquals(1, (int)rescue.getAlerts().size());
                
        // Search for the new pet
        Pet pet = rescue.findPet(pet1_id);
        assertNotNull(pet);
        
        // Search for a volunteer
        List<Volunteer> availableVolunteers = rescue.findVolunteer(pet1_id);
        assertNotNull(availableVolunteers);
        assertTrue((int)availableVolunteers.size() > 0);        
        Volunteer volunteer = availableVolunteers.get(0);
        
        // Get the alert
        Alert rescueAlert = rescue.getUnassignedAlerts().get(0);
        assertNotNull(rescueAlert);
        
        // Check that the list of mails is empty
        assertNotNull(mailbox.getMails());
        assertEquals((int)mailbox.getMails().size(), 0);
        
        rescue.assignAlert(rescueAlert, volunteer);
        
        // Check that the list of mails is not empty
        assertNotNull(mailbox.getMails());
        assertEquals(1, (int)mailbox.getMails().size());
        
        // Check volunteer mails
        assertNotNull(mailbox.getSentMails(volunteer.getEmail()));
        assertEquals(1, (int)mailbox.getSentMails(volunteer.getEmail()).size());
        
        //Search for a home
        List<Home> availableHomes = rescue.findHome(pet.getId());
        assertNotNull(availableHomes);
        assertTrue((int)availableHomes.size() > 0);        
        Home home = availableHomes.get(0);
        
        // Assign the home
        rescue.assignHome(pet, home);
        
        // Check that the list of mails is not empty
        assertNotNull(mailbox.getMails());
        assertEquals(2, (int)mailbox.getMails().size());
        
        // Check home mails
        assertNotNull(mailbox.getSentMails(home.getEmail()));
        assertEquals(1, (int)mailbox.getSentMails(home.getEmail()).size());
    }
}

