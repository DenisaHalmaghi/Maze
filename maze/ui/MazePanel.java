/*
 * MazePanel.java
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
package maze.ui;

import controller.PlayerController;
import gameClasses.IHealthManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import maze.listeners.MazeMoveListener;
import maze.listeners.EntryListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

import maze.*;
import maze.listeners.PlayerActionsListener;

/**
 *
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class MazePanel extends JPanel {
    private static final long serialVersionUID = 10L;

    private static final int ROOM_SIZE = 40;
    private static final int WALL_THICKNESS = 6;
    private static final int MARGIN = 20;

    //pt. reprezentarea labirintului in 2D
    private Map<Room, Point> locations;
    private Dimension bounds;
    private Point offset;

    private final Maze maze;
    private final List<MazeMoveListener> listeners;
    private final PlayerActionsListener playerController = new PlayerController();
    public MazePanel(final Maze maze) {
        if (maze == null) {
            throw new NullPointerException("Null Maze");
        }

        this.listeners = new ArrayList<>();
        this.maze = maze;

        this.locations = null;
        
        //obtinem reprezentarea ca si grid 2D pt. "maze"
        //=> se actualizeaza:locations, offset si bounds
        getGridView();
        
        //se inregistreaza obiectul care va fi notificat
        //  la tastarea sagetilor de catre user
        super.addKeyListener(new MazeKeyListener());

        //"el" va fi notificat cand se intra intr-o camera
        final EntryListener el = new EntryListener() {
            @Override
            public void entered(final MapSite site) {
                MazePanel.this.repaint();
            }
        };

        for (Room room : maze) {
            room.addEntryListener(el);
        }
    }
    //
    public void addMoveListener(final MazeMoveListener ml) {
        listeners.add(ml);
    }

    public final synchronized void removeMoveListener(final MazeMoveListener ml) {
        listeners.remove(ml);
    }

    private void getGridView() {
        Maze.GridView grid = maze.getGrid();
        locations = grid.getLocations();
        final int width = grid.getMaxX() - grid.getMinX() + 1;
        final int height = grid.getMaxY() - grid.getMinY() + 1;
        bounds = new Dimension(width * ROOM_SIZE + 2 * MARGIN, 
                               height * ROOM_SIZE + 2 * MARGIN);
        offset = new Point(grid.getMinX(), grid.getMinY());
    }

//    private void calculateDimensions() {
//        final int count = maze.getNumberOfRooms();
//        if (count == 0) {
//            offset = new Point(0, 0);
//            bounds = new Dimension(0, 0);
//
//        } else {
//            int minX = 0, maxX = 0, minY = 0, maxY = 0;
//            locations.put(maze.getRoom(0), new Point(0, 0));
//            boolean changed = true;
//            while (changed && locations.size() < count) {
//                changed = false;
//
//                for (Room room : maze) {
//                    final Point pt = locations.get(room);
//                    if (pt == null) {
//                        continue;
//                    }
//
//                    for (Direction dir : Direction.values()) {
//                        MapSite site = room.getSide(dir);
//
//                        if (site instanceof Door) {
//                            site = ((Door) site).getOtherSide(room);
//                        }
//
//                        if (site instanceof Room) {
//                            //final Room other = (Room) site;
//                            //if (locations.get(other) != null) {
//                            if (locations.get((Room) site) != null) {
//                                continue;
//                            }
//
//                            Point next = null;
//                            switch (dir) {
//                                case North:
//                                    next = new Point(pt.x, pt.y - 1);
//                                    minY = Math.min(minY, next.y);
//                                    break;
//                                case South:
//                                    next = new Point(pt.x, pt.y + 1);
//                                    maxY = Math.max(maxY, next.y);
//                                    break;
//                                case East:
//                                    next = new Point(pt.x + 1, pt.y);
//                                    maxX = Math.max(maxX, next.x);
//                                    break;
//                                case West:
//                                    next = new Point(pt.x - 1, pt.y);
//                                    minX = Math.min(minX, next.x);
//                                    break;
//                            }
//
//                            locations.put((Room) site, next);
//                            changed = true;
//                        }
//                    }
//                }
//            }
//            if (minX < 0) {
//                Collection<Point> c = locations.values();
//                for (Point p : c) {
//                    p.x -= minX;
//                }
//                maxX -= minX;
//                minX = 0;
//            }
//            if (minY < 0) {
//                Collection<Point> c = locations.values();
//                for (Point p : c) {
//                    p.y -= minY;
//                }
//                maxY -= minY;
//                minY = 0;
//            }
//
//            final int width = maxX - minX + 1;
//            final int height = maxY - minY + 1;
//            bounds = new Dimension(width * ROOM_SIZE + 2 * MARGIN, height
//                    * ROOM_SIZE + 2 * MARGIN);
//            offset = new Point(minX, minY);
//        }
//    }

    @Override
    public boolean isFocusable() {
        return true;
    }

    @Override
    public Dimension getPreferredSize() {
        return bounds;
    }

    @Override
    public Dimension getMinimumSize() {
        return bounds;
    }

    @Override
    //https://docs.oracle.com/javase/tutorial/uiswing/painting/closer.html
    public void paintComponent(final Graphics g    ) {
        super.paintComponent(g);

        int dx = MARGIN + -offset.x * ROOM_SIZE;
        int dy = MARGIN + -offset.y * ROOM_SIZE;

        // draw rooms first
        for (Room room : maze) {
            final Point pt = locations.get(room);
            
            assert pt != null : room.getNumber();
           
            draw(g, room, dx + pt.x * ROOM_SIZE, dy + pt.y * ROOM_SIZE);
          
        }

        // draw walls and doors
        for (Room room : maze) {
            
            final Point pt = locations.get(room);
            assert pt != null : room.getNumber();

            draw(g, room, Direction.North, dx + pt.x * ROOM_SIZE
                    - WALL_THICKNESS / 2, dy + pt.y * ROOM_SIZE
                    - WALL_THICKNESS / 2);
            draw(g, room, Direction.South, dx + pt.x * ROOM_SIZE
                    - WALL_THICKNESS / 2, dy + pt.y * ROOM_SIZE + ROOM_SIZE
                    - WALL_THICKNESS / 2);
            draw(g, room, Direction.East, dx + pt.x * ROOM_SIZE + ROOM_SIZE
                    - WALL_THICKNESS / 2, dy + pt.y * ROOM_SIZE
                    - WALL_THICKNESS / 2);
            draw(g, room, Direction.West, dx + pt.x * ROOM_SIZE
                    - WALL_THICKNESS / 2, dy + pt.y * ROOM_SIZE
                    - WALL_THICKNESS / 2);
        }

        requestFocus();
    }

    protected void draw(final Graphics g, final Room room, int x, int y) {
        final Color color = room.getColor();
        g.setColor(color);
        g.fillRect(x, y, ROOM_SIZE, ROOM_SIZE);

        if (maze.getCurrentRoom() == room) {
            final Color inverse = new Color(0xffffff - color.getRGB());
            g.setColor(inverse);
            g.fillOval(x + ROOM_SIZE / 2 - 5, y + ROOM_SIZE / 2 - 5, 10, 10);
        }
    }

    protected void draw(final Graphics g, final Room room, final Direction dir,
            int x, int y) {
        final MapSite site = room.getSide(dir);

        if (site instanceof Door) {
            draw(g, room, (Door) site, dir, x, y);
        } else if (site instanceof Wall) {
            draw(g, room, (Wall) site, dir, x, y);
        }
    }

    protected void draw(final Graphics g, final Room room, final Door door,
            final Direction dir, int x, int y) {
        int width = 1, height = 1;

        switch (dir) {
            case North:
            case South:
                width = ROOM_SIZE + WALL_THICKNESS;
                x += WALL_THICKNESS;
                width -= 2 * WALL_THICKNESS;
                height = 2;
                break;
            case East:
            case West:
                height = ROOM_SIZE + WALL_THICKNESS;
                y += WALL_THICKNESS;
                height -= 2 * WALL_THICKNESS;
                width = 2;
                break;
        }

        g.setColor(room.getColor().darker());
        g.fillRect(x, y, width, height);

        switch (dir) {
            case North:
            case South:
                width = ROOM_SIZE + WALL_THICKNESS;
                height = WALL_THICKNESS;
                x += height;
                width -= 4 * height;
                break;
            case East:
            case West:
                height = ROOM_SIZE + WALL_THICKNESS;
                width = WALL_THICKNESS;
                y += width;
                height -= 4 * width;
                break;
        }

        if (door.isOpen()) {
            g.setColor(room.getColor());
        } else {
            g.setColor(door.getColor());
        }

        g.fillRect(x, y, width, height);
    }

    protected void draw(final Graphics g, final Room room, final Wall wall,
            final Direction dir, int x, int y) {
        g.setColor(wall.getColor());
        switch (dir) {
            case North:
            case South:
                g.fillRect(x, y, ROOM_SIZE + WALL_THICKNESS, WALL_THICKNESS);
                break;
            case East:
            case West:
                g.fillRect(x, y, WALL_THICKNESS, ROOM_SIZE + WALL_THICKNESS);
                break;
        }
    }

    private final class MazeKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(final KeyEvent evt) {
            int code = evt.getKeyCode();
            switch (code) {
                case KeyEvent.VK_UP:
                    for (MazeMoveListener ml : listeners) {
                        ml.moveNorth(maze);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    for (MazeMoveListener ml : listeners) {
                        ml.moveSouth(maze);
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    for (MazeMoveListener ml : listeners) {
                        ml.moveWest(maze);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    for (MazeMoveListener ml : listeners) {
                        ml.moveEast(maze);
                    }
                    break;
                case KeyEvent.VK_U:
                    for (MazeMoveListener ml : listeners) {
                        ml.undoMove();
                    }
                    break;
                case KeyEvent.VK_E:    
                    playerController.consumePotion();
                    break;
                case KeyEvent.VK_X:
                    Room currentRoom=maze.getCurrentRoom();
                    System.out.println(currentRoom);
                    //playerController.attack();
                    for (Direction direction : Direction.values()) {
                        MapSite Side=currentRoom.getSide(direction);
                        if(Side instanceof IHealthManager){
                            IHealthManager target=(IHealthManager)Side;
                            if(target.getHealth()>0){
                                playerController.attack(target);
                                break;
                            }  
                        }
                    }
                    break;    
            }
        }
    }
}
