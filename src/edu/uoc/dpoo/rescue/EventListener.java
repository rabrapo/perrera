/*
* DPOO
* Semester 20181
* Pet Rescue
*/
package edu.uoc.dpoo.rescue;

/**
 *
 * @author JuanCarlos Braza Polanco
 */
public interface EventListener {
  public void onNewHome(Pet pet, Home home);
  public void onNewRescue(Pet pet, Volunteer volunteer);    
  public void onNewAlert(Alert alert);  
}
