package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;


public class Main extends Application {


    Controler controler = new Controler();


    public void start(Stage primaryStage) {
        Scene scene = new Scene(controler.getDisplayAppRoot());
        scene.setCursor(Cursor.NONE);
        Controler.createListeners(scene);
        primaryStage.setTitle("Platformer Test");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);

        controler.initContent(primaryStage);


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
