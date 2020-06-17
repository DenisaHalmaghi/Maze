/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeCreator;

import factories.AbstractFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import maze.Direction;
import maze.Door;
import maze.Maze;
import maze.Room;
import maze.Wall;
import runApp.EntryPoint;

/**
 *
 * @author DENISA
 */
public class MazeCreator{

    AbstractFactory factory;
    
    public MazeCreator(AbstractFactory f) {
        factory=f;
    }
    
    public Maze createLargeMaze(){
        Maze maze = new Maze();
        Room room0 = factory.makeRoom(0);
        Room room1 = factory.makeRoom(1);
        Room room2 = factory.makeRoom(2);
        Room room3 = factory.makeRoom(3);
        Room room4 = factory.makeRoom(4);
        Room room5 = factory.makeRoom(5);
        Room room6 = factory.makeRoom(6);
        Room room7 = factory.makeRoom(7);
        Room room8 = factory.makeRoom(8);
        Room room9 = factory.makeRoom(9);
        
        Door door0 = factory.makeDoor(room0, room8);
        Door door1 = factory.makeDoor(room1, room2);
        Door door2 = factory.makeDoor(room2, room3);
        Door door3 = factory.makeDoor(room4, room5);
        Door door4 = factory.makeDoor(room5, room6);
        Door door5 = factory.makeDoor(room5, room8);
        Door door6 = factory.makeDoor(room6, room9);
        Door door7 = factory.makeDoor(room7, room8);
        Door door8 = factory.makeDoor(room1, room4);

        door1.setOpen(true);
        door2.setOpen(false);
        door3.setOpen(true);
        door4.setOpen(true);
        door5.setOpen(false);
        door6.setOpen(true);
        door7.setOpen(true);
        door8.setOpen(true);

        room1.setSide(Direction.North, door8);
        room1.setSide(Direction.East, new Wall());
        room1.setSide(Direction.South, new Wall());
        room1.setSide(Direction.West, door1);

        room2.setSide(Direction.North, new Wall());
        room2.setSide(Direction.East, door1);
        room2.setSide(Direction.South, new Wall());
        room2.setSide(Direction.West, door2);

        room3.setSide(Direction.North, new Wall());
        room3.setSide(Direction.East, door2);
        room3.setSide(Direction.South, new Wall());
        room3.setSide(Direction.West, new Wall());

        room4.setSide(Direction.North, new Wall());
        room4.setSide(Direction.East, new Wall());
        room4.setSide(Direction.South, door8);
        room4.setSide(Direction.West, door3);

        room5.setSide(Direction.North, door5);
        room5.setSide(Direction.East, door3);
        room5.setSide(Direction.South, new Wall());
        room5.setSide(Direction.West, door4);

        room6.setSide(Direction.North, door6);
        room6.setSide(Direction.East, door4);
        room6.setSide(Direction.South, new Wall());
        room6.setSide(Direction.West, new Wall());

        room7.setSide(Direction.North, new Wall());
        room7.setSide(Direction.East, new Wall());
        room7.setSide(Direction.South, new Wall());
        room7.setSide(Direction.West, door7);

        //room8.setSide(Direction.North, new Wall());
        room8.setSide(Direction.East, door7);
        room8.setSide(Direction.South, door5);
        room8.setSide(Direction.West, new Wall());
        room8.setSide(Direction.North, door0);
        
        room9.setSide(Direction.East, new Wall());
        room9.setSide(Direction.South, door6);
        room9.setSide(Direction.West, new Wall());
        room9.setSide(Direction.North, new Wall());

        room0.setSide(Direction.North, new Wall());
        room0.setSide(Direction.East, new Wall());
        room0.setSide(Direction.South, door0);
        room0.setSide(Direction.West, new Wall());

        maze.addRoom(room1);
        maze.addRoom(room2);
        maze.addRoom(room3);
        maze.addRoom(room4);
        maze.addRoom(room5);
        maze.addRoom(room6);
        maze.addRoom(room7);
        maze.addRoom(room8);
        maze.addRoom(room9);
        maze.addRoom(room0);
        maze.setCurrentRoom(room1);
        
        maze.createGrid();
        
        return maze;
    }
    
     public Maze createMaze() {

        Maze maze = new Maze();
        Room room0 = factory.makeRoom(0);
        Room room1 = factory.makeRoom(1);
        Door door = factory.makeDoor(room0, room1);
        door.setOpen(false);

        room0.setSide(Direction.North, new Wall());
        room0.setSide(Direction.East, door);
        room0.setSide(Direction.South, new Wall());
        room0.setSide(Direction.West, new Wall());

        room1.setSide(Direction.North, new Wall());
        room1.setSide(Direction.East, new Wall());
        room1.setSide(Direction.South, new Wall());
        room1.setSide(Direction.West, door);

        maze.addRoom(room0);
        maze.addRoom(room1);

        maze.setCurrentRoom(room1);
        maze.createGrid();
        return maze;
    }
    
      public Maze loadMaze(final String path) {
        Maze maze = new Maze();
        
        System.out.println("Please load a maze from the file!");
        Scanner sc=null;
        try {
             sc=new Scanner(new File(path));
        } catch (FileNotFoundException ex) {
             System.out.println("bubaaa");
            Logger.getLogger(EntryPoint.class.getName()).log(Level.SEVERE, null, ex);
        }
        LinkedList<Room> rooms=new LinkedList<>();
        LinkedList<String> neighbours=new LinkedList<>();
        LinkedList<String[]> stringDoors=new LinkedList<>();
        HashMap<Integer,Door> doors=new HashMap<>();
        String line;
        String[] lineComponents;
        
        //read file and instantiate all rooms
        //save the neighbours
        while(sc.hasNext()){
           String strNeighbours="";
            line=sc.nextLine();
            lineComponents=line.split(" ");
            if(lineComponents[0].equals("room")){
                //instantiate rooms
                Room room= factory.makeRoom(Integer.parseInt(lineComponents[1]));//new Room();
                rooms.add(room);
                maze.addRoom(room);
                for(int i=2;i<lineComponents.length;i++){
                    strNeighbours+=lineComponents[i]+" ";
                }
                  neighbours.add(strNeighbours); 
            }
            else{
                //save the doors
                stringDoors.add(lineComponents);
            }
        }
        
        instantiateDoors(stringDoors,rooms,doors);
        setAllRoomsSides(rooms,neighbours, doors);
        maze.createGrid();
        return maze;
    }
    
    protected void instantiateDoors( LinkedList<String[]> stringifiedDoors,LinkedList<Room> rooms,HashMap<Integer,Door> doors){
        for(String[] stringifiedDoor : stringifiedDoors){
            int doorNumber=Integer.parseInt(stringifiedDoor[1]);
            int firstRoom=Integer.parseInt(stringifiedDoor[2]);
            int secondRoom=Integer.parseInt(stringifiedDoor[3]);
            Door door=factory.makeDoor(rooms.get(firstRoom), rooms.get(secondRoom));
            if(stringifiedDoor[4].equals("open")){
                door.setOpen(true);
            }
            doors.put(doorNumber, door);
        }
    }
    
    protected void setAllRoomsSides(LinkedList<Room> rooms,LinkedList<String> neighbours, HashMap<Integer,Door> doors){
        Direction[] directions=Direction.values();
        for(int i=0;i<rooms.size();i++){
            //get neighbours
            String[] ng=neighbours.get(i).split(" ");
            //get room
            Room room=rooms.get(i);
            //for each neoghbour
            for(int j=0;j<ng.length;j++){
                int neighbour=Integer.parseInt(ng[j]);
                Direction dir=directions[j];
              //set the neighbour
                if(neighbour<0){
                    //wall
                    room.setSide(dir, new Wall());
                }
                else{
                    //door
                    Door neighbourDoor=doors.get(neighbour);  
                    room.setSide(dir, neighbourDoor);
                }
            }
        }
    }
    
    
    
}
