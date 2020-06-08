/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import gameClasses.Item;
import gameClasses.Key;
import gameClasses.Player;
import gameClasses.Potion;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author DENISA
 */

public class RoomWithItemInside extends Room{
    Item item;
   // Random rand;
    public RoomWithItemInside(int num,Item pItem) {
        super(num);
        item=pItem;
    }
     @Override
     //suprascriem culoarea
    public Color getColor() {
        if(item!=null)
            return item.getColor();
        return Color.WHITE;
    }
    
    public void enter()
    {
        if(item!=null){
           Player.getPlayer().lootItem(item);
           item=null;
        }

        super.notifyEntryListeners();
        Player.getPlayer().getInvetory();
    }
    
    
}
