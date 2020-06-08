/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameClasses;

import java.awt.Color;

/**
 *
 * @author DENISA
 */
public class Potion extends Item{
    int health=120;
    public Potion(){
        
    }

    @Override
    public void use() {
        Player.getPlayer().modifyHealth(health);
    }
       
     public Color getColor(){
            return Color.red;
    }
     
      @Override
    public ItemType getType() {
        return ItemType.Potion;
    }
}
