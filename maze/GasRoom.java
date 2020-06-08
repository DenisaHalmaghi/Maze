/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import gameClasses.Player;
import java.awt.Color;

/**
 *
 * @author DENISA
 */


public class GasRoom extends Room{
    final int DAMAGE=35;
    public GasRoom(int num) {
        super(num);
               
    }
     @Override
     //suprascriem culoarea
    public Color getColor() {
        return Color.green;
    }
    
    public void enter()
    {
        Player.getPlayer().modifyHealth(-DAMAGE);
        super.notifyEntryListeners();
    }
    
    
}