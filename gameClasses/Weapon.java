/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameClasses;

/**
 *
 * @author DENISA
 */
public class Weapon {
   private final int DAMAGE;
   
   public Weapon(int pDamage){
       DAMAGE=pDamage;
   }
   
   public int getDamage(){
       return DAMAGE;
   }
}
