/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.MoveController;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JOptionPane;
import maze.listeners.EntryListener;
import maze.MapSite;
import maze.Maze;
import maze.Room;

/**
 *
 * @author admin
 */
public class TextUiMaze implements EntryListener, Runnable {

    Maze model;
    MoveController ctrl;

    public TextUiMaze(Maze model, MoveController ctrl) {
        this.model = model;
        this.ctrl = ctrl;
//        for (Iterator<Room> iterator = model.iterator(); iterator.hasNext();) {
//            Room r = iterator.next();
//            r.addEntryListener(this);
//        } 

        for (Room r : model) {
            r.addEntryListener(this);
        }
    }

    @Override
    public void entered(MapSite ms) {
        System.err.println("----->>" + ms.toString());
    }

    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("a,s,w sau z:");
                String line = sc.nextLine();
                char c = line.charAt(0);

                switch (c) {
                    case 'a':
                        ctrl.moveWest(model);
                        break;
                    case 's':
                        ctrl.moveEast(model);
                        break;
                    case 'w':
                        ctrl.moveNorth(model);
                        break;
                    case 'z':
                        ctrl.moveSouth(model);
                        break;
                    case ' '://caut un drum din currentRoom la o destinatie 
                        Integer[] roomNr = {2, 3, 4, 5};
                        Object selectedRoom = JOptionPane.showInputDialog(null,
                                "Choose one", "Input",
                                JOptionPane.INFORMATION_MESSAGE, null,
                                roomNr, roomNr[0]);
                        JOptionPane.showMessageDialog(null, "Destinatia:"+selectedRoom);
                        // ce faca aici?
                        break;
                    default:
                        System.out.println("EXIT");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
