
package game;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class LevelData {
    //both arrays should have the same size

    //array containing platforms data
    private static final List<String> LEVEL1_PATFORMS = new ArrayList<>();

    //array containing traps data
    private static final List<String> LEVEL1_HAZARDS = new ArrayList<>();



    public LevelData() {
        //reading the files
        plateformReader();
        trapReader();
    }

    //return height of the data arrays
    public static int getLevelDataHeight(){
        return LEVEL1_PATFORMS.size();
    }

    //return width of the data arrays
    public static int getLevelDataWidth(){
        return LEVEL1_PATFORMS.get(0).length();
    }

    //return plateform line stored at wanted indice
    public static String getPlatformAt(int indice){
        return LEVEL1_PATFORMS.get(indice);
    }

    //return traps line stored at wanted indice
    public static String getHazardAt(int indice){
        return LEVEL1_HAZARDS.get(indice);
    }

    //methode to read the platform file
    public static void plateformReader() {
        StringBuilder sb = new StringBuilder();


        try {
            FileInputStream reader = new FileInputStream("Data/Level1DataP.txt");
            int title;
            String s;
            //reading the char
            while ((title = reader.read()) != -1) {

                //creating lines
                if(title == 10){ //10 is ascii code of "\n"
                    s = sb.toString();
                    LEVEL1_PATFORMS.add(s);
                    sb.delete(0,sb.length());
                }
                else{
                    sb.append((char)title);
                }

            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    //methode to read the traps file
    public static void trapReader() {
        StringBuilder sb = new StringBuilder();


        try {
            FileInputStream reader = new FileInputStream("Data/Level1DataT.txt");
            int title;
            String s;
            //reading the char
            while ((title = reader.read()) != -1) {

                //creating the line
                if(title == 10){ //10 is ascii code of "\n"
                    s = sb.toString();
                    LEVEL1_HAZARDS.add(s);
                    sb.delete(0,sb.length());
                }
                else{
                    sb.append((char)title);
                }

            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}
