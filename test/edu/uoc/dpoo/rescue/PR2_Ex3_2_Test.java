package edu.uoc.dpoo.rescue;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PR2_Ex3_2_Test {
    
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
    
    private PetRescue rescue;
    
    public PR2_Ex3_2_Test() {
        
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
        
        // Check that initially the list of alerts is empty
        assertNotNull(rescue.getAlerts());
        assertEquals((int)rescue.getAlerts().size(), 0);
                
        // Add a new rescues
        rescue.newRescue(pet1_type, pet1_id, pet1_name, pet1_pdd);
        rescue.newRescue(pet2_type, pet2_id, pet2_name, pet2_pdd);
        rescue.newRescue(pet3_type, pet3_id, pet3_name, pet3_pdd);
                
        // Check the list of alerts
        assertNotNull(rescue.getAlerts());
        assertEquals(3, (int)rescue.getAlerts().size());        
        
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
    public void ActiveStay() {                
        Pet pet = rescue.findPet(pet1_id);
        assertNotNull(pet);
        Home home = rescue.getRegisteredHomes().get(0);
        assertNotNull(home);
        Stay s = new Stay(new Date(), pet, home);
        assertNotNull(s);
        
        assertTrue(s.isActive());
        
        s.setFinisheddAt(new Date());
        
        assertFalse(s.isActive());
    }
    
  
    @Test
    public void HomePets() {                
        Pet pet = rescue.findPet(pet1_id);
        assertNotNull(pet);
        Home home = rescue.getRegisteredHomes().get(0);
        assertNotNull(home);
        Stay s = new Stay(new Date(), pet, home);
        assertNotNull(s);        
        assertTrue(s.isActive());
        
        // Assign a pet to a home
        assertEquals(0, (int)home.getCurrentPets().size());        
        home.getPets().add(s);
        assertEquals(1, (int)home.getCurrentPets().size());                        
        s.setFinisheddAt(new Date());
        assertFalse(s.isActive());
        assertEquals(0, (int)home.getCurrentPets().size());        
    }
    
    
    @Test
    public void PetHome() {                
        Pet pet = rescue.findPet(pet1_id);
        assertNotNull(pet);
        Home home = rescue.getRegisteredHomes().get(0);
        assertNotNull(home);
        Stay s = new Stay(new Date(), pet, home);
        assertNotNull(s);        
        assertTrue(s.isActive());
        
        // Assign a home to a pet
        assertNull(pet.getCurrentHome());        
        assertNull(rescue.getCurrentHome(pet.getId()));
        pet.getHomes().add(s);        
        assertNotNull(pet.getCurrentHome());
        assertNotNull(rescue.getCurrentHome(pet.getId()));
        s.setFinisheddAt(new Date());
        assertFalse(s.isActive());
        assertNull(pet.getCurrentHome());
        assertNull(rescue.getCurrentHome(pet.getId()));
    }
    
    @Test
    public void ShelterAccept() {                
        Pet pet_no_pdd = rescue.findPet(pet1_id);
        Pet pet_pdd = rescue.findPet(pet2_id);
        Pet pet_cat = rescue.findPet(pet3_id);
        assertNotNull(pet_no_pdd);
        assertNotNull(pet_pdd);
        assertNotNull(pet_cat);
        Home home_all = rescue.getRegisteredHomes().get(0);
        Home home_cat = rescue.getRegisteredHomes().get(1);        
        assertNotNull(home_all);
        assertNotNull(home_cat);
        
        // Check by type
        assertTrue(home_all.accept(pet_pdd));
        assertTrue(home_all.accept(pet_no_pdd));
        assertTrue(home_all.accept(pet_cat));
        assertFalse(home_cat.accept(pet_pdd));
        assertFalse(home_cat.accept(pet_no_pdd));
        assertTrue(home_cat.accept(pet_cat));
                
        // Check by pdd
        assertTrue(home_all.accept(pet_pdd));
        home_all.setAcceptPdd(false);
        assertFalse(home_all.accept(pet_pdd));
        home_all.setAcceptPdd(true);
        
        // Check by capacity
        Stay s = new Stay(new Date(), pet_pdd, home_all);
        assertNotNull(s);        
        assertTrue(s.isActive());
        assertTrue(home_all.accept(pet_pdd));
        home_all.getPets().add(s);
        assertFalse(home_all.accept(pet_no_pdd));
    }
    
    @Test
    public void FindHome() {                
        Pet pet_no_pdd = rescue.findPet(pet1_id);
        Pet pet_pdd = rescue.findPet(pet2_id);
        Pet pet_cat = rescue.findPet(pet3_id);
        assertNotNull(pet_no_pdd);
        assertNotNull(pet_pdd);
        assertNotNull(pet_cat);
        
        
        assertNotNull(rescue.findHome(pet1_id));
        assertNotNull(rescue.findHome(pet2_id));
        assertNotNull(rescue.findHome(pet3_id));
        assertEquals(1, (int)rescue.findHome(pet1_id).size());
        assertEquals(1, (int)rescue.findHome(pet2_id).size());
        assertEquals(2, (int)rescue.findHome(pet3_id).size());
    }
    
    @Test
    public void AssignHome() {                
        Pet pet_no_pdd = rescue.findPet(pet1_id);
        Pet pet_pdd = rescue.findPet(pet2_id);
        Pet pet_cat = rescue.findPet(pet3_id);
        assertNotNull(pet_no_pdd);
        assertNotNull(pet_pdd);
        assertNotNull(pet_cat);
        Home home_all = rescue.getRegisteredHomes().get(0);
        Home home_cat = rescue.getRegisteredHomes().get(1);        
        assertNotNull(home_all);
        assertNotNull(home_cat);
                       
        rescue.assignHome(pet_cat, home_all);
        
        assertNotNull(rescue.findHome(pet1_id));
        assertNotNull(rescue.findHome(pet2_id));
        assertEquals(0, (int)rescue.findHome(pet1_id).size());
        assertEquals(0, (int)rescue.findHome(pet2_id).size());
        
        assertEquals(pet_cat.getCurrentHome(), home_all);
        assertNull(pet_pdd.getCurrentHome());
        assertNull(pet_no_pdd.getCurrentHome());
        
        assertEquals(1, (int)home_all.getCurrentPets().size());
        assertEquals(0, (int)home_cat.getCurrentPets().size());    
    }
}

