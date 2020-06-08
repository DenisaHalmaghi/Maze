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
import maze.RoomCovid;

/**
 *
 * @author DENISA
 */

public class PlainFactory implements AbstractFactory{

    @Override
    public  Room makeRoom(int num){
        //camera simpla
        return new Room(num);
     }
     
     @Override
    public  Door makeDoor(Room r1, Room r2){
             // usa simpla
             return new Door(r1,r2);
        
     }

}
