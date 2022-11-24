package game;

import game.traps.Laser;
import game.traps.Trap;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Display {
    //root cotaining every other one, the one we show
    private final Pane appRoot = new Pane();

    //root containg all ui infos
    private final Pane uiRoot = new Pane();

    private ImageView backGround;

    private Text deathCount;

    private ImageView victoryScreen;

    private ImageView playerSprite;


    private ArrayList<ImageView> trapsSprites = new ArrayList<>();

    private ArrayList<ImageView> socles = new ArrayList<>();


    public Display() {

    }

    public Pane getAppRoot() {
        return appRoot;
    }

    //create graphic elements of the background and the ui
    public void initGraphism(float screenWidth, float screenHeight, float playerWidth, float playerHeight, float levelWidth, float levelHeight, ArrayList<Trap> traps) {
        //creating background
        Image backTiles = new Image("File:src/main/ressources/images/Tiles.png");
        ImageView backTilesView = new ImageView();
        backTilesView.setImage(backTiles);
        backTilesView.setFitHeight(levelHeight);
        backTilesView.setFitWidth(levelWidth);
        backTilesView.setLayoutX(0);
        backTilesView.setLayoutY(-levelHeight + screenHeight);
        backGround = backTilesView;
        //backGround = new Rectangle(screenWidth , screenHeight);

        //creating deaht to display death counter
        Text text = new Text();
        text.setText("Death : 0");
        text.setX(80);
        text.setY(40);
        text.setScaleX(3);
        text.setScaleY(3);
        text.setFill(Color.PAPAYAWHIP);
        deathCount = text;
        //adding text to the ui
        uiRoot.getChildren().add(text);


        Image winImg = new Image("File:src/main/ressources/images/win.png");
        ImageView winView = new ImageView();
        winView.setImage(winImg);
        winView.setFitHeight(screenHeight);
        winView.setFitWidth(screenWidth);
        winView.setLayoutX(0);
        winView.setLayoutY(0);
        winView.setVisible(false);
        this.victoryScreen = winView;
        uiRoot.getChildren().add(winView);


        //creating sprite for the character
        Image img = new Image("File:src/main/ressources/images/sphere.png");
        ImageView imgView = new ImageView();
        imgView.setImage(img);
        playerSprite = imgView;
        playerSprite.setFitHeight((double) playerHeight);
        playerSprite.setFitWidth((double) playerWidth);

        //creating sprites for the traps
        for (Trap trap : traps) {
            if (trap instanceof Laser castedTrap) {
                //socle sprite
                Image socleImage = new Image("File:src/main/ressources/images/socleLaser.png");
                ImageView socleSprite = new ImageView();
                Node socle = trap.getNode();
                socleSprite.setImage(socleImage);

                socleSprite.setFitHeight(castedTrap.getSocleInfo()[1].getY());
                socleSprite.setFitWidth(castedTrap.getSocleInfo()[1].getX());

                socleSprite.setTranslateX(castedTrap.getSocleInfo()[0].getX());
                socleSprite.setTranslateY(castedTrap.getSocleInfo()[0].getY());

                socles.add(socleSprite);


                //laser sprite
                Image trapImage = new Image("File:src/main/ressources/images/laser.png");
                ImageView trapSprite = new ImageView();
                Node trapNode = trap.getNode();
                trapSprite.setImage(trapImage);

                trapSprite.setFitHeight(trap.getSpriteSize().getY());
                trapSprite.setFitWidth(trap.getSpriteSize().getX());

                trapSprite.setTranslateX(trapNode.getTranslateX());
                trapSprite.setTranslateY(trapNode.getTranslateY());

                trapsSprites.add(trapSprite);
            } else {
                Image trapImage = new Image("File:src/main/ressources/images/static_saw.png");
                ImageView trapSprite = new ImageView();
                trapSprite.setImage(trapImage);
                trapSprite.setFitHeight(trap.getSpriteSize().getX() * 2);
                trapSprite.setFitWidth(trap.getSpriteSize().getX() * 2);
                trapsSprites.add(trapSprite);

            }
        }

    }

    public void setDeathCount(int deathNumbr) {
        deathCount.setText("Death : " + deathNumbr);
    }

    public void updatePlayerSprite(Node player, float rotate) {
        playerSprite.setTranslateX(player.getTranslateX());
        playerSprite.setTranslateY(player.getTranslateY());
        playerSprite.setRotate(rotate);
    }

    public void updateTrapsSprite(ArrayList<Trap> traps) {
        int i = 0;
        for (Trap trap : traps) {
            if (trap instanceof Laser) {
                trapsSprites.get(i).setVisible(trap.getAnimationValue() == 1);
            } else {
                trapsSprites.get(i).setTranslateX(trap.getNode().getTranslateX() - trap.getSpriteSize().getX());
                trapsSprites.get(i).setTranslateY(trap.getNode().getTranslateY() - trap.getSpriteSize().getX());
                trapsSprites.get(i).setRotate(trap.getAnimationValue() * -3);
            }
            i++;
        }
    }

    //make the backgound layout equal to the level layout
    public void updateBackground(Pane gameRoot) {
        backGround.setLayoutX(gameRoot.getLayoutX());
        backGround.setLayoutY(gameRoot.getLayoutY());

    }

    public void updateVictoryScreen(boolean won){
        victoryScreen.setVisible(won);
    }

    //observer part
    public void createListeners(float screenWidth, float screenHeight, Pane gameRoot, float levelWidth, float levelHeight, Node node) {
        //both listeners check if the player is too far from the camera center, and move it in consequence
        //it allows auto scrollig when player is further than half of the screen


        node.translateXProperty().addListener((obs, old, newValue) -> {
            float offset = newValue.floatValue();

            if (offset > (screenWidth / 2) && offset < levelWidth - (screenWidth / 2)) {
                gameRoot.setLayoutX(-(offset - (screenWidth / 2)));
                //backGround.setLayoutX(-(offset - (screenWidth /2)));
            }
        });

        node.translateYProperty().addListener((obs, old, newValue) -> {
            float offset = newValue.floatValue();

            if (offset > (screenHeight / 2) && offset < levelHeight - (screenHeight / 2)) {
                gameRoot.setLayoutY(-(offset - (screenHeight / 2)));
                //backGround.setLayoutY(-(offset - (screenHeight /2)));
            }
        });
    }

    //adding all roots to the bottom one which is appRoot
    public void addAllToRoot(Pane gameRoot) {
        gameRoot.getChildren().add(playerSprite);
        for (ImageView trap : trapsSprites) {
            gameRoot.getChildren().add(trap);
        }
        for (ImageView socle : socles) {
            gameRoot.getChildren().add(socle);
        }
        appRoot.getChildren().addAll(backGround, gameRoot, uiRoot);
    }

}
