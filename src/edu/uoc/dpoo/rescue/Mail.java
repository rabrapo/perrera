
package edu.uoc.dpoo.rescue;

/**
 *
 * @author Juan Carlos Braza Polanco
 */
public class Mail {
  private String email;
  private String subject;
  private String body;

	/**
	* Default empty Mail constructor
	*/
	public Mail() {}

	/**
	* Default Mail constructor
  * @param email
  * @param subject
  * @param body
	*/
	public Mail(String email, String subject, String body) {
		this.email = email;
		this.subject = subject;
		this.body = body;
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
	* Returns value of subject
	* @return
	*/
	public String getSubject() {
		return subject;
	}

	/**
	* Sets new value of subject
	* @param subject
	*/
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	* Returns value of body
	* @return
	*/
	public String getBody() {
		return body;
	}

	/**
	* Sets new value of body
	* @param body
	*/
	public void setBody(String body) {
		this.body = body;
	}
}
