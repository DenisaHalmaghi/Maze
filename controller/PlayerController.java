/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gameClasses.IHealthManager;
import gameClasses.Item;
import gameClasses.ItemType;
import gameClasses.Player;
import maze.listeners.PlayerActionsListener;

/**
 *
 * @author DENISA
 */
public class PlayerController implements PlayerActionsListener{
    Player player=Player.getPlayer();
    @Override
    public void consumePotion() {
        
        System.out.println(player.getHealth());
        Item potion=player.getItem(ItemType.Potion);
        if(potion!=null){
            player.useItem(potion);
            System.out.println(player.getHealth());
        }
    }

    @Override
    public void attack(IHealthManager target) {
        target.modifyHealth(player.getDamage());
        System.out.println("attacking "+ target);
    }
    
}
