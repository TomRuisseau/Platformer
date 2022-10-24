package game.traps;

import game.Level;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import game.Game;
import game.traps.Trap;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class RotatingSaw extends Trap{
    public float radius;

    public RotatingSaw(float x, float y, Game game) {

        radius = game.level.platformHeight;
        Circle trap = new Circle(game.level.platformHeight * 0.6);
        trap.setTranslateX(x);
        trap.setTranslateY(y - radius);
        trap.setFill(Color.DARKGREEN);


        this.node = trap;

        game.gameRoot.getChildren().add(trap);
    }

    public void updateTrap(Level level) {
        if (level.game.player.playerNode.getBoundsInParent().intersects(node.getBoundsInParent())){
            level.game.restart();
            return;
        }
    }
}
