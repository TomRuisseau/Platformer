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

    private Rectangle backGround;

    private Text deathCount;

    private ImageView playerSprite;



    public Display() {

    }

    public Pane getAppRoot(){
        return appRoot;
    }

    //create graphic elements of the background and the ui
    public void initGraphism(float screenWidth, float screenHeight) {
        backGround = new Rectangle(screenWidth , screenHeight);
        Text text = new Text();
        //Setting the properties of text
        text.setText("Death : 0");
        text.setX(80);
        text.setY(40);
        text.setScaleX(3);
        text.setScaleY(3);
        text.setFill(Color.CADETBLUE);
        deathCount = text;
        uiRoot.getChildren().add(text);

        Image img = new Image("File:data/hamster-round.png");
        ImageView imgView = new ImageView();
        imgView.setImage(img);
        playerSprite = imgView;
    }

    public void setDeathCount(int deathNumbr){
        deathCount.setText("Death : " + deathNumbr);
    }

    public void updatePlayerSprite(Node player, float rotate){
        playerSprite.setTranslateX(player.getTranslateX());
        playerSprite.setTranslateY(player.getTranslateY());
        playerSprite.setRotate(rotate);
    }

    //adding all roots to the bottom one which is appRoot
    public void addAllToRoot(Pane gameRoot, float w, float h){
        playerSprite.setFitHeight((double)h);
        playerSprite.setFitWidth((double)w);
        gameRoot.getChildren().add(playerSprite);
        appRoot.getChildren().addAll(backGround,gameRoot, uiRoot);
    }

}
