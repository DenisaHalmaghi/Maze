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
public class Key extends Item{
    
    public Key(){
        
    }
    
    @Override
    public void use() {
        System.out.println("keyUsed");
    }
    public Color getColor(){
        return Color.CYAN;
    }

    @Override
    public ItemType getType() {
        return ItemType.Key;
    }
    
}
