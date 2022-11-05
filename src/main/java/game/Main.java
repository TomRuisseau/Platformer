package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;


public class Main extends Application {


    Controler controler = new Controler();


    public void start(Stage primaryStage) {
        //creating the scene

        Scene scene = new Scene(controler.getDisplayAppRoot());
        scene.setCursor(Cursor.NONE); //hiding the cursor
        Controler.createListeners(scene);
        primaryStage.setTitle("Platformer Test");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);

        controler.initContent(primaryStage);

        //updating 60 ticks by seconds
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                controler.update();
            }
        };

        timer.start();
    }

    public static void main(String[] args){
        launch();
    }

}
