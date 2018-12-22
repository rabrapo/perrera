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
public class Volunteer {
  /*
  Attributes
  */
  private String email;
  private boolean acceptPdd;
  private String name;

  /**
	* Default empty Volunteer constructor
	*/
	public Volunteer() {}

	/**
	* Default Volunteer constructor
  * @param name
  * @param email
  * @param acceptPdd
	*/
	public Volunteer(String name, String email, boolean acceptPdd) {
		this.email = email;
		this.acceptPdd = acceptPdd;
		this.name = name;
	}

  /**
   *
   * @return
   */
  public String getEmail() {
    return email;
  }

  /**
   *
   * @param email
   */
  public void setEmail(String email) {
	  this.email = email;
  }

  /**
   *
   * @return
   */
  public boolean getAcceptPdd() {
	  return acceptPdd;
  }

  /**
   *
   * @param acceptPdd
   */
  public void setAcceptPdd(boolean acceptPdd) {
	  this.acceptPdd = acceptPdd;
  }

  /**
   *
   * @return
   */
  public String getName() {
	  return name;
  }

  /**
   *
   * @param name
   */
  public void setName(String name) {
	  this.name = name;
  }  
}
