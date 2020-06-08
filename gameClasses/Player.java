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
   // LinkedList<Item> inventory =new LinkedList<>();
    HashMap<ItemType,LinkedList<Item>> inventory=new HashMap<>();  
    public Player(){
      // player=new Player();
    }
    
    public void initializeHealth(int hp){
        player.currentHealth=player.currentHealth=hp;
    }
      
    public void lootItem(Item item){
        
        //inventory.put(item.getType(), item);
       LinkedList<Item> list=inventory.get(item.getType());
       //not type but name -> golden key, silver jey  
       if(list==null){
          list=new LinkedList<>();
          inventory.put(item.getType(), list);
       }
       list.add(item);
      //  System.out.println(item.getType());
    }
    
    public int getHealth(){
        return currentHealth;
    }
    public void useItem(Item item){
        //delete from inventory
        item.use();
        //get type and erase one from type
        ItemType type=item.getType();
        System.out.println(type);
        inventory.get(type).removeLast();
        
    }
    
    public void modifyHealth(int dh){
       // System.out.println(dh);
        currentHealth+=dh;
        if(currentHealth<=0){
          System.err.println("Ai pierdut!");
          System.exit(0);
        }
        System.err.println("Current health:"+currentHealth);
        
    }
    
    public static Player getPlayer(){
        if(player==null){
            player=new Player();
        }
        return player;
    }

    public void getInvetory() {
        //de sters!!!!!!!!!!!
         System.out.println("HEALTH:"+currentHealth);
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
    
    public Item getKey() {
        return new Key();
    }

    public int getDamage() {
        return weapon.getDamage();
    }
}
