/*
 * Maze.java
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
package maze;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class Maze implements Iterable<Room> {

    private final Map<Integer, Room> rooms = new HashMap<>();
    private Room current;

    private GridView grid = null;

    public Maze() {

    }

    public final void addRoom(final Room r) {
        rooms.put(r.getNumber(), r);
    }

    public final Room getRoom(int number) {
        return rooms.get(number);
    }

    @Override
    public Iterator<Room> iterator() {
        return rooms.values().iterator();
    }

    public int getNumberOfRooms() {
        return rooms.size();
    }

    public final Room getCurrentRoom() {
        return current;
    }

    public final void setCurrentRoom(final Room room) {
        current = room;
    }

    public final void setCurrentRoom(int number) {
        current = rooms.get(number);
    }
    //---------------------------------
    //Grid related
    public void createGrid() {
        grid = new MazeGridView();
    }

    public GridView getGrid() {
        return grid;
    }

    public interface GridView {

        Map<Room, Point> getLocations();

        int getMinX();

        int getMaxX();

        int getMinY();

        int getMaxY();

    }

    private class MazeGridView implements GridView {

        private final Map<Room, Point> locations;
        int minX = 0, maxX = 0, minY = 0, maxY = 0;

        MazeGridView() {
            locations = new HashMap<>();
            calculateDimensions();
        }

        @Override
        public Map<Room, Point> getLocations() {
            return locations;
        }

        @Override
        public int getMinX() {
            return minX;
        }

        @Override
        public int getMaxX() {
            return maxX;
        }

        @Override
        public int getMinY() {
            return minY;
        }

        @Override
        public int getMaxY() {
            return maxY;
        }

        private void calculateDimensions() {
            final int count = getNumberOfRooms();
            if (count != 0) {
                locations.put(getRoom(0), new Point(0, 0));
                boolean changed = true;
                while (changed && locations.size() < count) {
                    changed = false;

                    for (Room room : Maze.this) {
                        final Point pt = locations.get(room);
                        if (pt == null) {
                            continue;
                        }

                        for (Direction dir : Direction.values()) {
                            MapSite site = room.getSide(dir);

                            if (site instanceof Door) {
                                site = ((Door) site).getOtherSide(room);
                            }

                            if (site instanceof Room) {
                                //final Room other = (Room) site;
                                //if (locations.get(other) != null) {
                                if (locations.get((Room) site) != null) {
                                    continue;
                                }

                                Point next = null;
                                switch (dir) {
                                    case North:
                                        next = new Point(pt.x, pt.y - 1);
                                        minY = Math.min(minY, next.y);
                                        break;
                                    case South:
                                        next = new Point(pt.x, pt.y + 1);
                                        maxY = Math.max(maxY, next.y);
                                        break;
                                    case East:
                                        next = new Point(pt.x + 1, pt.y);
                                        maxX = Math.max(maxX, next.x);
                                        break;
                                    case West:
                                        next = new Point(pt.x - 1, pt.y);
                                        minX = Math.min(minX, next.x);
                                        break;
                                }

                                locations.put((Room) site, next);
                                changed = true;
                            }
                        }
                    }
                }
                if (minX < 0) {
                    Collection<Point> c = locations.values();
                    for (Point p : c) {
                        p.x -= minX;
                    }
                    maxX -= minX;
                    minX = 0;
                }
                if (minY < 0) {
                    Collection<Point> c = locations.values();
                    for (Point p : c) {
                        p.y -= minY;
                    }
                    maxY -= minY;
                    minY = 0;
                }
            }
        }

    }
}
