package game.traps;

import game.Game;
import game.Level;
import game.traps.Trap;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class StaticSaw extends Trap {
    public float x;
    public float y;
    public StaticSaw(float x, float y, Game game) {
        this.x = x;
        this.y = y;
        Circle trap = new Circle(game.level.platformHeight * 0.6);
        trap.setTranslateX(x);
        trap.setTranslateY(y);
        trap.setFill(Color.RED);

        this.node = trap;

        game.gameRoot.getChildren().add(trap);
    }
    public void updateTrap(Level level){
        if (level.game.player.playerNode.getBoundsInParent().intersects(node.getBoundsInParent())){
            level.game.restart();
            return;
        }

        //update the sprite

    }
public void reset(Game game){

}
}
