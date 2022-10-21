package game;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    public Player player;

    public Level  level;

    public  ArrayList<Enemie> enemies = new ArrayList<>();

    public HashMap<KeyCode, Boolean> keys = new HashMap<>();

    public Pane gameRoot = new Pane();

    public Game(Player player, Level level) {
        this.player = player;
        this.level = level;
    }

    public void update(){
        if (isPressed(KeyCode.Z) && player.playerNode.getTranslateY() >= 5) {
            player.jump();
        }

        if (isPressed(KeyCode.Q) && player.playerNode.getTranslateX() >= 5) {
            player.moveX(-5, level);
        }

        if (isPressed(KeyCode.D) && player.playerNode.getTranslateX() + player.width <= level.width - 5) {
            player.moveX(5, level);
        }

        if (player.playerVelocity.getY() < 10){
            player.playerVelocity = player.playerVelocity.add(0, 1);
        }

        player.moveY((int)player.playerVelocity.getY(), level);
    }
    public void restart(){

        player.playerNode.setTranslateX(0);
        player.playerNode.setTranslateY(600);
        player.playerVelocity.add(-player.playerVelocity.getX(),-player.playerVelocity.getY());
    }

    public boolean isPressed(KeyCode key){
        return keys.getOrDefault(key,false);
    }
}
