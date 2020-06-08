/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.MoveController;
import java.util.Scanner;
import javax.swing.JOptionPane;
import maze.MapSite;
import maze.Maze;
import maze.Room;
import maze.RoomCovid;
import maze.listeners.EntryListener;

/**
 *
 * @author DENISA
 */


public class NewView implements EntryListener{

    Maze model;
    public NewView(Maze model) {
        this.model = model;
     
        for (Room r : model) {
            if (r instanceof RoomCovid)
                r.addEntryListener(this);
        }
    }

    @Override
    public void entered(MapSite mapSite) {
        System.err.println(String.format("Atentie! Camera infectata", mapSite.toString()));
    }
 
}

