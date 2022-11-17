package game.traps;

import game.Game;
import game.Level;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.scene.shape.Rectangle;

import java.util.Random;


public class Laser extends Trap{

    //laser part used as the hitbox
    private Node laserNode;
    private int dephasing;
    private float x;
    private float y;

    public double laserWitdh;

    public double laserHeight;

    public Point2D socleCoor;

    public Point2D socleSize;

    public Laser(float x, float y, float platformWidth, float platformHeight) {

        this.x=x;
        this.y=y;

        //creating laser support
        Rectangle trap = new Rectangle(x + platformWidth * 0.1 , y, platformWidth * 0.8, platformHeight * 0.4);
        socleCoor = new Point2D(x + platformWidth * 0.1 , y - platformHeight/3);
        socleSize = new Point2D(platformWidth * 0.8, platformHeight * 0.4);
        trap.setFill(Color.rgb(0,0,0,0));
        this.node = trap;

        //creating laser
        Rectangle laser = new Rectangle(platformWidth * 0.4, platformHeight * 3);
        laserWitdh = platformWidth * 0.4;
        laserHeight = platformHeight * 3;
        laser.setTranslateX(x + platformWidth * 0.3);
        laser.setTranslateY(y - 3* platformHeight);
        laser.setFill(Color.rgb(0,0,0,0));
        this.laserNode = laser;

        //generating a random dephasing for the laser
        Random rand = new Random();
        dephasing = rand.nextInt(100);


    }
    public Point2D [] getSocleInfo(){
        Point2D[] infos = new Point2D[2];
        infos[0] = socleCoor;
        infos[1] = socleSize;
        return infos;
    }
    @Override
    public void addNodesToRoot(Pane gameRoot) {
        gameRoot.getChildren().add(node);
        gameRoot.getChildren().add(laserNode);
    }

    public boolean updateTrap(long time, Node playerNode) {
        //make the laser appaear or disappaer every 2 seconds
        if (((time + dephasing) % 80 == 0)){
            laserNode.setVisible(!laserNode.isVisible());
        }
        //check if the player is touching the laser
        return playerNode.getBoundsInParent().intersects(laserNode.getBoundsInParent()) && laserNode.isVisible();
    }

    public void reset(){

    }

    @Override
    public Point2D getSpriteSize() {
        return new Point2D(laserWitdh, laserHeight);
    }

    @Override
    public Node getNode(){
        return laserNode;
    }

    @Override
    public int getAnimationValue() {
        return laserNode.isVisible() ? 1 : 0;
    }
}
