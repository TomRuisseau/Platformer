package game.traps;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class StaticSaw extends Trap {
    public float x;
    public float y;
    public StaticSaw(float x, float y, float platformHeight) {
        this.x = x;
        this.y = y;
        Circle trap = new Circle(platformHeight * 0.6);
        trap.setTranslateX(x);
        trap.setTranslateY(y);
        trap.setFill(Color.RED);
        node = trap;
    }

    @Override
    public void addNodesToRoot(Pane gameRoot) {
        gameRoot.getChildren().add(node);
    }

    //this trap is static so it only update the srpite and check if player should respawn
    public boolean updateTrap(long time, Node playerNode){
        return playerNode.getBoundsInParent().intersects(node.getBoundsInParent());
        //update the sprite
    }
    public void reset(){

    }
}
