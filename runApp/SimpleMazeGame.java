/*
 * SimpleMazeGame.java
 * Copyright (c) 2008, Drexel University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Drexel University nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY DREXEL UNIVERSITY ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DREXEL UNIVERSITY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package runApp;

//import java.io.IOException;
//import java.util.Scanner;
//import java.util.StringTokenizer;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import maze.ui.MazeViewer;
//import static util.MazeUtil.mazeString;
import Factories.AbstractFactory;
import Factories.CovidFactory;
import Factories.LockedDoorFactory;
import Factories.PlainFactory;
import view.GuiMaze;
import controller.MoveController;
import gameClasses.Player;
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
import mazeCreator.MazeCreator;
import view.NewView;
import view.TextUiMaze;

/**
 *
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class SimpleMazeGame {

   
    /**
     * Creates a large maze with 10 rooms.
     */
    public static Maze createLargeMaze() {
        Maze maze = new Maze();
        Room room0 = new Room(0);
        Room room1 = new Room(1);
        Room room2 = new Room(2);
        Room room3 = new Room(3);
        Room room4 = new Room(4);
        Room room5 = new Room(5);
        Room room6 = new Room(6);
        Room room7 = new Room(7);
        Room room8 = new Room(8);
        Room room9 = new Room(9);
        
        Door door0 = new Door(room0, room8);
        Door door1 = new Door(room1, room2);
        Door door2 = new Door(room2, room3);
        Door door3 = new Door(room4, room5);
        Door door4 = new Door(room5, room6);
        Door door5 = new Door(room5, room8);
        Door door6 = new Door(room6, room9);
        Door door7 = new Door(room7, room8);
        Door door8 = new Door(room1, room4);

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

    /**
     * Creates a small maze.
     */
    public static Maze createMaze() {

        Maze maze = new Maze();
        Room room0 = new Room(0);
        Room room1 = new Room(1);
        Door door = new Door(room0, room1);
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

      public static void main(String[] args) {
        MazeCreator mazeCreator = new MazeCreator(new LockedDoorFactory());
        Player player= Player.getPlayer();
        player.initializeHealth(400);
        Maze maze = mazeCreator.loadMaze("simpleMaze.txt");
        //Maze maze = mazeCreator.createLargeMaze();
        
        System.out.println(maze.getNumberOfRooms());
        maze.setCurrentRoom(0);
        GuiMaze viewer = new GuiMaze(maze, new MoveController(maze)); 
        viewer.run();
       //TextUiMaze viewer1 = new TextUiMaze(maze, new controller.MoveController(maze));
        NewView viewer1 = new NewView(maze);
        
        maze.getCurrentRoom().enter();
    }
}
