/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class MazeUtil {

    public static String mazeString(String fName) throws IOException {

        String maze = "";
        FileReader fr = null;
        LineNumberReader lnr = null;
        String str;
        //int i;

        try {
            // create new reader
            fr = new FileReader(fName);
            lnr = new LineNumberReader(fr);

            // read lines till the end of the stream
            while ((str = lnr.readLine()) != null) {
                maze += str+"\n";
//                i = lnr.getLineNumber();
//                System.out.print("(" + i + ")");
//
//                // prints string
//                System.out.println(str);
            }
            
        } catch (Exception e) {
            // if any error occurs
            e.printStackTrace();
        } finally {
            // closes the stream and releases system resources
            if (fr != null) {
                fr.close();
            }
            if (lnr != null) {
                lnr.close();
            }
        }

        return maze;
    }
}