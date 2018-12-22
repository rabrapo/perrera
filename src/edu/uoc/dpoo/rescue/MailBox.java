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
public class MailBox {
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
  
  }

  public List<Mail> getSentMails(String email) {
    return null;
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
}
