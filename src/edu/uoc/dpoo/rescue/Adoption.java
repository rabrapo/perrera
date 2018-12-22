/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.dpoo.rescue;

/**
 *
 * @author JuanCarlos Braza Polanco
 */
public class Adoption extends Home {
  private int age;

  public Adoption(String name, String address, String email, Boolean garden, Boolean acceptPdd, int age) {
    super(name, address, email, garden, acceptPdd);
    this.age = age;
  }   

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }   

  @Override
  public boolean accept(Pet pet) {
    boolean res = false;
    
    //check if the pet type is allowed
    for(PetType pT : super.getAllowed()) {
      if(pet.getType().equals(pT))
        res = true;
    }
    if(res)
      //check if the pet's age is correct and if a pdd pet is allowed
      res = (pet.getAge() <= this.age && pet.isPdd() == super.isAcceptPdd());
    
    return res;
  }
}
