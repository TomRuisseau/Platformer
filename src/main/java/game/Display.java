package game;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Display {
    public Game game;

    public Pane appRoot = new Pane();

    public Pane uiRoot = new Pane();

    public Display(Game game) {
        this.game = game;
    }

    public void initContent() {
        Rectangle bg = new Rectangle(1280, 720);

        game.level.levelWidth = LevelData.LEVEL1[0].length() * 60;

        for (int i = 0; i < LevelData.LEVEL1.length; i++){
            String line = LevelData.LEVEL1[i];
            for (int j = 0; j < line.length(); j++){
                switch (line.charAt(j)){
                    case '0' :
                        break;
                    case '1' :
                        Node platform = createEntity(j*60, i*60, 60, 60, Color.BROWN);
                        game.level.platforms.add(platform);
                        break;
                }
            }
        }

        game.player.playerNode = createEntity(0, 600, 40, 40, Color.BLUE);

        game.player.playerNode.translateXProperty().addListener((obs,old,newValue) ->{
            int offset = newValue.intValue();

            if(offset > 640 && offset < game.level.levelWidth - 640){
                game.gameRoot.setLayoutX(-(offset - 640));
            }
        });

        appRoot.getChildren().addAll(bg,game.gameRoot, uiRoot);
    }
    public Node createEntity(int x, int y, int w, int h, Color color){
        Rectangle entity = new Rectangle(w,h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);

        game.gameRoot.getChildren().add(entity);
        return entity;
    }
}
