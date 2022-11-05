package game;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Player {
    private Node node;

    private Point2D velocity = new Point2D(0,0);

    private float width;
    private float height;
    private boolean canJump = true;

    private boolean isTouchingGround = false;

    public Node getNode() {
        return node;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void addVelocity(double x, double y){
       velocity = velocity.add(x,y);
    }

    public float getWidth() {
        return width;
    }


    public float getHeight() {
        return height;
    }

    public boolean isCanJump() {
        return canJump;
    }

    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }

    public boolean isTouchingGround() {
        return isTouchingGround;
    }


    public void initPlayer(Node node, float platformWidth, float platformHeight, float screenWidth, float screenHeight, Pane gameRoot, float levelWidth, float levelHeight){
        this.node = node;
        width = platformWidth * (float)0.6;
        height = platformHeight * (float)0.6;
        createListeners(screenWidth, screenHeight, gameRoot, levelWidth, levelHeight);

    }

    public void createListeners(float screenWidth, float screenHeight, Pane gameRoot, float levelWidth, float levelHeight){
        node.translateXProperty().addListener((obs, old, newValue) ->{
            float offset = newValue.floatValue();

            if(offset > (screenWidth /2) && offset <levelWidth - (screenWidth /2)){
                gameRoot.setLayoutX(-(offset - (screenWidth /2)));
            }
        });

        node.translateYProperty().addListener((obs, old, newValue) ->{
            float offset = newValue.floatValue();

            if(offset > (screenHeight /2) && offset < levelHeight - (screenHeight /2)){
                gameRoot.setLayoutY(-(offset - (screenHeight /2)));
            }
        });
    }

    public void moveX(int value, Level level){
        boolean movingRight = value > 0;
        for (int i = 0; i < Math.abs(value); i++){
            node.setTranslateX(node.getTranslateX() + (movingRight ? 1 : -1));
            for (Node platform : level.getPlatforms()){
                if(node.getBoundsInParent().intersects(platform.getBoundsInParent()) || node.getTranslateX() < 0 || node.getTranslateX() + width > level.getWidth()){
                    if(movingRight){
                        if(node.getTranslateX() + this.width>= platform.getTranslateX()){
                            node.setTranslateX(node.getTranslateX()  - 1);
                            return;
                        }
                    }
                    else{
                        if(node.getTranslateX() <= platform.getTranslateX() + level.getPlatformWidth()){
                            node.setTranslateX(node.getTranslateX() +  1);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void moveY(int value, Level level){
        boolean movingDown = value > 0;
        isTouchingGround = false;
        for (int i = 0; i < Math.abs(value); i++){
            node.setTranslateY(node.getTranslateY() + (movingDown ? 1 : -1));
            for (Node platform : level.getPlatforms()){
                if(node.getBoundsInParent().intersects(platform.getBoundsInParent())){
                    if(movingDown){
                        if(node.getTranslateY() + this.height >= platform.getTranslateY()){
                            node.setTranslateY(node.getTranslateY() - 1);
                            canJump = true;
                            isTouchingGround = true;
                            return;
                        }
                    }
                    else{
                        if(node.getTranslateY()<= platform.getTranslateY() + level.getPlatformHeight()){
                            node.setTranslateY(node.getTranslateY() + 1);
                            velocity = velocity.add(0,- velocity.getY());
                            canJump = false;
                            return;
                        }
                    }
                }
            }
        }
    }

    public void jump(){
        if(canJump){
            if(velocity.getY() > -height * 0.5){
                if (velocity.getY()>=-height * 0.2){
                    velocity = velocity.add(0, -height * 0.2);
                }
                else{
                    velocity = velocity.add(0, -height *0.08);
                }
            }

            else{
                canJump = false;
            }
        }
    }

}
