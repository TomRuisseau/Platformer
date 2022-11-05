package game.traps;

import game.Game;
import game.Level;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.scene.shape.Rectangle;


public class Laser extends Trap{

    //laser part used as the hitbox
    public Node laserNode;
    public int deSync;

    public float x;
    public float y;

    public Laser(float x, float y, float platformWidth, float platformHeight) {

        this.x=x;
        this.y=y;

        //creating laser support
        Rectangle trap = new Rectangle(x + platformWidth * 0.1 , y, platformWidth * 0.8, platformHeight);
        trap.setFill(Color.BEIGE);
        this.node = trap;

        //creating laser
        Rectangle laser = new Rectangle(x + platformWidth * 0.3, y - 3* platformHeight, platformWidth * 0.4, platformHeight * 3);
        laser.setFill(Color.HOTPINK);
        this.laserNode = laser;


    }

    @Override
    public void addNodesToRoot(Pane gameRoot) {
        gameRoot.getChildren().add(node);
        gameRoot.getChildren().add(laserNode);
    }

    public boolean updateTrap(long time, Node playerNode) {
        //make the laser appaear or disappaer every 2 seconds
        if ((time % 120 == 0)){
            laserNode.setVisible(!laserNode.isVisible());
        }
        //check if the player is touching the laser
        return playerNode.getBoundsInParent().intersects(laserNode.getBoundsInParent()) && laserNode.isVisible();
    }

    public void reset(){

    }
}
