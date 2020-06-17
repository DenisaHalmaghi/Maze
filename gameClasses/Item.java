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
public abstract class Item {
   
    public abstract void use();
    public abstract Color getColor();

    public abstract ItemType getType();
}
