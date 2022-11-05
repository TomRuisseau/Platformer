
package game;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class LevelData {



    private static final List<String> LEVEL1_PATFORMS = new ArrayList<>();
    private static final List<String> LEVEL1_HAZARDS = new ArrayList<>();


    public LevelData() {
        plateformReader();
        trapReader();
    }

    public static int getLevelDataHeight(){
        return LEVEL1_PATFORMS.size();
    }

    public static int getLevelDataWidth(){
        return LEVEL1_PATFORMS.get(0).length();
    }

    public static String getPlatformAt(int indice){
        return LEVEL1_PATFORMS.get(indice);
    }

    public static String getHazardAt(int indice){
        return LEVEL1_HAZARDS.get(indice);
    }
    public static void plateformReader() {
        StringBuilder sb = new StringBuilder();


        try {
            FileInputStream reader = new FileInputStream("Data/Level1DataP.txt");
            int title;
            String s;
            while ((title = reader.read()) != -1) {


                if(title == 10){
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
    public static void trapReader() {
        StringBuilder sb = new StringBuilder();


        try {
            FileInputStream reader = new FileInputStream("Data/Level1DataT.txt");
            int title;
            while ((title = reader.read()) != -1) {

                String s;
                if(title == 10){
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
