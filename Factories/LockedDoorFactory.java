/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories;

import gameClasses.Item;
import gameClasses.Key;
import gameClasses.Potion;
import java.util.Random;
import maze.Door;
import maze.DoorNeedingKey;
import maze.GasRoom;
import maze.MapSite;
import maze.Room;
import maze.RoomCovid;
import maze.RoomWithItemInside;

/**
 *
 * @author DENISA
 */
public class LockedDoorFactory extends PlainFactory{
     Random rand ;
     int keys=0;
     public LockedDoorFactory(){
         super();
         //pentru creearea random a camerelor
         rand=new Random();
     }
     @Override
     public  Door makeDoor(Room r1, Room r2){
         //sanse de 40% sa creeam o camera de tip covid
         System.out.println(keys);
         if( rand.nextInt(100)<50){
             //doar daca avem chei existenta instantiem camera care necesita cheie
            // if(keys>0){
                 keys--;
                 System.out.println("usa cu cheie");
                 return new DoorNeedingKey(r1,r2);
                 
            // }
             
         }
             //camera simpla
             return new Door(r1,r2);
         
     }
     
        public  Room makeRoom(int num){
             Random rand=new Random();
             Item item;
         //sanse de 40% sa creeam o camera cu item
         if( rand.nextInt(100)<60){
             //camera cu item in ea
            if( rand.nextInt(100)<0){
                 System.out.println("avem o cheie");
                keys++;
                item=new Key();
            }
            else{
                //camera simpla
                item=new Potion();
            }
             return new RoomWithItemInside(num,item);
         }
         else{
             //camera simpla
             return new GasRoom(num);
         }
     }
     
     
}
