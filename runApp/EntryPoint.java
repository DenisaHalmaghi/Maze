/*
 * EntryPoint.java
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
import factories.AbstractFactory;
import factories.AllThingsRandomFactory;
import factories.PlainFactory;
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
import view.TextUiMaze;

/**
 *
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class EntryPoint {

   
    /**
     * Creates a large maze with 10 rooms.
     */
        public static void main(String[] args) {
        MazeCreator mazeCreator = new MazeCreator(new AllThingsRandomFactory());
        Player player= Player.getPlayer();
        player.initializeHealth(400);
        Maze maze = mazeCreator.loadMaze("simpleMaze.txt");
        //Maze maze = mazeCreator.createLargeMaze();
        
        System.out.println(maze.getNumberOfRooms());
        maze.setCurrentRoom(0);
        GuiMaze viewer = new GuiMaze(maze, new MoveController(maze)); 
        viewer.run();
       //TextUiMaze viewer1 = new TextUiMaze(maze, new controller.MoveController(maze));
       
        maze.getCurrentRoom().enter();
    }
}
