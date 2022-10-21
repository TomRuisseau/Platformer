package game;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    public Player player;

    public Level  level;

    public  ArrayList<Enemie> enemies = new ArrayList<>();

    Controler controler;

    public Pane gameRoot = new Pane();

    public Game(Player player, Level level, Controler controler) {
        this.player = player;
        this.level = level;
        this.controler = controler;
    }

    public void update(){
        if (controler.isPressed(KeyCode.Z) && player.playerNode.getTranslateY() >= 5) {
            player.jump();
        }
        else {
            player.canJump = false;
        }

        if (controler.isPressed(KeyCode.Q) && player.playerNode.getTranslateX() >= 5) {
            player.playerVelocity = player.playerVelocity.add(-player.playerVelocity.getX() - 10, 0);
        }
        else if(player.playerVelocity.getX() < 0){
            if(player.isTouchingGround){
                player.playerVelocity = player.playerVelocity.add( 0.5, 0);
            }
            else{
                player.playerVelocity = player.playerVelocity.add( 0.1, 0);
            }

        }


        if (controler.isPressed(KeyCode.D) && player.playerNode.getTranslateX() + player.width <= level.width - 5) {
            player.playerVelocity = player.playerVelocity.add(-player.playerVelocity.getX() + 10, 0);
        }
        else if( player.playerVelocity.getX() > 0){
            if(player.isTouchingGround){
                player.playerVelocity = player.playerVelocity.add( -0.5, 0);
            }
            else{
                player.playerVelocity = player.playerVelocity.add( -0.1, 0);
            }
        }

        if (player.playerVelocity.getY() < 10){
            player.playerVelocity = player.playerVelocity.add(0, 2);
        }

        player.moveY((int)player.playerVelocity.getY(), level);
        player.moveX((int)player.playerVelocity.getX(), level);

        if(controler.isPressed((KeyCode.R))){
            restart();
        }

        if(player.playerNode.getTranslateY() >= level.height){
            restart();
        }

    }
    public void restart(){

        player.playerNode.setTranslateX(0);
        player.playerNode.setTranslateY(500);
        player.playerVelocity.add(-player.playerVelocity.getX(),-player.playerVelocity.getY());
        gameRoot.setLayoutX(0);
    }

}
