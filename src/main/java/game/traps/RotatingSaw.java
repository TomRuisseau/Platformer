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


    public float x;
    public float y;

    public double sumRota = 0 ;

    public RotatingSaw(float x, float y, Game game) {
        this.x = x;
        this.y = y;

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
        System.out.println(rectangleSaw.getRotate());

        rectangleSaw.getTransforms().add(new Rotate(90, center.getX(), center.getY()));



    }

    public void updateTrap(Level level) {
        if (level.game.player.playerNode.getBoundsInParent().intersects(node.getBoundsInParent())){
            level.game.restart();
            return;
        }




        node.setTranslateX(center.getX() + radius * Math.cos(level.game.time / (float)60));
        node.setTranslateY(center.getY() + radius * Math.sin(level.game.time / (float)60));


      rectangleSaw.getTransforms().add(new Rotate(0.955,center.getX(),center.getY()));
        sumRota = sumRota + 0.955;
    }

    public void reset(Game game){
        node.setTranslateX(x);
        node.setTranslateY(y - radius);


        rectangleSaw.getTransforms().add(new Rotate(-sumRota, center.getX(), center.getY()));
        
        sumRota = 0;
        System.out.println(rectangleSaw.getRotate());

    }
}
