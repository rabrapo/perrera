
package edu.uoc.dpoo.rescue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PR2_Ex2_1_Test {
    
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
           
        
    public PR2_Ex2_1_Test() {
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
    public void registerRescueNewPet() {
        PetRescue rescue = new PetRescue();
        
        // Check that initially the list of alerts is empty
        assertNotNull(rescue.getAlerts());
        assertEquals((int)rescue.getAlerts().size(), 0);
                
        // Add a new rescue
        rescue.newRescue(pet1_type, pet1_id, pet1_name, pet1_pdd);
                
        // Check that the list of alerts have one element
        assertNotNull(rescue.getAlerts());
        assertEquals(1, (int)rescue.getAlerts().size());
    }
    
    @Test
    public void registerRescueExistingPet() {
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
    }
    
}

