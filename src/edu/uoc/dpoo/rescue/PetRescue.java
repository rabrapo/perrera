
package edu.uoc.dpoo.rescue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Juan Carlos Braza Polanco
 */
public class PetRescue {
  
  /*
  * Attributes
  */
  private List<Volunteer> volunteers;
  private List<Alert> alerts;
  private List<Home> homes;
  private List<EventListener> listeners;

  /**
   * Default constructor
   */
  public PetRescue() {
    volunteers = new ArrayList<>();
    alerts = new ArrayList<>();
    homes = new ArrayList<>();
  }

	/**
	* Default PetRescue constructor
  * @param volunteers
  * @param alerts
  * @param homes
	*/
	public PetRescue(List<Volunteer> volunteers, List<Alert> alerts, List<Home> homes) {
		this.volunteers = volunteers;
		this.alerts = alerts;
		this.homes = homes;
	}

  /**
   * Find a specified pet in the system
   * @param id The id of the pet
   * @return The pet with the id specified
   */
  public Pet findPet(String id) {
    // search the pet in the alerts comparing its id
    for(Alert al : alerts)
      if(al.getPet().getId().equals(id))
        return al.getPet();
    
    return null;
  }

  /**
   *
   * @param type
   * @param id
   * @param name
   * @param pdd
   * @return The new pet rescued
   */
  public Pet newRescue(PetType type, String id, String name, boolean pdd) {
    Pet p = new Pet(type, id, name, pdd); // New pet
    Alert a = new Alert(p, AlertType.NEW_RESCUE); // New alert
    alerts.add(a); // store the new created alert
    
    return p;
  }
  
  /**
   *
   * @param name The name of the owner of the shelter
   * @param address The address of the new shelter
   * @param email The email of the shelter
   * @param garden True if the shelter has a garden
   * @param acceptPdd True if the shelter accepts dangerous dogs
   * @param capacity Maximun capacity of the shelter
   * @param maxDays Maximun days a pet can stay in the shelter
   * @param vet True if the shelter has a veterinary
   * @return The new shelter added to the system.
   * @throws RescueException If the new shelter exists throws the exception
   */
  public Shelter registerShelter(String name, String address, String email,  
          boolean garden, boolean acceptPdd, int capacity, int maxDays, 
          boolean vet) throws RescueException {
    for(Home h : homes)
      if(h.getEmail().equals(email))
        throw new RescueException(RescueException.DUPLICATED_HOME);

    Shelter s = new Shelter(name, address, email, garden, acceptPdd, capacity, maxDays, vet);
    homes.add(s);
    
    return s;
  }

  /**
   *
   * @param name
   * @param address
   * @param email
   * @param garden
   * @param acceptPdd
   * @param age
   * @return
   * @throws RescueException
   */
  public Adoption registerAdoption(String name, String address, String email, 
          boolean garden, boolean acceptPdd, int age) throws RescueException {
    for(Home h: homes)
      if(h.getEmail().equals(email))
        throw new RescueException(RescueException.DUPLICATED_HOME);

    Adoption a = new Adoption(name, address, email, garden, acceptPdd, age);
    homes.add(a);
    
    return a;
  }

  /**
   * Add a new volunteer. If there is already a volunteer with the same email, 
   * the new one won't be added and it must return null. Otherwise, the new one is added.
   * @param name
   * @param email
   * @param acceptPdd
   * @return
   * @throws RescueException
   */

  public Volunteer registerVolunteer(String name, String email, boolean acceptPdd) throws RescueException {
    for(Volunteer v : volunteers)
      if(v.getEmail().equals(email))
        throw new RescueException(RescueException.DUPLICATED_VOLUNTEER);

    Volunteer v = new Volunteer(name, email, acceptPdd);
    volunteers.add(v);

    return v;
  }

  /**
   *
   * @return
   */
  public List<Volunteer> getRegisteredVolunteers() {
    return volunteers;
  }

  /**
   *
   * @return A list of registered homes in the application
   */
  public List<Home> getRegisteredHomes() {
    return homes;
  }

  /**
   * Search possibles homes for a specified pet
   * @param id of the pet
   * @return The possibles shelters where the pet could stay
   */
  public List<Home> findHome(String id) {
    //List of possibles homes
    List<Home> ls = new ArrayList<>();
    
    //search the pet
    Pet pet = this.findPet(id);
    
    if(pet != null)
      //search homes
      for(Home h : this.homes)
        if(h.accept(pet))
          ls.add(h);
    
    return ls; 
  }

  /**
   * Assign the pet to a home and creates and opened stay
   * @param pet Pet to be adopted
   * @param home Home where the pet stays
   */
  public void assignHome(Pet pet, Home home) {
    Stay s = new Stay(new Date(), pet, home);
    home.addStay(s);
    pet.addStay(s);
  }

  /**
   *
   * @return All the opened and unassigned alerts
   */
  public List<Alert> getUnassignedAlerts() {
    List<Alert> temp = new ArrayList<>();
    
    alerts.stream().filter((al) -> (al.getClosedAt() == null && al.getAssigned() == null)).forEachOrdered((al) -> {
      temp.add(al);
    });
    
    return temp;
  }

  /**
   *
   * @param id
   * @return All the opened alerts of a specified pet
   */
  public List<Alert> getPetAlerts(String id) {
    List<Alert> temp = new ArrayList<>();
    
    alerts.stream().filter((al) -> (al.getPet().getId().equals(id))).filter((al) -> (al.getClosedAt() == null)).forEachOrdered((al) -> {
      temp.add(al);
    });
    
    return temp;
  }

  /**
   *
   * @param email Email of the volunteer
   * @return The opened assigned alerts of the volunteer
   */
  public List<Alert> getVolunteerAlerts(String email) {
    List<Alert> all = new ArrayList<>();
    
    if(!this.alerts.isEmpty())
      for(Alert al : alerts) {
        Volunteer v = al.getAssigned();
        //LINEA CLAVE
        if(v != null)                                    
          if((v.getEmail() != null) && (!v.getEmail().equals("")))
            if(v.getEmail().equals(email) && al.getClosedAt() == null)
              all.add(al); 
      }

    return all;
  }

  /**
   * Current home of the pet if its stay is opened
   * @param id of the pet
   * @return The home where the pet is, if its stay is opened
   */
  public Home getCurrentHome(String id) {
    return findPet(id).getCurrentHome();
    /*
    Pet p = this.findPet(id);

    if(p != null)
      return p.getCurrentHome();
    else
      return null;
    */
  }

  /**
   *
   * @param id Pet identification
   * @return All availables volunteers that can take the pet to a new home
   */
  public List<Volunteer> findVolunteer(String id) {
    List<Volunteer> vls = new ArrayList<>();
    boolean dangerous = false;
    
    //Check if the pet is or is not a pdd
    for (Alert al : alerts) {
      if(al.getPet().getId().equals(id)) {
        dangerous = al.getPet().isPdd();
      }
    }
    
    //Check if the volunteer accepts pdd and has not opened alerts
    if(dangerous) {
      volunteers.stream().filter((v) -> (v.getAcceptPdd() && getVolunteerAlerts(v.getEmail()).isEmpty())).forEachOrdered((v) -> {
        vls.add(v);
      });    
    } else {
      volunteers.stream().filter((v) -> (getVolunteerAlerts(v.getEmail()).isEmpty())).forEachOrdered((v) -> {
        vls.add(v);
      });
    }
    
    return vls;
  }

  /**
   * Assign the specified alert to the specified volunteer
   * @param alert
   * @param volunteer
   */
  public void assignAlert(Alert alert, Volunteer volunteer) {
    alert.assign(volunteer);
  }

  /**
   * Close the alert
   * @param alert
   */
  public void closeAlert(Alert alert) {
    alert.setClosedAt(new Date());
  }

	/**
	 * Returns value of volunteers
	 * @return
	 */
	public List<Volunteer> getVolunteers() {
		return volunteers;
	}

	/**
	* Sets new value of volunteers
	* @param volunteers
	*/
	public void setVolunteers(List<Volunteer> volunteers) {
		this.volunteers = volunteers;
	}

	/**
	* Returns value of alerts
	* @return
	*/
	public List<Alert> getAlerts() {
		return alerts;
	}

	/**
	* Sets new value of alerts
	* @param alerts
	*/
	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}

	/**
	* Returns List of homes
	* @return
	*/
	public List<Home> getHomes() {
		return homes;
	}

	/**
	* Sets new value of homes
	* @param homes
	*/
	public void setHomes(List<Home> homes) {
		this.homes = homes;
	}
  
  /**
   *
   * @param listener
   */
  public void addListener(EventListener listener) {
    this.listeners.add(listener);
  }
}
