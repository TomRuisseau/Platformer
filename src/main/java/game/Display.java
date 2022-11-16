package game;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Display {
    //root cotaining every other one, the one we show
    private final Pane appRoot = new Pane();

    //root containg all ui infos
    private final Pane uiRoot = new Pane();

    private ImageView backGround;

    private Text deathCount;

    private ImageView playerSprite;



    public Display() {

    }

    public Pane getAppRoot(){
        return appRoot;
    }

    //create graphic elements of the background and the ui
    public void initGraphism(float screenWidth, float screenHeight, float playerWidth, float playerHeight, float levelWidth, float levelHeight) {
        //creating background
        Image backTiles = new Image("File:src/main/ressources/images/Tiles.png");
        ImageView backTilesView = new ImageView();
        backTilesView.setImage(backTiles);
        backTilesView.setFitHeight(levelHeight);
        backTilesView.setFitWidth(levelWidth);
        backGround = backTilesView;
        //backGround = new Rectangle(screenWidth , screenHeight);

        //creating deaht to display death counter
        Text text = new Text();
        text.setText("Death : 0");
        text.setX(80);
        text.setY(40);
        text.setScaleX(3);
        text.setScaleY(3);
        text.setFill(Color.CADETBLUE);
        deathCount = text;
        //adding text to the ui
        uiRoot.getChildren().add(text);

        //creating sprite for the character
        Image img = new Image("File:src/main/ressources/images/hamster-round.png");
        ImageView imgView = new ImageView();
        imgView.setImage(img);
        playerSprite = imgView;
        playerSprite.setFitHeight((double)playerHeight);
        playerSprite.setFitWidth((double)playerWidth);

    }

    public void setDeathCount(int deathNumbr){
        deathCount.setText("Death : " + deathNumbr);
    }

    public void updatePlayerSprite(Node player, float rotate){
        playerSprite.setTranslateX(player.getTranslateX());
        playerSprite.setTranslateY(player.getTranslateY());
        playerSprite.setRotate(rotate);
    }

    //observer part
    public void createListeners(float screenWidth, float screenHeight, Pane gameRoot, float levelWidth, float levelHeight, Node node){
        //both listeners check if the player is too far from the camera center, and move it in consequence
        //it allows auto scrollig when player is further than half of the screen


        node.translateXProperty().addListener((obs, old, newValue) ->{
            float offset = newValue.floatValue();

            if(offset > (screenWidth /2) && offset <levelWidth - (screenWidth /2)){
                gameRoot.setLayoutX(-(offset - (screenWidth /2)));
                backGround.setLayoutX(-(offset - (screenWidth /2)));
            }
        });

        node.translateYProperty().addListener((obs, old, newValue) ->{
            float offset = newValue.floatValue();

            if(offset > (screenHeight /2) && offset < levelHeight - (screenHeight /2)){
                gameRoot.setLayoutY(-(offset - (screenHeight /2)));
                backGround.setLayoutY(-(offset - (screenHeight /2)));
            }
        });
    }

    //adding all roots to the bottom one which is appRoot
    public void addAllToRoot(Pane gameRoot){
        gameRoot.getChildren().add(playerSprite);
        appRoot.getChildren().addAll(backGround,gameRoot, uiRoot);
    }

}
