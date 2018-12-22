/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.dpoo.rescue;

/**
 *
 * @author Juan Carlos Braza Polanco
 */
public class Shelter extends Home {
  private int capacity;
  private int maxDays;
  private boolean vet;

	/**
	* Default empty Shelter constructor
	*/
	public Shelter() {
		super();
	}

	/**
	* Default Shelter constructor
  * @param name
  * @param address
  * @param email
  * @param garden
  * @param acceptPdd
  * @param capacity
  * @param maxDays
  * @param vet
	*/
	public Shelter(String name, String address, String email, boolean garden, boolean acceptPdd, int capacity, int maxDays, boolean vet) {
		super(name, address, email, garden, acceptPdd);
		this.capacity = capacity;
		this.maxDays = maxDays;
		this.vet = vet;
	}

	/**
	* Check if a pet can or can not be accepted at the shelter, check if there is capacity, check if the PetType is allowed and check if the pet is pdd and the shelter allowes it 
	* @return Pet is or not accepted at this shelter
	*/
  @Override
  public boolean accept(Pet pet) {
    boolean res = false;
    
    //check if there is capacity
    if(super.getCurrentPets().size() < this.capacity) {
      //Check if the PetType is allowed
      for(PetType pT : super.getAllowed())
        if(pT.equals(pet.getType()))
          //Check if the pet is pdd and the shelter allowes it 
          if(pet.isPdd() && super.isAcceptPdd() || !pet.isPdd())
            res = true;
    }  
   
    return res;
  }
  
	/**
	* Returns value of capacity
	* @return
	*/
	public int getCapacity() {
		return capacity;
	}

	/**
	* Sets new value of capacity
	* @param capacity
	*/
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	* Returns value of maxDays
	* @return
	*/
	public int getMaxDays() {
		return maxDays;
	}

	/**
	* Sets new value of maxDays
	* @param maxDays
	*/
	public void setMaxDays(int maxDays) {
		this.maxDays = maxDays;
	}

	/**
	* Returns value of vet
	* @return
	*/
	public boolean isVet() {
		return vet;
	}

	/**
	* Sets new value of vet
	* @param vet
	*/
	public void setVet(boolean vet) {
		this.vet = vet;
	}
}
