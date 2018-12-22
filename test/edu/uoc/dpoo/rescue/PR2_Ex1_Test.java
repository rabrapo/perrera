
package edu.uoc.dpoo.rescue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PR2_Ex1_Test {
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
  private final int shelter1_capacity = 3;
  private final int shelter1_maxDays = 7;
  private final boolean shelter1_vet = true;


  public PR2_Ex1_Test() {
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
  public void addDuplicatedVolunteer() {
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
      fail();
    } catch (Throwable t){
      if(!(t instanceof RescueException)) {
          fail();
      }
    }

    // Check that the list of volunteers have two elements
    assertNotNull(rescue.getRegisteredVolunteers());
    assertEquals(2, (int)rescue.getRegisteredVolunteers().size());
  }

  @Test
  public void addDuplicatedHome() {
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
      fail();
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
  public void addDuplicatedPetType() {
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
      fail();
    } catch (Throwable t){            
      if(!(t instanceof RescueException)) {
        fail();
      }            
    }     

    assertNotNull(s.getAllowed());
    assertEquals((int)s.getAllowed().size(), 1);
  }  
}
