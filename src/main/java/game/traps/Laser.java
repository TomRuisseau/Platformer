package game.traps;

import game.Game;
import game.Level;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.scene.shape.Rectangle;


public class Laser extends Trap{

    public Node laserNode;

    public Laser(float x, float y, Game game) {


        Rectangle trap = new Rectangle(x + game.level.platformWidth * 0.1 , y, game.level.platformWidth * 0.8, game.level.platformHeight);
        trap.setFill(Color.BEIGE);
        this.node = trap;

        Rectangle laser = new Rectangle(x + game.level.platformWidth * 0.3, y - 3* game.level.platformHeight, game.level.platformWidth * 0.4, game.level.platformHeight * 3);
        laser.setFill(Color.HOTPINK);
        this.laserNode = laser;

        game.gameRoot.getChildren().add(trap);
        game.gameRoot.getChildren().add(laser);
    }

    public void updateTrap(Level level) {
        if (level.game.player.playerNode.getBoundsInParent().intersects(laserNode.getBoundsInParent()) && laserNode.isVisible() ){
            level.game.restart();
            return;
        }

        if ((level.game.time % 120 == 0)){
            laserNode.setVisible(!laserNode.isVisible());
        }

    }
}
