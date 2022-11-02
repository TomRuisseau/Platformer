
package game;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

public class LevelData {


    public static final List<String> LEVEL1_PATFORMS = new ArrayList<String>();
    public static void lineReader() {
        StringBuilder sb = new StringBuilder();


        try {
            FileInputStream reader = new FileInputStream("C:/Users/Administrateur/Documents/GitHub/Platformer/src/main/java/game/Level1DataP");
            int title;
            while ((title = reader.read()) != -1) {

                String s;
                sb.append((char)title);
                System.out.print((char)title);
                if(title == 10){
                    s = sb.toString();
                    LEVEL1_PATFORMS.add(s);
                    sb.delete(0,sb.length());
                }

            }
            System.out.print(" test " );
            System.out.println(LEVEL1_PATFORMS);
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }



    public static final String[] LEVEL1_HAZARDS = new String[] {
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000200000000000000000000000000000000000000000000000000000000000000000000000000",
            "000000000000200000000000000000000000000000000000000000000000000000000000000000",
            "000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "000010000010003300000000000000000000000000000000000000000000000000000000000000",
    };
}
