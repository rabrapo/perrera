/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.dpoo.rescue;

import java.util.Date;

/**
 *
 * @author Juan Carlos Braza Polanco
 */
public class Stay {
  private Date startedAt;
  private Date finishedAt;
  protected Home home;
  protected Pet pet;

	/**
	* Default empty Stay constructor
	*/
	public Stay() {}

  /**
   * Create an opened stay with a pet in a home
   * @param startedAt Date of the beginning of the stay
   * @param pet Pet to be assigned
   * @param home Home where the pet could stay
   */
  public Stay(Date startedAt, Pet pet, Home home) {
    if(home.accept(pet)) {
      this.startedAt = startedAt;
      this.finishedAt = null;
      this.pet = pet;
      this.home = home;
    }
  }
  
  public boolean isActive(){
    return this.finishedAt == null;
  }

	/**
	* Returns value of startedAt
	* @return
	*/
	public Date getStartedAt() {
		return startedAt;
	}

	/**
	* Sets new value of startedAt
	* @param startedAt
	*/
	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	/**
	* Returns value of finishedAt
	* @return
	*/
	public Date getFinishedAt() {
		return finishedAt;
	}

  /**
	* Sets new value of finishedAt
	* @param finishedAt
	*/
	public void setFinisheddAt(Date finishedAt) {
		this.finishedAt = finishedAt;
    //this.home.removeStay(this);
    //this.pet.removeStay(this);
	}

  /**
   * @return the home
   */
  public Home getHome() {
    return home;
  }

  /**
   * @param home the home to set
   */
  public void setHome(Home home) {
    this.home = home;
  }

  /**
   * @return the pet
   */
  public Pet getPet() {
    return pet;
  }

  /**
   * @param pet the pet to set
   */
  public void setPet(Pet pet) {
    this.pet = pet;
  }
  
}
