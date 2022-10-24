package game;

import javafx.scene.Node;

import java.util.ArrayList;

public class Level {
    public ArrayList<Node> platforms = new ArrayList<>();
    public ArrayList<Trap> traps = new ArrayList<>();

    public Game game;

    public float width;
    public float height;
    public float platformWidth;
    public float platformHeight;

    public void updateTraps(){
        for(Trap trap : traps ){
            trap.updateTrap(this);
        }
    }


}
