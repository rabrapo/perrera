
package edu.uoc.dpoo.rescue;

import java.util.Date;

/**
 *
 * @author JuanCarlos Braza Polanco
 */
public class Alert {

  /*
  Attributes
  */
  private Date createdAt;
  private Date assignedAt;
  private Date closedAt;
  private Volunteer assigned;
  private Pet pet;
  private AlertType type;

  /**
   *
   * @param pet
   * @param type
   */
  public Alert(Pet pet, AlertType type) {
    this.pet = pet;
    this.type = type;
    this.createdAt = new Date();
    this.assigned = null;        
    this.assignedAt = null;
    this.closedAt = null;
  }

  /**
   *
   * @return
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /**
   *
   * @param createdAt
   */
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  /**
   *
   * @return
   */
  public Date getAssignedAt() {
    return assignedAt;
  }

  /**
   *
   * @param assignedAt
   */
  public void setAssignedAt(Date assignedAt) {
    this.assignedAt = assignedAt;
  }

  /**
   *
   * @return
   */
  public Date getClosedAt() {
    return closedAt;
  }

  /**
   *
   * @param closedAt
   */
  public void setClosedAt(Date closedAt) {
    this.closedAt = closedAt;
  }

  /**
   *
   * @return
   */
  public Volunteer getAssigned() {
    return assigned;
  }
  
  public void setAssigned(Volunteer v) {
    this.assigned = v;
  }

  /**
   * Assign the alert to the specified volunteer
   * @param volunteer
   */
  public void assign(Volunteer volunteer) {
    setAssigned(volunteer);
  }
  
  /**
   *
   * @return
   */
  public Pet getPet() {
    return pet;
  }

  /**
   *
   * @param pet
   */
  public void setPet(Pet pet) {
    this.pet = pet;
  }

  /**
   *
   * @return
   */
  public AlertType getType() {
    return type;
  }

  /**
   *
   * @param type
   */
  public void setType(AlertType type) {
    this.type = type;
  }
  
}
