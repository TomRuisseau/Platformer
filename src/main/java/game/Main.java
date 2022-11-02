package game;

import java.util.*;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Main extends Application {
<<<<<<< HEAD

    Display display = new Display(new Game(new Player(), new Level(), new Controler()));
=======
    Display display = new Display();
>>>>>>> beb066c6ee7a3ac90b51f6d811ff4064ef31e5a9


    public void start(Stage primaryStage) {

        Scene scene = new Scene(display.appRoot);
        scene.setCursor(Cursor.NONE);
        display.game.controler.createListeners(scene);
        primaryStage.setTitle("Platformer Test");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);

        display.initContent(primaryStage);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                display.game.update();
            }
        };
        timer.start();
    }

    public static void main(String[] args){
        launch();
    }

}
