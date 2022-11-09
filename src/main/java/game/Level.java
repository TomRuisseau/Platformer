package game;

import game.traps.Trap;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class Level {
    //array containing nodes of the platforms
    private final ArrayList<Node> platforms;

    //array containing the traps
    private final ArrayList<Trap> traps;

    //door for next level
    private Node door;

    private float width;
    private float height;
    private float platformWidth;
    private float platformHeight;

    public Level() {
        this.platforms =  new ArrayList<>();
        this.traps = new ArrayList<>();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getPlatformWidth() {
        return platformWidth;
    }

    public void setPlatformWidth(float platformWidth) {
        this.platformWidth = platformWidth;
    }

    public float getPlatformHeight() {
        return platformHeight;
    }

    public void setPlatformHeight(float platformHeight) {
        this.platformHeight = platformHeight;
    }

    public void addPlatform(Node platform){
        platforms.add(platform);
    }

    public void addTrap(Trap trap){
        traps.add(trap);
    }

    public ArrayList<Trap> getTraps() {
        return traps;
    }

    public ArrayList<Node> getPlatforms() {
        return platforms;
    }

    public Node getDoor(){
        return door;
    }

    public Node newDoor(float x,float y,float width, float height) {
        Rectangle myDoor = new Rectangle();
        myDoor.setWidth(width);
        myDoor.setHeight(height);
        myDoor.setTranslateX(x);
        myDoor.setTranslateY(y);
        myDoor.setFill(Color.DARKGREEN);
        this.door = myDoor;
        return myDoor;
    }


    //updating all traps at once, and returning wether the player is colliding with a trap or not
    public boolean updateTraps(long time, Node playerNode){
        for(Trap trap : traps ){
            //updating the trap and checking if player is colliding with it
            if(trap.updateTrap(time, playerNode)){
                return true;
            }

        }
        return false;
    }


}
