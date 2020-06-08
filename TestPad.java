
import maze.Direction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public class TestPad {

    static String[] m = {"*****",
        "*   *",
        "*   *",
        "*   *",
        "*****"};

    static void addDoor(Direction dir, String[] a) {
        switch (dir) {
            case North:
                a[0] = "** **";
                break;
            case East:
                a[2] = a[2].substring(0, 3) + " ";//"*    ";
                break;
            case South:
                a[4] = "** **";
                break;
            case West:
                a[2] = " " + a[2].substring(1, 4);
                break;
        }
    }

    static void print() {
        for (int i = 0; i < m.length; System.out.println(m[i++])) {

        }
    }

    public static String pad(String str, int size, char padChar) {
        StringBuffer padded = new StringBuffer(str);

        while (padded.length() < size) {
            padded.insert(0, padChar);

        }
        return padded.toString();
    }

    public static void main(String[] args) {
        print();
        System.out.println("---------");
        addDoor(Direction.North, m);
        addDoor(Direction.West, m);
        addDoor(Direction.East, m);
        addDoor(Direction.South, m);
        print();
        String s1 = "1", s2 = "22", s3 = "333";
        System.out.println("." + pad(s1, 3, '0') + ".");
        System.out.println("." + pad(s2, 3, '0') + ".");
        System.out.println("." + pad(s3, 3, '0') + ".");
    }
}
