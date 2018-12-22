
package edu.uoc.dpoo.rescue;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Carlos Braza Polanco
 */
public class Pet {
  private boolean pdd;
  private String id;
  private String name;
  private Date rescueAt;
  private int age;
  private PetType type;
  private List<Home> homes;
  private List<Stay> stays; //clase asociativa

  /**
	* Default empty Pet constructor
	*/
	public Pet() {}

	/**
	* Default Pet constructor
  * @param pdd
  * @param id
  * @param name
  * @param rescueAt
  * @param age
  * @param type
	*/
	public Pet(boolean pdd, String id, String name, Date rescueAt, int age, PetType type) {
		this.pdd = pdd;
		this.id = id;
		this.name = name;
		this.rescueAt = rescueAt;
		this.age = age;
    this.type = type;
    homes = new ArrayList<>();
    stays = new ArrayList<>();
	}
  
  /**
   * Constructor with parcial values
   * @param type
   * @param id
   * @param name
   * @param pdd
   */
  public Pet(PetType type, String id, String name, boolean pdd) {
    this.type = type;
    this.id = id;
    this.name = name;
    this.pdd = pdd;
    this.rescueAt = new Date();
    homes = new ArrayList<>();
    stays = new ArrayList<>();    
  }

  /**
   * Current home of the pet (its stay must be opened)
   * @return Current home of the pet if its stay is opened
   */
  public Home getCurrentHome() {
    for(Stay s : this.stays)
      if(s.getPet().getId().equals(this.id) && s.isActive() && s.getHome() != null) {
        //System.out.println("Pet.getCurrentHome(): SI hay casa");
        return s.getHome();
      }
      //else
      //System.out.println("Pet.getCurrentHome(): NO hay casa");
          
    return null;
  }

	/**
	* Returns value of pdd
	* @return
	*/
	public boolean isPdd() {
		return pdd;
	}

	/**
	* Sets new value of pdd
	* @param pdd
	*/
	public void setPdd(boolean pdd) {
		this.pdd = pdd;
	}

	/**
	* Returns value of id
	* @return
	*/
	public String getId() {
		return id;
	}

	/**
	* Sets new value of id
	* @param id
	*/
	public void setId(String id) {
		this.id = id;
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
	* Returns value of rescueAt
	* @return
	*/
	public Date getRescueAt() {
		return rescueAt;
	}

	/**
	* Sets new value of rescueAt
	* @param rescueAt
	*/
	public void setRescueAt(Date rescueAt) {
		this.rescueAt = rescueAt;
	}

	/**
	* Returns value of age
	* @return
	*/
	public int getAge() {
		return age;
	}

	/**
	* Sets new value of age
	* @param age
	*/
	public void setAge(int age) {
		this.age = age;
	}

	/**
	* Returns value of type
	* @return
	*/
	public PetType getType() {
		return type;
	}

	/**
	* Sets new value of type
	* @param type
	*/
	public void setType(PetType type) {
		this.type = type;
	}

	/**
	* Returns value of homes
	* @return
	*/
	public List<Stay> getHomes() {
		return stays;
	}

	/**
	* Sets new value of homes
	* @param homes
	*/
	public void setHomes(List<Home> homes) {
		this.homes = homes;
	}

  /*
	* Returns value of stays
	* @return
	*/
  public List<Stay> getStays() {
    return stays;
  }

	/**
	* Sets new value of stays
	* @param stays
	*/
  public void setStays(List<Stay> stays) {
    this.stays = stays;
  }
  
  public void addStay(Stay s) {
    this.stays.add(s);
  }
  
}
