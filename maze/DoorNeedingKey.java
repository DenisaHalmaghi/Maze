/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import gameClasses.IHealthManager;
import gameClasses.Item;
import gameClasses.ItemType;
import gameClasses.Player;
import java.awt.Color;

/**
 *
 * @author DENISA
 */
public class DoorNeedingKey extends Door implements IHealthManager{
  //  boolean unlocked=false;
    private int health=60;
    private boolean unlocked=false;
    public DoorNeedingKey(Room r1, Room r2) {
        super(r1, r2);
        
    }
    
    @Override
    public Color getColor()
    {
            return Color.BLUE;
    }
    
    @Override
    public boolean isUnlocked(){
        
        return unlocked;
    }
    
    @Override
    public void unlock(){
        //ask player is he has a key in the inventory
        //player.hasKey?player.destroykey();set open
        Player player=Player.getPlayer();
        Item key=player.getItem(ItemType.Key);
        if(key!=null){
            player.useItem(key);
            unlocked=true;
            System.out.println("unlocked");
        }
        
        
        //unlocked=true;
        
        //System.out.println("I am being unlocked");
    }
    
    public void enter()
    {
        System.out.println("is open:"+open+" "+this);
             if(!isUnlocked()){
                //try to unlock it
                System.out.println("locked");
                unlock();
               
            }  
              open = true;
//            if (!open) {
//                    System.out.println("Opened the door.");
//                    open = true;
//            }

            super.notifyEntryListeners();
    }

    @Override
    public void modifyHealth(int dh) {
        health-=dh;
        System.out.println(health);
        if(health<=0){
          System.err.println("am spart usa");
          unlocked=true;
          // System.err.println("am spart usa");
        }
    }
    
    @Override
       public int getHealth() {
         return health;
    }
 
}
