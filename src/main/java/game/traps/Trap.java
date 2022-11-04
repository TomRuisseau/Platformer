package game.traps;


import game.Level;
import javafx.scene.*;
import game.Game;


public abstract class Trap {
    public Node node;

    public abstract void updateTrap(Level level);
        public abstract void reset(Game game);
}
