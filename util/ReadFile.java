package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ReadFile {

    public static void main(String[] args) {

        BufferedReader br = null;

        try {

            String line;

            br = new BufferedReader(new FileReader("small.maze"));
            int i = 0;
            while ((line = br.readLine()) != null) {
                System.out.println("");
                System.out.println((++i)+":"+line +"<<END>>");

                StringTokenizer stringTokenizer = new StringTokenizer(line, " ");

                while (stringTokenizer.hasMoreElements()) {
                    System.out.print(stringTokenizer.nextToken()+" ");
                }
            }

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}