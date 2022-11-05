package game.traps;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;


public class RotatingSaw extends Trap{
    //rotation radius
    public float radius;

    //rotation center
    public Point2D center;

    //link of rotation
    public Rectangle rectangleSaw = new Rectangle();


    public float x;
    public float y;

    //total rotation
    public double sumRota = 0 ;

    public RotatingSaw(float x, float y, float platformHeight) {
        this.x = x;
        this.y = y;

        //creating the saw
        radius = platformHeight;
        center = new Point2D(x,y);
        Circle trap = new Circle(platformHeight * 0.6);
        trap.setTranslateX(x);
        trap.setTranslateY(y - radius);
        trap.setFill(Color.DARKGREEN);


        //creating the link
        float length = (float) Math.sqrt(Math.pow(trap.getTranslateX()-center.getX(),2)+ Math.sqrt(Math.pow(trap.getTranslateY()-center.getY(),2)));

        rectangleSaw.setWidth((length*1.5));
        rectangleSaw.setHeight(platformHeight);

        rectangleSaw.setX(trap.getTranslateX()- 0.5*rectangleSaw.getWidth());
        rectangleSaw.setY(trap.getTranslateY() - 0.5*rectangleSaw.getWidth());
        rectangleSaw.setFill(Color.PINK);


        this.node = trap;

        rectangleSaw.getTransforms().add(new Rotate(90, center.getX(), center.getY()));
    }

    @Override
    public void addNodesToRoot(Pane gameRoot) {
        gameRoot.getChildren().add(node);
        gameRoot.getChildren().add(rectangleSaw);
    }

    public boolean updateTrap(long time, Node playerNode) {

        //make the saw translate in a circle shape along the time
        node.setTranslateX(center.getX() + radius * Math.cos(time / (float)60));
        node.setTranslateY(center.getY() + radius * Math.sin(time / (float)60));

        //make the link rotate at the same speed
        rectangleSaw.getTransforms().add(new Rotate(0.955,center.getX(),center.getY()));
        sumRota = sumRota + 0.955;

        return playerNode.getBoundsInParent().intersects(node.getBoundsInParent());
    }

    public void reset(){
        //replace the saw and the link at their initial position and rotation
        node.setTranslateX(x);
        node.setTranslateY(y - radius);


        rectangleSaw.getTransforms().add(new Rotate(-sumRota, center.getX(), center.getY()));
        
        sumRota = 0;

    }
}
