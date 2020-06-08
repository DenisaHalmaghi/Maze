/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories;

import java.util.Random;
import maze.Door;
import maze.Room;
import maze.RoomCovid;

/**
 *
 * @author DENISA
 */
public class CovidFactory extends PlainFactory{
     Random rand ;
     public CovidFactory(){
         super();
         //pentru creearea random a camerelor
         rand=new Random();
     }
     @Override
     public  Room makeRoom(int num){
         //sanse de 40% sa creeam o camera de tip covid
         if( rand.nextInt(100)<40){
             //camera de tip covid
             return new RoomCovid(num);
         }
         else{
             //camera simpla
             return new Room(num);
         }
     }
     
}