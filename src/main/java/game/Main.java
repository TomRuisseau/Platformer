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
    Display display = new Display(new Game(new Player(), new Level(), new Controler()));

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
