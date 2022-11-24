package game;


import java.util.*;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controler {
    //map containing the info wether each key is pressed or not
    private static final HashMap<KeyCode, Boolean> keys = new HashMap<>();

    //model
    private final Game game;

    //view
    private final Display display;

    private float screenHeight;

    public Controler() {
        this.game = new Game();
        this.display = new Display();
    }

    public Pane getDisplayAppRoot(){
        return display.getAppRoot();
    }

    public boolean isPressed(KeyCode key){
        return keys.getOrDefault(key,false);
    }

    //method creating listeners on keys
    public static void createListeners(Scene scene){
        //setting the value of each map value to true when its right key is pressed and to false when it's released
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
    }

    //initializing all content
    public void initContent(Stage primaryStage){
        //determining the size of the user screen
        float screenWidth = (float) primaryStage.getWidth();
        screenHeight = (float) primaryStage.getHeight();

        //initializing game content
        game.initGame(screenWidth, screenHeight);


        //initializing graphic content
        display.initGraphism(screenWidth, screenHeight,game.getPlayerWidth(), game.getPlayerHeight(), game.getLevelWidth(), game.getLevelHeight(), game.getTrapList());
        display.createListeners(screenWidth, screenHeight, game.getGameRoot(), game.getLevelWidth(), game.getLevelHeight(), game.getPlayerNode() );


        //grouping all roots together
        display.addAllToRoot(game.getGameRoot());
    }

    //function called every tick to update all infos
    public void update(){
        game.updateTimes();

        if(game.getRespawnTime() > 30 && !game.isWon()){//prevent player from moving right after respawning

            if ((isPressed(KeyCode.Z) || isPressed(KeyCode.SPACE) )) {
                game.makePlayerJump();
            }
            else {//prevent player from jumping when mid air or double jumping
                game.preventPlayerFromJumping();
            }

            //if user wants to move left, we add velocity to the right unltil a max velocity
            //if not, we substract velocity to simulate friction
            if (isPressed(KeyCode.Q) &&  game.getPlayerVelocity().getX() > -game.getPlayerWidth() * 0.2) {
                game.addToPlayerVelocity(-game.getPlayerWidth() * 0.015, 0);
            }
            else if(game.getPlayerVelocity().getX() < 0){
                game.frictionLeft();
            }

            //if user wants to move right, we add velocity to the right unltil a max velocity
            //if not, we substract velocity to simulate friction
            if (isPressed(KeyCode.D) &&  game.getPlayerVelocity().getX() < game.getPlayerWidth() * 0.2) {
                game.addToPlayerVelocity(game.getPlayerWidth()* 0.015, 0);
            }
            else if( game.getPlayerVelocity().getX() > 0){
                game.frictionRight();
            }

            //adding downard velocity every tick to simulatre gravity
            game.gravity();


            //make player move depending on its velocity
            game.makePlayerMoveX();
            game.makePlayerMoveY();
        }

        //restart the game when R is pressed
        if(isPressed((KeyCode.R))){
            game.restart(screenHeight);
            game.addToDeathCount(-1);
        }

        //restart the game if the player falls below the screen
        game.isPlayerOutOfScreen(screenHeight);

        //update the traps every tick
        //it also checks if player is colliding with them
        game.updateTraps(screenHeight);

        //update ui text
        display.setDeathCount(game.getDeathCount());

        //replace and animate palayerSprite
        display.updatePlayerSprite(game.getPlayerNode(), game.getPlayerSpriteRotate());

        //replace and animate trapSprites
        display.updateTrapsSprite(game.getTrapList());

        //replace the background
        display.updateBackground(game.getGameRoot());

        //display or hide victory screen
        display.updateVictoryScreen(game.isWon());

    }




}
