package game;

import game.traps.RotatingSaw;
import game.traps.StaticSaw;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Display {
    public Game game;

    public Pane appRoot = new Pane();

    public Pane uiRoot = new Pane();

    public float screenWidth;
    public float screenHeight;
    public Display(Game game) {
        this.game = game;
    }

    public void initContent(Stage primaryStage) {
        screenWidth =  (float)primaryStage.getWidth();
        screenHeight = (float) primaryStage.getHeight();
        Rectangle bg = new Rectangle(screenWidth , screenHeight);

        game.level.width =  screenWidth * (float)3;
        game.level.height = screenHeight * (float)1.5;

        game.level.platformWidth =  ((float)1/((float)LevelData.LEVEL1_PATFORMS[0].length())) * game.level.width;
        game.level.platformHeight=  ((float)1/((float)LevelData.LEVEL1_PATFORMS.length)) * game.level.height;

        game.gameRoot.setLayoutY(-game.level.height + screenHeight);

        for (int i = 0; i < LevelData.LEVEL1_PATFORMS.length; i++){
            String platformLine = LevelData.LEVEL1_PATFORMS[i];
            String hazardLine = LevelData.LEVEL1_HAZARDS[i];
            for (int j = 0; j < platformLine.length(); j++){
                switch (platformLine.charAt(j)){
                    case '0' :
                        break;
                    case '1' :
                        Node platform = createPlatform(j*game.level.platformWidth,  i*game.level.platformHeight, game.level.platformWidth,  game.level.platformHeight, Color.DARKMAGENTA);
                        game.level.platforms.add(platform);
                        break;
                }

                switch (hazardLine.charAt(j)){
                    case '0' :
                        break;
                    case '1':
                        game.level.traps.add(new StaticSaw(j*game.level.platformWidth,  i*game.level.platformHeight, game));
                        break;
                    case '2':
                        game.level.traps.add(new RotatingSaw(j*game.level.platformWidth,  i*game.level.platformHeight, game));
                        break;
                }
            }
        }

        game.player.playerNode = createPlatform(game.level.platformWidth, game.level.height - (2 * game.level.platformHeight), game.level.platformWidth * (float)0.6, game.level.platformHeight * (float)0.6, Color.CORNFLOWERBLUE);
        game.player.width = game.level.platformWidth * (float)0.6;
        game.player.height = game.level.platformHeight * (float)0.6;

        game.player.playerNode.translateXProperty().addListener((obs,old,newValue) ->{
            float offset = newValue.floatValue();

            if(offset > (screenWidth /2) && offset < game.level.width - (screenWidth /2)){
                game.gameRoot.setLayoutX(-(offset - (screenWidth /2)));
            }
        });

        game.player.playerNode.translateYProperty().addListener((obs,old,newValue) ->{
            float offset = newValue.floatValue();

            if(offset > (screenHeight /2) && offset < game.level.height - (screenHeight /2)){
                game.gameRoot.setLayoutY(-(offset - (screenHeight /2)));
            }
        });



        game.primaryStage = primaryStage;
        game.display = this;
        appRoot.getChildren().addAll(bg,game.gameRoot, uiRoot);
    }
    public Node createPlatform(float x, float y, float w, float h, Color color){
        Rectangle entity = new Rectangle(w,h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);

        game.gameRoot.getChildren().add(entity);
        return entity;
    }
}
