package game;

import java.util.*;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Main extends Application {
    Display display = new Display(new Game(new Player(), new Level()));

    public void start(Stage primaryStage) {
        display.initContent();

        Scene scene = new Scene(display.appRoot);
        scene.setOnKeyPressed(event -> display.game.keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> display.game.keys.put(event.getCode(), false));
        primaryStage.setTitle("Platformer Test");
        primaryStage.setScene(scene);
        primaryStage.show();

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
