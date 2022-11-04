package game.traps;

import game.Level;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import game.Game;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;


public class RotatingSaw extends Trap{
    public float radius;

    public Point2D center;

    public Rectangle rectangleSaw = new Rectangle();

    public int compteur = 0;

    public RotatingSaw(float x, float y, Game game) {

        radius = game.level.platformHeight;
        center = new Point2D(x,y);
        Circle trap = new Circle(game.level.platformHeight * 0.6);
        trap.setTranslateX(x);
        trap.setTranslateY(y - radius);
        trap.setFill(Color.DARKGREEN);

        float length = (float) Math.sqrt(Math.pow(trap.getTranslateX()-center.getX(),2)+ Math.sqrt(Math.pow(trap.getTranslateY()-center.getY(),2)));

        rectangleSaw.setWidth((length*1.5));
        rectangleSaw.setHeight(game.level.platformHeight);

        rectangleSaw.setX(trap.getTranslateX()- 0.5*rectangleSaw.getWidth());
        rectangleSaw.setY(trap.getTranslateY() - 0.5*rectangleSaw.getWidth());
        rectangleSaw.setFill(Color.PINK);




        this.node = trap;

        game.gameRoot.getChildren().add(trap);
        game.gameRoot.getChildren().add(rectangleSaw);



    }

    public void updateTrap(Level level) {
        if (level.game.player.playerNode.getBoundsInParent().intersects(node.getBoundsInParent())){
            level.game.restart();
            return;
        }

        if(compteur <1) {
            rectangleSaw.getTransforms().add(new Rotate(90, center.getX(), center.getY()));
            compteur++;
        }


        node.setTranslateX(center.getX() + radius * Math.cos(level.game.time / (float)60));
        node.setTranslateY(center.getY() + radius * Math.sin(level.game.time / (float)60));


      rectangleSaw.getTransforms().add(new Rotate(0.955,center.getX(),center.getY()));
    }
}
