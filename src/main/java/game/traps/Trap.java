package game.traps;


import javafx.scene.*;
import javafx.scene.layout.Pane;


public abstract class Trap {
    Node node;

    public abstract boolean updateTrap(long time, Node playerNode);
    public abstract void reset();
    public abstract void addNodesToRoot(Pane gameRoot);
}
