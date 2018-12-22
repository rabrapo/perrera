/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.dpoo.rescue;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Carlos Braza Polanco
 */
public abstract class Home {
  protected String name;
  protected String address;
  protected String email;
  protected boolean garden;
  protected boolean acceptPdd;
  protected List<Pet> pets;
  protected List<PetType> allowed;
  protected List<Stay> stays; //asociative class

  /**
	* Default empty Home constructor
	*/
	public Home() {}

	/**
	* Default Home constructor
  * @param name Name of the home
  * @param address Address of the home
  * @param email Email of the home
  * @param garden If the home has a garden
  * @param acceptPdd If the home accepts
	*/
	public Home(String name, String address, String email, boolean garden, boolean acceptPdd) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.garden = garden;
		this.acceptPdd = acceptPdd;
    pets = new ArrayList<>();
    allowed = new ArrayList<>();
    stays = new ArrayList<>();
	}

  /**
   * 
   * @param pet A Pet to be accepted
   * @return If the pet is accepted
   */
  public abstract boolean accept(Pet pet);

  /**
   *
   * @param type Pet's pet type to register
   * @throws RescueException If the type already exists throws an exception
   */
  public void addType(PetType type) throws RescueException {
    for(PetType pT : this.allowed)
      if(pT == type)
        throw new RescueException(RescueException.DUPLICATED_TYPE);

    allowed.add(type);
  }

  /**
   *
   * @param type Remove the PetType specified as parameter
   */
  public void removeType(PetType type) {
    allowed.remove(type);
  }

  /**
   * Buscar el home en las STAYS e ir añadiendo los pets correspondientes con una estancia activa 
   * @return The list of the currents pets with an active stay in this home
   */
  public List<Pet> getCurrentPets() {
    for(Stay st : stays) {
      //check if the pet's stay is active
      if(st.isActive() && st.getPet() != null)
        this.pets.add(st.getPet());
      if(!st.isActive())
        this.pets.remove(st.getPet());
    }
    return pets;
  }

	/**
	* Returns value of name
	* @return
	*/
	public String getName() {
		return name;
	}

	/**
	* Sets new value of name
	* @param name
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* Returns value of address
	* @return
	*/
	public String getAddress() {
		return address;
	}

	/**
	* Sets new value of address
	* @param address
	*/
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	* Returns value of email
	* @return
	*/
	public String getEmail() {
		return email;
	}

	/**
	* Sets new value of email
	* @param email
	*/
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	* Returns value of garden
	* @return
	*/
	public boolean isGarden() {
		return garden;
	}

	/**
	* Sets new value of garden
	* @param garden
	*/
	public void setGarden(boolean garden) {
		this.garden = garden;
	}

	/**
	* Returns value of acceptPdd
	* @return
	*/
	public boolean isAcceptPdd() {
		return acceptPdd;
	}

	/**
	* Sets new value of acceptPdd
	* @param acceptPdd
	*/
	public void setAcceptPdd(boolean acceptPdd) {
		this.acceptPdd = acceptPdd;
	}
  
  /**
   * 
   * @return Lista de pets que han pasado y también los que están actualmente
   */
  public List<Stay> getPets() {
    return stays;
  }

  /**
  * A list of all PetType's allowed at the home
  * @return The list of pets allowed
  */
  public List<PetType> getAllowed() {
    return allowed;
  }

  /**
   * 
   * @return The home's stays list
   */
  public List<Stay> getStays() {
    return stays;
  }

  /*
  *
  * @param pets
  */
  public void setPets(List<Pet> pets) {
    this.pets = pets;
  }

  /*
  *
  * @param allowed
  */
  public void setAllowed(List<PetType> allowed) {
    this.allowed = allowed;
  }

  /*
  *
  * @param stays
  */
  public void setStays(List<Stay> stays) {
    this.stays = stays;
  }
  
  /**
   * Add a new opened STAY 
   * @param s Stay to add
   */
  public void addStay(Stay s) {
    this.stays.add(s);
/*    if(this.accept(pet))
      this.stays.add(new Stay(new Date(), pet, this));*/
  }
  
  public boolean equals(Home h) {
    return this.email.equals(h.getEmail());
  }
  
}
