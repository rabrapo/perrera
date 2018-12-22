
package edu.uoc.dpoo.rescue;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Carlos Braza Polanco
 */
public class MailBox implements EventListener {
  private List<Mail> mails;

  /**
	* Default empty MailBox constructor
	*/
	public MailBox() {
    mails = new ArrayList<>();  
  }

	/**
	* Default MailBox constructor
  * @param mails
	*/
	public MailBox(List<Mail> mails) {
		this.mails = mails;
	}

  public void sendMail(String email, String subject, String body) {
    this.mails.add(new Mail(email, subject, body));
  }

  /**
   *
   * @param email
   * @return
   */
  public List<Mail> getSentMails(String email) {
    List<Mail> lm = new ArrayList<>();
    
    for(Mail m : mails)
      if(m.getEmail().equals(email))
        lm.add(m);
    
    return lm;
  }

	/**
	* Returns value of mails
	* @return
	*/
	public List<Mail> getMails() {
		return mails;
	}

	/**
	* Sets new value of mails
	* @param mails
	*/
	public void setMails(List<Mail> mails) {
		this.mails = mails;
	}

  /**
   * The home receives an email with the data of the new pet
   * @param pet Pet to be added to the home
   * @param home Home where the pet could stay
   */
  @Override
  public void onNewHome(Pet pet, Home home) {
    this.sendMail(home.getEmail(), "New Stay", pet.getId());
  }

  /**
   * 
   * @param pet
   * @param volunteer
   */
  @Override
  public void onNewRescue(Pet pet, Volunteer volunteer) {
    
  }

  /**
   * The volunteer receives an email with data of the new alert
   * @param alert The new alert with the data of the pet
   */
  @Override
  public void onNewAlert(Alert alert) {
    this.sendMail(alert.getAssigned().getEmail(), "New Alert", alert.getPet().getId());
  }
}
