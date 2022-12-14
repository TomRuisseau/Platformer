package game;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Player {
    private Node node;

    //velocity of the character, decomposed between x and y value
    private Point2D velocity = new Point2D(0,0);

    private Point2D position = new Point2D(0,0);

    private float width;
    private float height;
    private boolean canJump = true;

    private boolean isTouchingGround = false;

    private float spriteRotate = 0;

    private ImageView back;

    public void setBack(ImageView back) {
        this.back = back;
    }

    public Node getNode() {
        return node;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void addVelocity(double x, double y){
       velocity = velocity.add(x,y);
    }

    public Point2D getPosition(){return position;}

    public void setPosition(Point2D position){this.position = position;}
    public void addPostition(double x, double y){position = position.add(x, y);}


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

    public float getSpriteRotate(){return spriteRotate;}



    //getting the player infos
    public void initPlayer(Node node, float platformWidth, float platformHeight){
        this.node = node;
        this.position.add(node.getTranslateX(), node.getTranslateY());
        width = platformWidth * (float)0.6;
        height = platformHeight * (float)0.6;
        //creating listeners used for the player
        //createListeners(screenWidth, screenHeight, gameRoot, levelWidth, levelHeight);

    }


    //make player move allong the x axes
    //value parameter is usally player x velocity
    public void moveX(int value, Level level){
        //checking which way the character intends to move
        boolean movingRight = value > 0;

        //try to make player move as much pixel as its x velocity
        for (int i = 0; i < Math.abs(value); i++){
            //make player move one pixel
            node.setTranslateX(node.getTranslateX() + (movingRight ? 1 : -1));
            spriteRotate += (movingRight ? 1 : -1) * 1.5;

            for (Node platform : level.getPlatforms()){//browsing all platforms
                if(node.getBoundsInParent().intersects(platform.getBoundsInParent()) || node.getTranslateX() < 0 || node.getTranslateX() + width > level.getWidth()){
                    //if player intersect with a platfoms, we cancel the last movement and exit the function
                    //this system simumate collisions
                    node.setTranslateX(node.getTranslateX() + (!movingRight ? 1 : -1));
                    spriteRotate += (!movingRight ? 1 : -1) * 1.5;
                    return;
                }
            }
        }
    }

    //make player move allong the y axes
    //value parameter is usally player y velocity
    public void moveY(int value, Level level){
        //checking which way the character intends to move
        boolean movingDown = value > 0;


        isTouchingGround = false;
        //try to make player move as much pixel as its x velocity
        for (int i = 0; i < Math.abs(value); i++){
            //make player move one pixel
            node.setTranslateY(node.getTranslateY() + (movingDown ? 1 : -1));

            for (Node platform : level.getPlatforms()){
                if(node.getBoundsInParent().intersects(platform.getBoundsInParent())){
                    //if player intersect with a platfoms, we cancel the last movement, update infos,  and exit the function
                    //this system simumate collisions

                    if(movingDown){//if player is moving down, and intersect a platfom, it means it's on the ground
                            //if on the ground, the character is allowed to jump
                            canJump = true;
                            isTouchingGround = true;
                    }
                    else{//if player intersect a platfom while moving up, it means its a "ceiling"
                            //we delete y velocity if player hit its head, to simulate collision
                            velocity = velocity.add(0,- velocity.getY());
                            canJump = false;
                    }

                    //canceling last movement
                    node.setTranslateY(node.getTranslateY() + (!movingDown ? 1 : -1));
                    return;
                }
            }
        }
    }

    //method to make character jump
    public void jump(){
        if(canJump){//checking if jumping is allowed

            //the behaviour is that character as a minimum jump
            //and then as long as the jump key is pressed, the character goes higher along time until a max value
            //it allows to have an analog jump

            if(velocity.getY() > -height * 0.5){//checking if max velocity is not yet reached
                if (velocity.getY()>=-height * 0.2){//initial higher jump
                    velocity = velocity.add(0, -height * 0.2);
                }
                else{//small increase in velocity as long as the key is pressed
                    velocity = velocity.add(0, -height *0.08);
                }
            }

            //if max height is reached, prevent from jumping
            else{
                canJump = false;
            }
        }
    }

}
