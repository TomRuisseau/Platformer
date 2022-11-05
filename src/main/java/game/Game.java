package game;

import game.traps.Laser;
import game.traps.RotatingSaw;
import game.traps.StaticSaw;
import game.traps.Trap;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Game {
    private final Player player;

    private final Level  level;
    private final Pane gameRoot = new Pane();

    private LevelData levelData;
    private long time = 0;

    private long respawnTime = 0;
    public Game() {

        this.player = new Player();
        this.level = new Level();
        this.levelData = new LevelData();
    }

    public Pane getGameRoot(){
        return gameRoot;
    }


    public long getRespawnTime() {
        return respawnTime;
    }

    public void preventPlayerFromJumping(){
        player.setCanJump(false);
    }

    public Point2D getPlayerVelocity(){
        return player.getVelocity();
    }

    public float getPlayerWidth(){
        return player.getWidth();
    }
    public float getPlayerHeight(){
        return player.getHeight();
    }


    public void addToPlayerVelocity(double x, double y){
        player.addVelocity( x,  y);
    }

    public boolean playerTouchingGround(){
        return player.isTouchingGround();
    }

    public boolean playerCanJump(){
        return player.isCanJump();
    }

    public Node getPlayerNode(){
        return player.getNode();
    }

    public float getLevelHeight(){
        return level.getHeight();
    }

    public void makePlayerMoveX(){
        player.moveX((int)getPlayerVelocity().getX(), level);
    }

    public void makePlayerMoveY(){
        player.moveY((int)getPlayerVelocity().getY(), level);
    }

    public void updateTraps(float screenHeight){
        if(level.updateTraps(time, player.getNode())){
            restart(screenHeight);
        }
    }

    public void initGame(float screenWidth, float screenHeight){

        level.setWidth(screenWidth * (float)3);
        level.setHeight(screenHeight * (float)1.5);

        level.setPlatformWidth(((float)1/((float)LevelData.getLevelDataWidth())) * level.getWidth());
        level.setPlatformHeight(((float)1/((float)LevelData.getLevelDataHeight())) * level.getHeight());

        gameRoot.setLayoutY(-level.getHeight()+ screenHeight);

        intiPlatforms();
        initTraps();

        Node playerNode = createPlatform(
                level.getPlatformWidth(),
                level.getHeight() -level.getPlatformHeight() - level.getPlatformHeight() * (float)0.6 - 1,
                level.getPlatformWidth() * (float)0.6 ,
                level.getPlatformHeight() * (float)0.6,
                Color.CORNFLOWERBLUE);

        player.initPlayer(playerNode,
                level.getPlatformWidth(),
                level.getPlatformHeight(),
                screenWidth, screenHeight,
                gameRoot,
                level.getWidth(),
                level.getHeight());


    }

    public void intiPlatforms(){
        for (int i = 0; i < LevelData.getLevelDataHeight(); i++){
            String platformLine = LevelData.getPlatformAt(i);
            for (int j = 0; j < platformLine.length(); j++){
                switch (platformLine.charAt(j)){
                    case '0' :
                        break;
                    case '1' :
                       level.addPlatform( createPlatform(
                               j*level.getPlatformWidth(),
                               i*level.getPlatformHeight(),
                               level.getPlatformWidth(),
                               level.getPlatformHeight(),
                               Color.DARKMAGENTA));
                       break;
                }
            }
        }
    }

    public void initTraps(){
        for (int i = 0; i < LevelData.getLevelDataHeight(); i++){
            String hazardLine = LevelData.getHazardAt(i);
            for (int j = 0; j < hazardLine.length(); j++){
                switch (hazardLine.charAt(j)){
                    case '0' :
                        break;
                    case '1':
                        StaticSaw newStaticSaw = new StaticSaw(j*level.getPlatformWidth(),  i* level.getPlatformHeight(), level.getPlatformHeight());
                        newStaticSaw.addNodesToRoot(gameRoot);
                        level.addTrap(newStaticSaw);
                        break;
                    case '2':
                        RotatingSaw newRotatingSaw = new RotatingSaw(j*level.getPlatformWidth(),  i*level.getPlatformHeight(), level.getPlatformHeight());
                        newRotatingSaw.addNodesToRoot(gameRoot);
                        level.addTrap(newRotatingSaw);
                        break;
                    case '3' :
                        Laser newLaser = new Laser(j*level.getPlatformWidth(),  i*level.getPlatformHeight(), level.getPlatformWidth(), level.getPlatformHeight());
                        newLaser.addNodesToRoot(gameRoot);
                        level.addTrap(newLaser);
                        break;
                }
            }
        }
    }

    public Node createPlatform(float x, float y, float w, float h, Color color){
        Rectangle entity = new Rectangle(w,h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);

        gameRoot.getChildren().add(entity);
        return entity;
    }
    public void updateTimes(){
        time++;
        respawnTime++;
    }

    public void makePlayerJump(){
        player.jump();
    }


    public void restart(float screenHeight){
        time = 0;
        respawnTime = 0;
        getPlayerNode().setTranslateX(level.getPlatformWidth());
        getPlayerNode().setTranslateY(level.getHeight() - level.getPlatformHeight() - level.getPlatformHeight() * (float)0.6 - 1);
        addToPlayerVelocity(-getPlayerVelocity().getX(),-getPlayerVelocity().getY());
        gameRoot.setLayoutX(0);
        gameRoot.setLayoutY(-level.getHeight() + screenHeight);


        for(Trap trap : level.getTraps()){
            trap.reset();
        }

    }

}
