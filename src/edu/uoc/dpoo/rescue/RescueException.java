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
public class RescueException extends Exception {
  // Predefined errors
  // Without parameters    
  public static final String DUPLICATED_VOLUNTEER = "There is a volunteer with the same email";
  public static final String DUPLICATED_HOME = "There is another home with the same email"; 
  public static final String DUPLICATED_TYPE = "This house already have this type"; 


  /* 

  - How to throw with a custom message:    
  throw new RescueException("this is a custom message");

  - How to throw with a pre-defined message:    
  -- Without parameters
  throw new RescueException(RescueException.DUPLICATED_VOLUNTEER);    

  */
  public RescueException (String message){
    super(message);
  }  
}
