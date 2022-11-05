package game;

import game.traps.Trap;
import javafx.scene.Node;

import java.util.ArrayList;

public class Level {
    private final ArrayList<Node> platforms;
    private final ArrayList<Trap> traps;

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

    public boolean updateTraps(long time, Node playerNode){
        for(Trap trap : traps ){
            if(trap.updateTrap(time, playerNode)){
                return true;
            }

        }
        return false;
    }


}
