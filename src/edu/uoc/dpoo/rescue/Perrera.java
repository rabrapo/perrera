/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.dpoo.rescue;

import java.util.List;

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
    MailBox mailbox = new MailBox();
    
    rescue.addListener(mailbox);
    
    System.out.println("*** Events ***");
    // Check that initially the list of volunteers is empty
    if(rescue.getRegisteredVolunteers() == null)
      System.out.println("MAL: getRegisteredVolunteers() == null");
    if(!rescue.getRegisteredVolunteers().isEmpty())
      System.out.println("MAL: getRegisteredVolunteers() no vacío");
    try {
      // Add news volunteers
      rescue.registerVolunteer(volunteer1_name, volunteer1_email, volunteer1_allowPdd);            
      rescue.registerVolunteer(volunteer2_name, volunteer2_email, volunteer2_allowPdd);            
    } catch (RescueException e){            
      System.out.println("Excepción: " + e.getMessage());
    }        
    // Check that the list of volunteers has two elements
    if(rescue.getRegisteredVolunteers() == null)
      System.out.println("MAL: getRegisteredVolunteers() == null");
    if(rescue.getRegisteredVolunteers().size() != 2)
      System.out.println("MAL: getRegisteredVolunteers().size() tiene que ser 2");
    // Check that initially the list of homes is empty
    if(rescue.getHomes() == null)
      System.out.println("MAL: getHomes() == null");
    if(!rescue.getHomes().isEmpty())
      System.out.println("MAL: getHomes() no está vacío");
    // Add a new Shelter home  
    try {            
      Shelter s = rescue.registerShelter(shelter1_name, shelter1_address, shelter1_email, shelter1_garden, shelter1_pdd, shelter1_capacity, shelter1_maxDays, shelter1_vet);
      if(s == null)
        System.out.println("MAL: shelter1 null");
      else {
        // Add a new types  
        s.addType(PetType.DOG);
        s.addType(PetType.CAT);
        if(s.getAllowed().size() != 2)
          System.out.println("MAL: getAllowed() no es 2");
      }
    } catch (RescueException e){            
      System.out.println("Excepción: " + e.getMessage());           
    }
    // Add a new Shelter home        
    try {            
      Shelter s = rescue.registerShelter(shelter2_name, shelter2_address, shelter2_email, shelter2_garden, shelter2_pdd, shelter2_capacity, shelter2_maxDays, shelter2_vet);
      if(s == null)
        System.out.println("MAL: shelter2 null");
      else {
        // Add a new type  
        s.addType(PetType.CAT);
        if(s.getAllowed().size() != 1)
          System.out.println("MAL: getAllowed() no es 1");        
      }
    } catch (RescueException e){            
      System.out.println("Excepción: " + e.getMessage());           
    }
    // Check list of homes
    if(rescue.getHomes() == null)
      System.out.println("MAL: rescue.getHomes() es null");
    if(rescue.getHomes().size() != 2)
      System.out.println("MAL: rescue.getHomes().size() no es 2");
    // Check that initially the list of alerts is empty
    if(rescue.getAlerts() == null)
      System.out.println("MAL: getAlerts() == null");
    if(!rescue.getAlerts().isEmpty())
      System.out.println("MAL: getAlerts().isEmpty()");
    // Check that initially the list of mails is empty
    if(mailbox.getMails() == null)
      System.out.println("MAL: mailbox.getMails() es nulo");
    if(!mailbox.getMails().isEmpty())
      System.out.println("");
    // Add a new rescue
    rescue.newRescue(pet1_type, pet1_id, pet1_name, pet1_pdd);
    // Check that the list of alerts have one element
    if(rescue.getAlerts() == null)
      System.out.println("MAL: rescue.getAlerts() es null");
    if(rescue.getAlerts().size() != 1)
      System.out.println("MAL: rescue.getAlerts().size() no es 1");
    // Search for the new pet
    Pet pet = rescue.findPet(pet1_id);
    if(pet == null)
      System.out.println("MAL: pet es null");
    // Search for a volunteer
    List<Volunteer> availableVolunteers = rescue.findVolunteer(pet1_id);
    if(availableVolunteers == null)
      System.out.println("MAL: availableVolunteers es null");
    else {
      if(availableVolunteers.isEmpty())
        System.out.println("MAL: availableVolunteers.size() está vacío.");  
      else {
        Volunteer volunteer = availableVolunteers.get(0);
        // Get the alert
        Alert rescueAlert = rescue.getUnassignedAlerts().get(0);
        if(rescueAlert == null)
          System.out.println("MAL: rescueAlert");
        else {
          // Check that the list of mails is empty
          if((mailbox.getMails() == null))
            System.out.println("MAL: (mailbox.getMails() nulo");
          else {
            if(!mailbox.getMails().isEmpty())
              System.out.println("MAL: mailbox.getMails() no está vacío");
            else {
              rescue.assignAlert(rescueAlert, volunteer);
              // Check that the list of mails is not empty
              if((mailbox.getMails() == null))
                System.out.println("MAL: mailbox.getMails() nulo");
              else {
                if(mailbox.getMails().size() != 1)
                  System.out.println("MAL: mailbox.getMails().size() debe ser 1");
                else {
                  // Check volunteer mails
                  if(mailbox.getSentMails(volunteer.getEmail()) == null)
                    System.out.println("MAL: mailbox.getSentMails(volunteer.getEmail()) es null");
                  else {
                    if(mailbox.getSentMails(volunteer.getEmail()).size() != 1)
                      System.out.println("MAL: mailbox.getSentMails(volunteer.getEmail()).size() debe ser 1");
                    else {
                      //Search for a home
                      List<Home> availableHomes = rescue.findHome(pet.getId());
                      if(availableHomes == null)
                        System.out.println("MAL: availableHomes es null");
                      else {
                        if(availableHomes.isEmpty())
                          System.out.println("MAL: availableHomes.isEmpty()");
                        else {
                          Home home = availableHomes.get(0);
                          // Assign the home
                          rescue.assignHome(pet, home);
                          // Check that the list of mails is not empty
                          if(mailbox.getMails() == null)
                            System.out.println("MAL: mailbox.getMails() null");
                          else {
                            if(mailbox.getMails().size() != 2)
                              System.out.println("MAL: mailbox.getMails().size()");
                            else {
                              // Check home mails
                              if(mailbox.getSentMails(home.getEmail()) == null)
                                System.out.println("MAL: mailbox.getSentMails(home.getEmail()) null");
                              else {
                                if(mailbox.getSentMails(home.getEmail()).size() == 1)
                                  System.out.println("BIEN");
                                else
                                  System.out.println(mailbox.getSentMails(home.getEmail()).size());
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    
  }
}
