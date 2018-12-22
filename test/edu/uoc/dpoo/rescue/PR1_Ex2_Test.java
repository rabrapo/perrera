package edu.uoc.dpoo.rescue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PR1_Ex2_Test {
    
    private final String volunteer1_name = "Robert";
    private final String volunteer1_email = "robert@rescue.dpoo.uoc.edu";
    private final boolean volunteer1_allowPdd = false;
    private final String volunteer2_name = "Julia";
    private final String volunteer2_email = "julia@rescue.dpoo.uoc.edu";
    private final boolean volunteer2_allowPdd = true;
    
        
    public PR1_Ex2_Test() {
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
    public void addVolunteer() {
        PetRescue rescue = new PetRescue();
        
        // Check that initially the list of volunteers is empty
        assertNotNull(rescue.getRegisteredVolunteers());
        assertEquals((int)rescue.getRegisteredVolunteers().size(), 0);
               
        try {
            // Add a new volunteer
            rescue.registerVolunteer(volunteer1_name, volunteer1_email, volunteer1_allowPdd);            
        } catch (Throwable t){            
            fail();            
        }        
        
        // Check that the list of volunteers have one element
        assertNotNull(rescue.getRegisteredVolunteers());
        assertEquals(1, (int)rescue.getRegisteredVolunteers().size());
    }
    
    @Test
    public void addMultipleVolunteers() {
        PetRescue rescue = new PetRescue();
        
        // Check that initially the list of volunteers is empty
        assertNotNull(rescue.getRegisteredVolunteers());
        assertEquals((int)rescue.getRegisteredVolunteers().size(), 0);
                
        try {
            // Add a new volunteer        
            rescue.registerVolunteer(volunteer1_name, volunteer1_email, volunteer1_allowPdd);
        } catch (Throwable t){            
            fail();            
        }
        
        // Check that the list of volunteers have one element
        assertNotNull(rescue.getRegisteredVolunteers());
        assertEquals(1, (int)rescue.getRegisteredVolunteers().size());
        
        try {
            // Add a new volunteer
        rescue.registerVolunteer(volunteer2_name, volunteer2_email, volunteer2_allowPdd);
        } catch (Throwable t){            
            fail();            
        }        
        
        // Check that the list of volunteers have two elements
        assertNotNull(rescue.getRegisteredVolunteers());
        assertEquals(2, (int)rescue.getRegisteredVolunteers().size());
    }
    
    @Test
    public void addDuplicatedVolunteers() {
        PetRescue rescue = new PetRescue();
        
        // Check that initially the list of volunteers is empty
        assertNotNull(rescue.getRegisteredVolunteers());
        assertEquals((int)rescue.getRegisteredVolunteers().size(), 0);
                
        try {
            // Add a new volunteer
            rescue.registerVolunteer(volunteer1_name, volunteer1_email, volunteer1_allowPdd);
        } catch (Throwable t){            
            fail();            
        }
        
        // Check that the list of volunteers have one element
        assertNotNull(rescue.getRegisteredVolunteers());
        assertEquals(1, (int)rescue.getRegisteredVolunteers().size());
                
        try {
            // Add a new volunteer
            rescue.registerVolunteer(volunteer2_name, volunteer2_email, volunteer2_allowPdd);
        } catch (Throwable t){            
            fail();            
        }
        
        // Check that the list of volunteers have two elements
        assertNotNull(rescue.getRegisteredVolunteers());
        assertEquals(2, (int)rescue.getRegisteredVolunteers().size());
        
        try {            
            // Add a new volunteer
            rescue.registerVolunteer(volunteer1_name, volunteer1_email, volunteer1_allowPdd);            
        } catch (Throwable t){
            if(!(t instanceof RescueException)) {
                fail();
            }
        }
        
        // Check that the list of volunteers have two elements
        assertNotNull(rescue.getRegisteredVolunteers());
        assertEquals(2, (int)rescue.getRegisteredVolunteers().size());
    }
}
