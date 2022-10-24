package game.traps;


import game.Level;
import javafx.scene.*;


public abstract class Trap {
    public Node node;

    public abstract void updateTrap(Level level);
}
