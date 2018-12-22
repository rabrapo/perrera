package edu.uoc.dpoo.rescue;

import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PR2_Ex2_3_Test {
    
    private final String pet1_id = "100-200-300-400-500";
    private final String pet1_name = "Barney";
    private final int pet1_age = 2;
    private final PetType pet1_type = PetType.DOG;
    private final Boolean pet1_pdd = false;
    
    private final String pet2_id = "101-201-301-401-501";
    private final String pet2_name = "Lena";
    private final int pet2_age = 4;
    private final PetType pet2_type = PetType.DOG;
    private final Boolean pet2_pdd = true;
    
    private final String pet3_id = "102-202-302-402-502";
    private final String pet3_name = "Garfield";
    private final int pet3_age = 6;
    private final PetType pet3_type = PetType.CAT;
    private final Boolean pet3_pdd = false;
    
    private final String volunteer1_name = "Robert";
    private final String volunteer1_email = "robert@rescue.dpoo.uoc.edu";
    private final boolean volunteer1_allowPdd = false;
    private final String volunteer2_name = "Julia";
    private final String volunteer2_email = "julia@rescue.dpoo.uoc.edu";
    private final boolean volunteer2_allowPdd = true;
           
        
    public PR2_Ex2_3_Test() {
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
    public void findPetAlerts() {
        PetRescue rescue = new PetRescue();
        
        // Check that initially the list of alerts is empty
        assertNotNull(rescue.getAlerts());
        assertEquals((int)rescue.getAlerts().size(), 0);
                
        // Add a new rescue
        rescue.newRescue(pet1_type, pet1_id, pet1_name, pet1_pdd);
                
        // Check that the list of alerts have one element
        assertNotNull(rescue.getAlerts());
        assertEquals(1, (int)rescue.getAlerts().size());
        
        // Add a new rescue for the same pet
        rescue.newRescue(pet1_type, pet1_id, pet1_name, pet1_pdd);
                
        // Check that the list of alerts have two elements
        assertNotNull(rescue.getAlerts());
        assertEquals(2, (int)rescue.getAlerts().size());
        
        // Add a new rescue for another pet
        rescue.newRescue(pet2_type, pet2_id, pet2_name, pet2_pdd);
                
        // Check that the list of alerts have three elements
        assertNotNull(rescue.getAlerts());
        assertEquals(3, (int)rescue.getAlerts().size());
        
        // Find alerts for pet 1
        List<Alert> pet1_alerts = rescue.getPetAlerts(pet1_id);
        assertNotNull(pet1_alerts);
        assertEquals(2, (int)pet1_alerts.size());
        
        // Find alerts for pet 2
        List<Alert> pet2_alerts = rescue.getPetAlerts(pet2_id);
        assertNotNull(pet2_alerts);
        assertEquals(1, (int)pet2_alerts.size());
    }
    
    @Test
    public void closeAlert() {
        PetRescue rescue = new PetRescue();
        
        // Check that initially the list of alerts is empty
        assertNotNull(rescue.getAlerts());
        assertEquals((int)rescue.getAlerts().size(), 0);
                
        // Add a new rescue
        rescue.newRescue(pet1_type, pet1_id, pet1_name, pet1_pdd);
                
        // Check that the list of alerts have one element
        assertNotNull(rescue.getAlerts());
        assertEquals(1, (int)rescue.getAlerts().size());
        
        // Add a new rescue for the same pet
        rescue.newRescue(pet1_type, pet1_id, pet1_name, pet1_pdd);
                
        // Check that the list of alerts have two elements
        assertNotNull(rescue.getAlerts());
        assertEquals(2, (int)rescue.getAlerts().size());
        
        // Find alerts for pet 1
        List<Alert> pet1_alerts = rescue.getPetAlerts(pet1_id);
        assertNotNull(pet1_alerts);
        assertEquals(2, (int)pet1_alerts.size());
        
        // Close first alert
        rescue.closeAlert(pet1_alerts.get(0));
        
        // Find alerts for pet 1
        List<Alert> pet1_alerts2 = rescue.getPetAlerts(pet1_id);
        assertNotNull(pet1_alerts2);
        assertEquals(1, (int)pet1_alerts2.size());        
    }
    
    @Test
    public void unassignedAlerts() {
        PetRescue rescue = new PetRescue();
        
        // Check that initially the list of alerts is empty
        assertNotNull(rescue.getAlerts());
        assertEquals((int)rescue.getAlerts().size(), 0);
                
        // Add a new rescue
        rescue.newRescue(pet1_type, pet1_id, pet1_name, pet1_pdd);
                
        // Check that the list of alerts have one element
        assertNotNull(rescue.getAlerts());
        assertEquals(1, (int)rescue.getAlerts().size());
        
        // Add a new rescue for the same pet
        rescue.newRescue(pet1_type, pet1_id, pet1_name, pet1_pdd);
                
        // Check that the list of alerts have two elements
        assertNotNull(rescue.getAlerts());
        assertEquals(2, (int)rescue.getAlerts().size());
        
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
        
        // Find unassigned alerts
        List<Alert> alerts = rescue.getUnassignedAlerts();
        assertNotNull(alerts);
        assertEquals(2, (int)alerts.size());
        
        // Close first alert
        rescue.closeAlert(alerts.get(0));
        
        List<Alert> alerts2 = rescue.getUnassignedAlerts();
        assertNotNull(alerts2);
        assertEquals(1, (int)alerts2.size());
        
        // Assign remaining alert
        alerts2.get(0).setAssignedAt(new Date());
        alerts2.get(0).setAssigned(rescue.getRegisteredVolunteers().get(0));
        
        List<Alert> alerts3 = rescue.getUnassignedAlerts();
        assertNotNull(alerts3);
        assertEquals(0, (int)alerts3.size());        
    }
    
}

