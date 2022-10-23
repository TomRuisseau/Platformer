package game;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

import javafx.stage.Stage;

public class Game {
    public Player player;

    public Level  level;

    public  ArrayList<Enemy> enemies = new ArrayList<>();

    public Controler controler;

    public Display display;

    public Pane gameRoot = new Pane();

    public Stage primaryStage;

    public Game(Player player, Level level, Controler controler) {
        this.player = player;
        this.level = level;
        level.game = this;
        this.controler = controler;
    }

    public void update(){
        if ((controler.isPressed(KeyCode.Z) || controler.isPressed(KeyCode.SPACE) )) {
            player.jump();
        }
        else {
            player.canJump = false;
        }

        if (controler.isPressed(KeyCode.Q) &&  player.playerVelocity.getX() > -player.width * 0.2) {
            player.playerVelocity = player.playerVelocity.add(-player.width * 0.015, 0);
        }
        else if(player.playerVelocity.getX() < 0){
            if(player.isTouchingGround){//friction au sol
                player.playerVelocity = player.playerVelocity.add( player.width * 0.25 * 0.05, 0);

                if(player.playerVelocity.getX() > 0){//empeche la friction de causer un demi tour
                    player.playerVelocity = player.playerVelocity.add( -player.playerVelocity.getX(), 0);
                }
            }
            else{//friction dans l'air
                player.playerVelocity = player.playerVelocity.add( player.width * 0.25 * 0.02, 0);
            }
        }


        if (controler.isPressed(KeyCode.D) &&  player.playerVelocity.getX() < player.width * 0.2) {
            player.playerVelocity = player.playerVelocity.add( player.width * 0.015, 0);
        }
        else if( player.playerVelocity.getX() > 0){
            if(player.isTouchingGround){//friction au sol
                player.playerVelocity = player.playerVelocity.add( -player.width * 0.25 * 0.05, 0);

                if(player.playerVelocity.getX() < 0){//empeche la friction de causer un demi tour
                    player.playerVelocity = player.playerVelocity.add( -player.playerVelocity.getX(), 0);
                }
            }
            else{//friction dans l'air
                player.playerVelocity = player.playerVelocity.add( -player.width * 0.25 * 0.02, 0);
            }
        }

        if (player.playerVelocity.getY() < player.height * 0.3 && !player.canJump){
            player.playerVelocity = player.playerVelocity.add(0, player.height *0.03);
        }

        player.moveY((int)player.playerVelocity.getY(), level);
        player.moveX((int)player.playerVelocity.getX(), level);

        if(controler.isPressed((KeyCode.R))){
            restart();
        }

        if(player.playerNode.getTranslateY() >= level.height){
            restart();
        }

        for(Trap trap : level.traps ){
            if (player.playerNode.getBoundsInParent().intersects(trap.node.getBoundsInParent())){
                restart();
            }
        }

    }
    public void restart(){

        player.playerNode.setTranslateX(level.platformWidth);
        player.playerNode.setTranslateY(level.height - (2 * level.platformHeight));
        player.playerVelocity.add(-player.playerVelocity.getX(),-player.playerVelocity.getY());
        gameRoot.setLayoutX(0);
        gameRoot.setLayoutY(-level.height + display.screenHeight);

    }

}
