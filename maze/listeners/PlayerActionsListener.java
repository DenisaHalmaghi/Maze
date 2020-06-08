/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.listeners;

import gameClasses.IHealthManager;

/**
 *
 * @author DENISA
 */
public interface PlayerActionsListener {
    void consumePotion();
    void attack(IHealthManager target);
}
