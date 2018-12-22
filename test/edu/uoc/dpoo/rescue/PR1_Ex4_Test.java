package edu.uoc.dpoo.rescue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PR1_Ex4_Test {
    
    private final String shelter1_name = "Susana";
    private final String shelter1_address = "Susana's home";
    private final String shelter1_email = "susana@dpoo.uoc.edu";
    private final boolean shelter1_garden = true;
    private final boolean shelter1_pdd = true;
    private final int shelter1_capacity = 3;
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
    
    private final String adoption1_name = "Anais";
    private final String adoption1_address = "Anais's home";
    private final String adoption1_email = "anais@dpoo.uoc.edu";
    private final boolean adoption1_garden = true;
    private final boolean adoption1_pdd = false;
    private final int adoption1_age = 3;    
                    
    public PR1_Ex4_Test() {
        
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
    public void addShelter() {
        PetRescue rescue = new PetRescue();
        
        // Check that initially the list of homes is empty
        assertNotNull(rescue.getHomes());
        assertEquals((int)rescue.getHomes().size(), 0);
        
        try {
            // Add a new Shelter home
            Shelter s = rescue.registerShelter(shelter1_name, shelter1_address, shelter1_email, shelter1_garden, shelter1_pdd, shelter1_capacity, shelter1_maxDays, shelter1_vet);
            
            assertNotNull(s);
            
            // Check that allowed types is empty
            assertNotNull(s.getAllowed());
            assertEquals((int)s.getAllowed().size(), 0);

            // Check that the list of homes contains the new home
            assertNotNull(rescue.getHomes());
            assertEquals((int)rescue.getHomes().size(), 1);
        } catch (Throwable t){            
            fail();            
        }        
    }
    
    @Test
    public void addAdoption() {
        PetRescue rescue = new PetRescue();
        
        // Check that initially the list of homes is empty
        assertNotNull(rescue.getHomes());
        assertEquals((int)rescue.getHomes().size(), 0);
        
        try {
            // Add a new Adoption home
            Adoption a = rescue.registerAdoption(adoption1_name, adoption1_address, adoption1_email, adoption1_garden, adoption1_pdd, adoption1_age);
            assertNotNull(a);

            // Check that allowed types is empty
            assertNotNull(a.getAllowed());
            assertEquals((int)a.getAllowed().size(), 0);

            // Check that the list of homes contains the new home
            assertNotNull(rescue.getHomes());
            assertEquals((int)rescue.getHomes().size(), 1);
        } catch (Throwable t){            
            fail();            
        }
    }
    
    @Test
    public void addMixedHomes() {
        PetRescue rescue = new PetRescue();
        
        // Check that initially the list of homes is empty
        assertNotNull(rescue.getHomes());
        assertEquals((int)rescue.getHomes().size(), 0);
        
        try {
            // Add a new Shelter home
            Shelter s = rescue.registerShelter(shelter1_name, shelter1_address, shelter1_email, shelter1_garden, shelter1_pdd, shelter1_capacity, shelter1_maxDays, shelter1_vet);
            assertNotNull(s);
        } catch (Throwable t){            
            fail();            
        }
        
        try {
            // Add a new Adoption home
            Adoption a = rescue.registerAdoption(adoption1_name, adoption1_address, adoption1_email, adoption1_garden, adoption1_pdd, adoption1_age);
            assertNotNull(a);
        } catch (Throwable t){            
            fail();            
        }
        
        try {
            // Add a new Shelter home
            Shelter s2 = rescue.registerShelter(shelter2_name, shelter2_address, shelter2_email, shelter2_garden, shelter2_pdd, shelter2_capacity, shelter2_maxDays, shelter2_vet);
            assertNotNull(s2);
        } catch (Throwable t){            
            fail();            
        }
        
        // Check that the list of homes contains all new homes
        assertNotNull(rescue.getHomes());
        assertEquals((int)rescue.getHomes().size(), 3);
    }
    
    @Test
    public void addDuplicatedHomes() {
        PetRescue rescue = new PetRescue();
        
        // Check that initially the list of homes is empty
        assertNotNull(rescue.getHomes());
        assertEquals((int)rescue.getHomes().size(), 0);
        
        try {
            // Add a new Shelter home
            Shelter s = rescue.registerShelter(shelter1_name, shelter1_address, shelter1_email, shelter1_garden, shelter1_pdd, shelter1_capacity, shelter1_maxDays, shelter1_vet);
            assertNotNull(s);
        } catch (Throwable t){            
            fail();            
        }
        
        try {
            // Add again the same Shelter home
            Shelter s2 = rescue.registerShelter(shelter1_name, shelter1_address, shelter1_email, shelter1_garden, shelter1_pdd, shelter1_capacity, shelter1_maxDays, shelter1_vet);
            assertNull(s2);
        } catch (Throwable t){            
            if(!(t instanceof RescueException)) {
                fail();
            }
        }        
        
        // Check that the list of homes contains only the one home
        assertNotNull(rescue.getHomes());
        assertEquals((int)rescue.getHomes().size(), 1);
    }
    
    @Test
    public void addPetType() {        
        PetRescue rescue = new PetRescue();
        
        // Check that initially the list of homes is empty
        assertNotNull(rescue.getHomes());
        assertEquals((int)rescue.getHomes().size(), 0);
        
        // Add a new Shelter home
        Shelter s = null;
        try {
            s = rescue.registerShelter(shelter1_name, shelter1_address, shelter1_email, shelter1_garden, shelter1_pdd, shelter1_capacity, shelter1_maxDays, shelter1_vet);
            assertNotNull(s);
        } catch (Throwable t){            
            fail();            
        }
        
        // Check that allowed types is empty
        assertNotNull(s.getAllowed());
        assertEquals((int)s.getAllowed().size(), 0);
        
        try {
            // Add a new type        
            s.addType(PetType.DOG);
        } catch (Throwable t){            
            fail();            
        }   
            
        assertNotNull(s.getAllowed());
        assertEquals((int)s.getAllowed().size(), 1);        
    }
    
    @Test
    public void removePetType() {
        PetRescue rescue = new PetRescue();
        
        // Check that initially the list of homes is empty
        assertNotNull(rescue.getHomes());
        assertEquals((int)rescue.getHomes().size(), 0);
        
        // Add a new Shelter home
        Shelter s = null;
        try {
            s = rescue.registerShelter(shelter1_name, shelter1_address, shelter1_email, shelter1_garden, shelter1_pdd, shelter1_capacity, shelter1_maxDays, shelter1_vet);
            assertNotNull(s);
        } catch (Throwable t){            
            fail();            
        }
        
        // Check that allowed types is empty
        assertNotNull(s.getAllowed());
        assertEquals((int)s.getAllowed().size(), 0);
        
        try {
            // Add a new type        
            s.addType(PetType.DOG);
        } catch (Throwable t){            
            fail();            
        }
        
        assertNotNull(s.getAllowed());
        assertEquals((int)s.getAllowed().size(), 1);
        
        // Remove the pet type
        s.removeType(PetType.DOG);
        
        // Check that allowed types is empty
        assertNotNull(s.getAllowed());
        assertEquals((int)s.getAllowed().size(), 0);
    }
    
    @Test
    public void addMultiplePetTypes() {
        PetRescue rescue = new PetRescue();
        
        // Check that initially the list of homes is empty
        assertNotNull(rescue.getHomes());
        assertEquals((int)rescue.getHomes().size(), 0);
        
        // Add a new Shelter home
        Shelter s = null;
        try {
            s = rescue.registerShelter(shelter1_name, shelter1_address, shelter1_email, shelter1_garden, shelter1_pdd, shelter1_capacity, shelter1_maxDays, shelter1_vet);
            assertNotNull(s);
        } catch (Throwable t){            
            fail();            
        }
        
        // Check that allowed types is empty
        assertNotNull(s.getAllowed());
        assertEquals((int)s.getAllowed().size(), 0);
        
        try {
            // Add a new type        
            s.addType(PetType.DOG);
        } catch (Throwable t){            
            fail();            
        }        
        assertNotNull(s.getAllowed());
        assertEquals((int)s.getAllowed().size(), 1);        
        
        try {
            // Add a new type        
            s.addType(PetType.CAT);
        } catch (Throwable t){            
            fail();            
        } 
        
        assertNotNull(s.getAllowed());
        assertEquals((int)s.getAllowed().size(), 2);        
    }
    
    @Test
    public void addDuplicatedPetTypes() {
        PetRescue rescue = new PetRescue();
        
        // Check that initially the list of homes is empty
        assertNotNull(rescue.getHomes());
        assertEquals((int)rescue.getHomes().size(), 0);
        
        // Add a new Shelter home
        Shelter s = null;
        try {
            s = rescue.registerShelter(shelter1_name, shelter1_address, shelter1_email, shelter1_garden, shelter1_pdd, shelter1_capacity, shelter1_maxDays, shelter1_vet);
            assertNotNull(s);
        } catch (Throwable t){            
            fail();            
        }
        
        // Check that allowed types is empty
        assertNotNull(s.getAllowed());
        assertEquals((int)s.getAllowed().size(), 0);
        
        try {
            // Add a new type        
            s.addType(PetType.DOG);
        } catch (Throwable t){            
            fail();            
        }     
        assertNotNull(s.getAllowed());
        assertEquals((int)s.getAllowed().size(), 1);
        
        try {
            // Add a duplicated type
            s.addType(PetType.DOG);
        } catch (Throwable t){            
            if(!(t instanceof RescueException)) {
                fail();
            }            
        }     
        
        assertNotNull(s.getAllowed());
        assertEquals((int)s.getAllowed().size(), 1);
    }
    
    @Test
    public void removeNotExistingPetType() {
        PetRescue rescue = new PetRescue();
        
        // Check that initially the list of homes is empty
        assertNotNull(rescue.getHomes());
        assertEquals((int)rescue.getHomes().size(), 0);
        
        // Add a new Shelter home
        Shelter s = null;
        try {
            s = rescue.registerShelter(shelter1_name, shelter1_address, shelter1_email, shelter1_garden, shelter1_pdd, shelter1_capacity, shelter1_maxDays, shelter1_vet);
            assertNotNull(s);
        } catch (Throwable t){            
            fail();            
        }
        
        // Check that allowed types is empty
        assertNotNull(s.getAllowed());
        assertEquals((int)s.getAllowed().size(), 0);
        
        try {
            // Add a new type        
            s.addType(PetType.DOG);
        } catch (Throwable t){            
            fail();            
        }     
        assertNotNull(s.getAllowed());
        assertEquals((int)s.getAllowed().size(), 1);
        
        // Remove the pet type
        s.removeType(PetType.CAT);
        
        // Check that allowed types contains one type
        assertNotNull(s.getAllowed());
        assertEquals((int)s.getAllowed().size(), 1);
        
        // Remove the pet type
        s.removeType(PetType.DOG);
        
        // Check that allowed types contains one type
        assertNotNull(s.getAllowed());
        assertEquals((int)s.getAllowed().size(), 0);
    }
}
