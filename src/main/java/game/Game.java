package game;

import game.traps.Laser;
import game.traps.RotatingSaw;
import game.traps.StaticSaw;
import game.traps.Trap;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Game {
    private final Player player;
    private final Level  level;

    //root containing all of the game nodes
    private final Pane gameRoot = new Pane();


    //object used to read the files and containing all the read datas
    private LevelData levelData;
    private long time = 0;

    private long respawnTime = 0;

    public Game() {

        this.player = new Player();
        this.level = new Level();

        //reading the files by instantiating levelData
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

    //adding values to the player velocity
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

    public void isTouchingDoor(){
        Node node = level.getDoor();
        if(getPlayerNode().getBoundsInParent().intersects(node.getBoundsInParent())){
            System.out.println("gagné");
        }
    }

    //make level update all traps, and restarting the game if player is colliding with one of them
    public void updateTraps(float screenHeight){
        isTouchingDoor();
        if(level.updateTraps(time, player.getNode())){
            restart(screenHeight);
        }
    }

    //initiating all game informations
    public void initGame(float screenWidth, float screenHeight){

        //choosing the ration to decide how much of the level shows on screen
        level.setWidth(screenWidth * (float)3);
        level.setHeight(screenHeight * (float)1.5);

        //finding the proportional size of platforms to fill the level
        level.setPlatformWidth(((float)1/((float)LevelData.getLevelDataWidth())) * level.getWidth());
        level.setPlatformHeight(((float)1/((float)LevelData.getLevelDataHeight())) * level.getHeight());

        //placing the camera on the player
        gameRoot.setLayoutY(-level.getHeight()+ screenHeight);

        //creating platforms and traps
        intiPlatforms();
        initTraps();

        //creating player node .6 time smaller than a platform and placing it
        Node playerNode = createPlayer(
                level.getPlatformWidth(),
                level.getHeight() -level.getPlatformHeight() - level.getPlatformHeight() * (float)0.6 - 1,
                level.getPlatformWidth() * (float)0.6 ,
                level.getPlatformHeight() * (float)0.6);

        //initializing infos of the player
        player.initPlayer(playerNode,
                level.getPlatformWidth(),
                level.getPlatformHeight(),
                screenWidth, screenHeight,
                gameRoot,
                level.getWidth(),
                level.getHeight());


    }

    //method reading level data to create all the platforms
    public void intiPlatforms(){
        //browse all acquired level platformes data
        for (int i = 0; i < LevelData.getLevelDataHeight(); i++){
            String platformLine = LevelData.getPlatformAt(i);
            for (int j = 0; j < platformLine.length(); j++){

                //parsing read datas
                switch (platformLine.charAt(j)){
                    case '0' ://if 0 do nothing
                        break;
                    case '1' ://if 1 create a platform here
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
        //browse all acquired level traps data
        for (int i = 0; i < LevelData.getLevelDataHeight(); i++){
            String hazardLine = LevelData.getHazardAt(i);
            for (int j = 0; j < hazardLine.length(); j++){

                //parsing read datas
                switch (hazardLine.charAt(j)){
                    case '0' ://if 0 do nothing
                        break;
                    case '1'://if 1, create a static saw here
                        StaticSaw newStaticSaw = new StaticSaw(j*level.getPlatformWidth(),  i* level.getPlatformHeight(), level.getPlatformHeight());
                        newStaticSaw.addNodesToRoot(gameRoot);
                        level.addTrap(newStaticSaw);
                        break;
                    case '2'://if 2, create a rotating saw here
                        RotatingSaw newRotatingSaw = new RotatingSaw(j*level.getPlatformWidth(),  i*level.getPlatformHeight(), level.getPlatformHeight());
                        newRotatingSaw.addNodesToRoot(gameRoot);
                        level.addTrap(newRotatingSaw);
                        break;
                    case '3' ://if 3, create a vertical laser here
                        Laser newLaser = new Laser(j*level.getPlatformWidth(),  i*level.getPlatformHeight(), level.getPlatformWidth(), level.getPlatformHeight());
                        newLaser.addNodesToRoot(gameRoot);
                        level.addTrap(newLaser);
                        break;
                    case '4' : //if 4, create a door
                        Node myDoor = level.newDoor(j*level.getPlatformWidth(),i*level.getPlatformHeight(),level.getPlatformWidth(),level.getPlatformHeight()*2);
                        gameRoot.getChildren().add(myDoor);

                }
            }
        }
    }

    //methode used to create the palyer node
    //parameters are its position and size
    public Node createPlayer(float x, float y, float w, float h){

        Image img = new Image("File:data/squarePink.jpg");
        ImageView imgView = new ImageView();
        imgView.setImage(img);
        imgView.setFitHeight((double)h);
        imgView.setFitWidth((double)w);

        imgView.setTranslateX(x);
        imgView.setTranslateY(y);

        gameRoot.getChildren().add(imgView);
        return imgView;
    }

    //methode used to create a single platform
    //parameters are its position, size and color
    public Node createPlatform(float x, float y, float w, float h, Color color){
        //creating the node
        Rectangle entity = new Rectangle(w,h);

        //placing and coloring it
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);

        //adding the node to the scene
        gameRoot.getChildren().add(entity);
        return entity;
    }

    //update all timers, 60 ticks per second
    public void updateTimes(){
        time++;
        respawnTime++;
    }

    public void makePlayerJump(){
        player.jump();
    }

    //method which restart the game
    //only parameters is screenHeight used to replace the camera
    public void restart(float screenHeight){
        //reset timers
        time = 0;
        respawnTime = 0;

        //replacing the player at the spawn point
        getPlayerNode().setTranslateX(level.getPlatformWidth());
        getPlayerNode().setTranslateY(level.getHeight() - level.getPlatformHeight() - level.getPlatformHeight() * (float)0.6 - 1);

        //deleting player momentum
        addToPlayerVelocity(-getPlayerVelocity().getX(),-getPlayerVelocity().getY());

        //reseting the camera
        gameRoot.setLayoutX(0);
        gameRoot.setLayoutY(-level.getHeight() + screenHeight);

        //reset all the traps
        for(Trap trap : level.getTraps()){
            trap.reset();
        }

    }

    void frictionLeft(){
        if(this.playerTouchingGround()){//friction on the ground
            this.addToPlayerVelocity(this.getPlayerWidth()* 0.25 * 0.05, 0);

            if(this.getPlayerVelocity().getX() > 0){//prevent friction from forcing player to make a U turn
                this.addToPlayerVelocity(-this.getPlayerVelocity().getX(), 0);
            }
        }
        else{//friction in the air
            this.addToPlayerVelocity(this.getPlayerWidth() * 0.25 * 0.02, 0);
        }
    }

    void frictionRight(){
        if(this.playerTouchingGround()){//friction on the ground
            this.addToPlayerVelocity(-this.getPlayerWidth()* 0.25 * 0.05, 0);

            if(this.getPlayerVelocity().getX() < 0){//prevent friction from forcing player to make a U turn
                this.addToPlayerVelocity(-this.getPlayerVelocity().getX(), 0);
            }
        }
        else{//friction in the air
            this.addToPlayerVelocity(-this.getPlayerWidth() * 0.25 * 0.02, 0);
        }
    }

    void gravity(){
        if (this.getPlayerVelocity().getY()  < this.getPlayerHeight() * 0.3 && !this.playerCanJump()){
            this.addToPlayerVelocity(0, this.getPlayerHeight() * 0.03);
        }
    }

    void isPlayerOutOfScreen(float screenHeight){
        if(this.getPlayerNode().getTranslateY() >= this.getLevelHeight()){
            this.restart(screenHeight);
        }
    }
}
