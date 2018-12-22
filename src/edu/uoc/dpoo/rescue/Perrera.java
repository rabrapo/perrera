/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.dpoo.rescue;

/**
 *
 * @author rafa
 */
public class Perrera {

  public static void main(String args[]) {
    String pet1_id = "100-200-300-400-500";
    String pet1_name = "Barney";
    PetType pet1_type = PetType.DOG;
    Boolean pet1_pdd = false;
    
    String pet2_id = "101-201-301-401-501";
    String pet2_name = "Lena";
    PetType pet2_type = PetType.DOG;
    Boolean pet2_pdd = true;
    
    String pet3_id = "102-202-302-402-502";
    String pet3_name = "Garfield";
    PetType pet3_type = PetType.CAT;
    Boolean pet3_pdd = false;
    
    String volunteer1_name = "Robert";
    String volunteer1_email = "robert@rescue.dpoo.uoc.edu";
    boolean volunteer1_allowPdd = false;
    
    String volunteer2_name = "Julia";
    String volunteer2_email = "julia@rescue.dpoo.uoc.edu";
    boolean volunteer2_allowPdd = true;
    
    String shelter1_name = "Susana";
    String shelter1_address = "Susana's home";
    String shelter1_email = "susana@dpoo.uoc.edu";
    boolean shelter1_garden = true;
    boolean shelter1_pdd = true;
    int shelter1_capacity = 1;
    int shelter1_maxDays = 7;
    boolean shelter1_vet = true;
    
    String shelter2_name = "John";
    String shelter2_address = "John's home";
    String shelter2_email = "john@dpoo.uoc.edu";
    boolean shelter2_garden = false;
    boolean shelter2_pdd = false;
    int shelter2_capacity = 5;
    int shelter2_maxDays = 4;
    boolean shelter2_vet = false;
    
    PetRescue rescue = new PetRescue();
    
    // Check that initially the list of alerts is empty
    /*
    System.out.println("No rescates");
    System.out.println("Nº de alertas: " + rescue.getAlerts().size());
    */
    // Add news rescues
    rescue.newRescue(pet1_type, pet1_id, pet1_name, pet1_pdd);
    rescue.newRescue(pet2_type, pet2_id, pet2_name, pet2_pdd);
    rescue.newRescue(pet3_type, pet3_id, pet3_name, pet3_pdd);    
    // Check the size of the list of alerts
    /*
    System.out.println("Hay 3 rescates");
    System.out.println("Nº de alertas: " + rescue.getAlerts().size());
    // Check that initially the list of volunteers is empty
    System.out.println("Check that initially the list of volunteers is empty");
    System.out.println("Nº voluntarios: " + rescue.getRegisteredVolunteers().size());
    */
    try {
      rescue.registerVolunteer(volunteer1_name, volunteer1_email, volunteer1_allowPdd);
      rescue.registerVolunteer(volunteer2_name, volunteer2_email, volunteer2_allowPdd);
      //System.out.println("Voluntarios added: " + rescue.getRegisteredVolunteers().size());
    } catch(RescueException e) {
      System.out.println("Error: " + e.getMessage());
    }
    // Check that initially the list of homes is empty
    //System.out.println("Check that initially the list of homes is empty: " + rescue.getHomes().size());
    // Add a new Shelter home        
    try {            
      Shelter s = rescue.registerShelter(shelter1_name, shelter1_address, shelter1_email, shelter1_garden, shelter1_pdd, shelter1_capacity, shelter1_maxDays, shelter1_vet);
      // Add news pettypes  
      s.addType(PetType.DOG);
      s.addType(PetType.CAT);
      //System.out.println("PetTypes allowed: " + s.getAllowed().size());
    } catch (RescueException e){            
        System.out.println("Error: " + e.getMessage());           
    }
    // Add a new Shelter home        
    try {            
      Shelter s = rescue.registerShelter(shelter2_name, shelter2_address, shelter2_email, shelter2_garden, shelter2_pdd, shelter2_capacity, shelter2_maxDays, shelter2_vet);
      // Add news pettypes  
      s.addType(PetType.DOG);
      s.addType(PetType.CAT);
      //System.out.println("PetTypes allowed: " + s.getAllowed().size());
    } catch (RescueException e){            
        System.out.println("Error: " + e.getMessage());           
    }
    System.out.println("*** ASSIGN HOME ***");
    Pet pet_no_pdd = rescue.findPet(pet1_id);
    Pet pet_pdd = rescue.findPet(pet2_id);
    Pet pet_cat = rescue.findPet(pet3_id);
    if(pet_no_pdd == null)
      System.out.println("MAL: 1º nulo");
    if(pet_pdd == null)
      System.out.println("MAL: 2º nulo");
    if(pet_cat == null)
      System.out.println("MAL: 3º nulo");
    Home home_all = rescue.getRegisteredHomes().get(0);
    if(home_all == null)
      System.out.println("MAL: 4º nulo");
    Home home_cat = rescue.getRegisteredHomes().get(1);
    if(home_cat == null)
      System.out.println("MAL: 5º nulo");
    rescue.assignHome(pet_cat, home_all);
    if(rescue.findHome(pet1_id) == null)
      System.out.println("MAL: 6º nulo");
    if(rescue.findHome(pet2_id) == null)
      System.out.println("MAL: 7º nulo");
    if(pet_cat.getCurrentHome().equals(home_all))
      System.out.println("BIEN");
    if(pet_pdd.getCurrentHome() != null)
      System.out.println("MAL: 8 null");
    if(pet_no_pdd.getCurrentHome() != null)
      System.out.println("MAL: 9 null");
    System.out.println("* FINAL *");
    System.out.println("home_all.getCurrentPets().size(): " + home_all.getCurrentPets().size());
    System.out.println("home_cat.getCurrentPets().size(): " + home_cat.getCurrentPets().size());
/*    
    System.out.println("*** HOME PETS ***");
    Pet pet_no_pdd = rescue.findPet(pet1_id);
    if(pet_no_pdd == null)
      System.out.println("MAL: pet_no_pdd es null");
    Home home = rescue.getRegisteredHomes().get(0);
    if(home == null)
      System.out.println("MAL: home es null");
    Stay s = new Stay(new Date(), pet_no_pdd, home);  
    if(!s.isActive())
      System.out.println("MAL: stay es NO activa");
    if(home != null)
      if(!home.getCurrentPets().isEmpty())
        System.out.println("MAL: home.getCurrentPets().size() != 0");
    if(home.getPets() != null) {
      home.getPets().add(s);
      if(home.getCurrentPets().size() != 1)
        System.out.println("MAL: home.getCurrentPets().size() != 1");
      s.setFinisheddAt(new Date());
      if(s.isActive())
        System.out.println("MAL: stay es activa");
      if(!home.getCurrentPets().isEmpty())
        System.out.println("MAL: home.getCurrentPets().size() != 0");
    }
*/
  }
}
