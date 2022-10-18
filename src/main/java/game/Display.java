package game;

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

        game.level.width =  screenWidth * (float)2;
        game.level.height = screenHeight * (float)1;

        game.level.platformWidth =  ((float)1/((float)LevelData.LEVEL1[0].length())) * game.level.width;
        game.level.platformHeight=  ((float)1/((float)LevelData.LEVEL1.length)) * game.level.height;

        for (int i = 0; i < LevelData.LEVEL1.length; i++){
            String line = LevelData.LEVEL1[i];
            for (int j = 0; j < line.length(); j++){
                switch (line.charAt(j)){
                    case '0' :
                        break;
                    case '1' :
                        Node platform = createEntity(j*game.level.platformWidth,  i*game.level.platformHeight, game.level.platformWidth,  game.level.platformHeight, Color.DARKMAGENTA);
                        game.level.platforms.add(platform);
                        break;
                }
            }
        }

        game.player.playerNode = createEntity(0, 600, game.level.platformWidth * (float)0.6, game.level.platformHeight * (float)0.6, Color.CORNFLOWERBLUE);
        game.player.width = game.level.platformWidth * (float)0.6;
        game.player.height = game.level.platformHeight * (float)0.6;

        game.player.playerNode.translateXProperty().addListener((obs,old,newValue) ->{
            int offset = newValue.intValue();

            if(offset > 640 && offset < game.level.width - 640){
                game.gameRoot.setLayoutX(-(offset - 640));
            }
        });

        appRoot.getChildren().addAll(bg,game.gameRoot, uiRoot);
    }
    public Node createEntity(float x, float y, float w, float h, Color color){
        Rectangle entity = new Rectangle(w,h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);

        game.gameRoot.getChildren().add(entity);
        return entity;
    }
}
