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

public class Controler {
    public HashMap<KeyCode, Boolean> keys = new HashMap<>();

    public void createListeners(Scene scene){
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
    }

    public boolean isPressed(KeyCode key){
        return keys.getOrDefault(key,false);
    }


}
