package game;


import java.util.*;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controler {
    private static HashMap<KeyCode, Boolean> keys = new HashMap<>();

    private Game game;

    private Display display;

    private float screenWidth;
    private float screenHeight;

    public Controler() {
        this.game = new Game();
        this.display = new Display();
    }

    public Pane getDisplayAppRoot(){
        return display.getAppRoot();
    }

    public static void createListeners(Scene scene){
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
    }

    public void initContent(Stage primaryStage){
        screenWidth =  (float)primaryStage.getWidth();
        screenHeight = (float) primaryStage.getHeight();

        display.initGraphism(screenWidth, screenHeight);
        game.initGame(screenWidth, screenHeight);

        display.addAllToRoot(game.getGameRoot());
    }

    public void update(){
        game.updateTimes();

        if(game.getRespawnTime() > 30){
            if ((isPressed(KeyCode.Z) || isPressed(KeyCode.SPACE) )) {
                game.makePlayerJump();
            }
            else {
                game.preventPlayerFromJumping();
            }

            if (isPressed(KeyCode.Q) &&  game.getPlayerVelocity().getX() > -game.getPlayerWidth() * 0.2) {
                game.addToPlayerVelocity(-game.getPlayerWidth() * 0.015, 0);
            }
            else if(game.getPlayerVelocity().getX() < 0){
                if(game.playerTouchingGround()){//friction au sol
                    game.addToPlayerVelocity(game.getPlayerWidth()* 0.25 * 0.05, 0);

                    if(game.getPlayerVelocity().getX() > 0){//empeche la friction de causer un demi tour
                        game.addToPlayerVelocity(-game.getPlayerVelocity().getX(), 0);
                    }
                }
                else{//friction dans l'air
                    game.addToPlayerVelocity(game.getPlayerWidth() * 0.25 * 0.02, 0);
                }
            }


            if (isPressed(KeyCode.D) &&  game.getPlayerVelocity().getX() < game.getPlayerWidth() * 0.2) {
                game.addToPlayerVelocity(game.getPlayerWidth()* 0.015, 0);
            }
            else if( game.getPlayerVelocity().getX() > 0){
                if(game.playerTouchingGround()){//friction au sol
                    game.addToPlayerVelocity(-game.getPlayerWidth()* 0.25 * 0.05, 0);

                    if(game.getPlayerVelocity().getX() < 0){//empeche la friction de causer un demi tour
                        game.addToPlayerVelocity(-game.getPlayerVelocity().getX(), 0);
                    }
                }
                else{//friction dans l'air
                    game.addToPlayerVelocity(-game.getPlayerWidth() * 0.25 * 0.02, 0);
                }
            }

            if (game.getPlayerVelocity().getY()  < game.getPlayerHeight() * 0.3 && !game.playerCanJump()){
                game.addToPlayerVelocity(0, game.getPlayerHeight() * 0.03);
            }

            game.makePlayerMoveX();
            game.makePlayerMoveY();
        }

        if(isPressed((KeyCode.R))){
            game.restart(screenHeight);
        }

        if(game.getPlayerNode().getTranslateY() >= game.getLevelHeight()){
            game.restart(screenHeight);
        }

        game.updateTraps(screenHeight);

    }

    public boolean isPressed(KeyCode key){
        return keys.getOrDefault(key,false);
    }


}
