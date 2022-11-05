package game;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class Display {
    private final Pane appRoot = new Pane();

    private final Pane uiRoot = new Pane();

    private Rectangle backGround;


    public Display() {

    }

    public Pane getAppRoot(){
        return appRoot;
    }


    public void initGraphism(float screenWidth, float screenHeight) {
        backGround = new Rectangle(screenWidth , screenHeight);
    }

    public void addAllToRoot(Pane gameRoot){
       appRoot.getChildren().addAll(backGround,gameRoot, uiRoot);
    }

}
