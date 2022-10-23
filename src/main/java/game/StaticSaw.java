package game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class StaticSaw extends Trap{
    public StaticSaw(float x, float y, Game game) {
        Circle trap = new Circle(game.level.platformHeight * 0.6);
        trap.setTranslateX(x);
        trap.setTranslateY(y);
        trap.setFill(Color.RED);

        this.node = trap;

        game.gameRoot.getChildren().add(trap);
    }
}
