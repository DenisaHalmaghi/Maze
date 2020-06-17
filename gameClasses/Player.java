/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameClasses;

import java.util.HashMap;
import java.util.LinkedList;
import maze.Door;

/**
 *
 * @author DENISA
 */
public class Player implements IHealthManager {
    private int damage=5;
    private int maxHealth,currentHealth;
    private Weapon weapon=new Weapon(20);
    private static Player player;
    HashMap<ItemType,LinkedList<Item>> inventory=new HashMap<>();  
    public Player(){
     
    }
    
    public void initializeHealth(int hp){
        player.currentHealth=player.maxHealth=hp;
    }
      
    public void lootItem(Item item){
        
       
       LinkedList<Item> list=inventory.get(item.getType());
       
       if(list==null){
          list=new LinkedList<>();
          inventory.put(item.getType(), list);
       }
       list.add(item);
      
    }
    
    public int getHealth(){
        return currentHealth;
    }
    public void useItem(Item item){
        item.use();
        //get BY type and erase one from type
        ItemType type=item.getType();
        System.out.println(type);
        inventory.get(type).removeLast();
        
    }
    
    public void modifyHealth(int dh){
      
        currentHealth+=dh;
        if(currentHealth<=0){
          System.err.println("Ai pierdut!");
          System.exit(0);
        }
        if(currentHealth>maxHealth){
            currentHealth=maxHealth;
            
        }
        System.err.println("Current player health:"+currentHealth+"/"+maxHealth);
        
    }
    
    public static Player getPlayer(){
        if(player==null){
            player=new Player();
        }
        return player;
    }

    public void getInvetory() {
        //de sters!!!!!!!!!!!
         System.out.println("INVENTORY:");
        try{
             System.out.println("keys in inventory:"+inventory.get(ItemType.Key).size());
            
        }
        catch(Exception e){
            System.out.println("keys in inventory:"+0);
        }
        
        
         try{
              System.out.println("potions in inventory:"+inventory.get(ItemType.Potion).size());
            
        }
        catch(Exception e){
             System.out.println("potions in inventory:"+0);
        }
      
      
    }

    public Item getItem(ItemType type) {
        Item item=null;
        try{
            item=inventory.get(type).getFirst();
        }
        catch(Exception e){
            
        }
        return item;
    }
   
    public int getDamage() {
        return weapon.getDamage();
    }
}
