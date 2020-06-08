package runApp;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import maze.*;

public class TestLocations {

    static Map<Room, Point> locations = new HashMap<>();
    static Maze maze = SimpleMazeGame.createLargeMaze();

    public static void main(String[] args) {
        //Dimension bounds;
        //Point offset;
        final int count = maze.getNumberOfRooms();
        int minX = 0, maxX = 0, minY = 0, maxY = 0;
        locations.put(maze.getRoom(0), new Point(0, 0));
        boolean changed = true;
        while (changed && locations.size() < count) {
            changed = false;

            for (Room room : maze) {
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
                        final Room other = (Room) site;
                        if (locations.get(other) != null) {
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

                        locations.put(other, next);
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

        final int width = maxX - minX + 1;
        final int height = maxY - minY + 1;
        System.out.println("W=" + width + "  H=" + height);
        for (int i = 0; i < maze.getNumberOfRooms(); i++) {
            Point p = locations.get(maze.getRoom(i));
            System.out.println("Room " + i + ": x=" + p.x + "  y=" + p.y);
        }
        System.out.println("minX=" + minX + "   minY=" + minY);
//            bounds = new Dimension(width * ROOM_SIZE + 2 * MARGIN, height
//                    * ROOM_SIZE + 2 * MARGIN);
//        offset = new Point(minX, minY);

    }

}
