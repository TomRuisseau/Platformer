package game;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class Display {
    //root cotaining every other one, the one we show
    private final Pane appRoot = new Pane();

    //root containg all ui infos
    private final Pane uiRoot = new Pane();

    private Rectangle backGround;


    public Display() {

    }

    public Pane getAppRoot(){
        return appRoot;
    }

    //create graphic elements of the background and the ui
    public void initGraphism(float screenWidth, float screenHeight) {
        backGround = new Rectangle(screenWidth , screenHeight);
    }

    //adding all roots to the bottom one which is appRoot
    public void addAllToRoot(Pane gameRoot){
       appRoot.getChildren().addAll(backGround,gameRoot, uiRoot);
    }

}
