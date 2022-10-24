package game.traps;

import game.Level;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import game.Game;
import javafx.geometry.Point2D;


public class RotatingSaw extends Trap{
    public float radius;

    public Point2D center;

    public RotatingSaw(float x, float y, Game game) {

        radius = game.level.platformHeight;
        center = new Point2D(x,y);
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

        node.setTranslateX(center.getX() + radius * Math.cos(level.game.time / (float)60));
        node.setTranslateY(center.getY() + radius * Math.sin(level.game.time / (float)60));
    }
}
