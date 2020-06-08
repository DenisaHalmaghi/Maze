/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories;


import java.util.Random;
import maze.Door;
import maze.MapSite;
import maze.Room;

/**
 *
 * @author DENISA
 */

//prototip pentru fabrica de componente pentru maze
public interface AbstractFactory {
    
    public Room makeRoom(int num);
    public Door makeDoor(Room r1, Room r2);
    
}
