/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import gameClasses.Item;
import gameClasses.Key;
import gameClasses.Potion;
import java.util.Random;
import maze.Door;
import maze.DoorNeedingKey;
import maze.GasRoom;
import maze.MapSite;
import maze.Room;

import maze.RoomWithItemInside;

/**
 *
 * @author DENISA
 */
public class AllThingsRandomFactory implements AbstractFactory{
     Random rand ;
     public AllThingsRandomFactory(){
         super();
         //pentru creearea random a camerelor
         rand=new Random();
     }
     @Override
     public  Door makeDoor(Room r1, Room r2){
        if( rand.nextInt(100)<50){

                System.out.println("usa cu cheie");
                return new DoorNeedingKey(r1,r2);
        }
            //usa simpla
            return new Door(r1,r2);
    }
     
        public  Room makeRoom(int num){
             Random rand=new Random();
             int number=rand.nextInt(100);
             Item item;
         //sanse de 33% sa creeam un anumit tip de camera
         if( number<33){
            
            if( rand.nextInt(100)<50){
                item=new Key();
            }
            else{
                
                item=new Potion();
            }
             return new RoomWithItemInside(num,item);
         }
         else if(number>66){
             //camera cu gaz
             return new GasRoom(num);
         }
         else{
             return new Room(num);
         }
     }
     
     
}
