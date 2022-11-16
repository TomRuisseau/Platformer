package game.traps;


import javafx.scene.*;
import javafx.scene.layout.Pane;


public abstract class Trap {
    //main node of the trap, usally used as the hitbox
    protected Node node;

    //method to update trap position and sprite
    //and at the same time check if player is in the trap or not
    public abstract boolean updateTrap(long time, Node playerNode);

    //method to reset the trap at its initial postion and behaviour
    public abstract void reset();

    public Node getNode(){return node;}

    public abstract double getSawRadius();

    //method to group all part of the trap together and put them in the scene
    public abstract void addNodesToRoot(Pane gameRoot);
}
