package game;

import javafx.geometry.Point2D;
import javafx.scene.Node;

public class Player {
    public Node playerNode;

    public Point2D playerVelocity = new Point2D(0,0);

    public float width;
    public float height;
    public boolean canJump = true;



    public void moveX(int value, Level level){
        boolean movingRight = value > 0;

        for (int i = 0; i < Math.abs(value); i++){
            for (Node platform : level.platforms){
                if(playerNode.getBoundsInParent().intersects(platform.getBoundsInParent())){
                    if(movingRight){
                        if(playerNode.getTranslateX() + this.width >= platform.getTranslateX()){
                            return;
                        }
                    }
                    else{
                        if(playerNode.getTranslateX() <= platform.getTranslateX() + level.platformWidth){
                            return;
                        }
                    }
                }
            }
            playerNode.setTranslateX(playerNode.getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    public void moveY(int value, Level level){
        boolean movingDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++){
            for (Node platform : level.platforms){
                if(playerNode.getBoundsInParent().intersects(platform.getBoundsInParent())){
                    if(movingDown){
                        if(playerNode.getTranslateY() + this.height >= platform.getTranslateY()){
                            playerNode.setTranslateY(playerNode.getTranslateY() - 1);
                            canJump = true;
                            return;
                        }
                    }
                    else{
                        if(playerNode.getTranslateY() <= platform.getTranslateY() + level.platformHeight){
                            return;
                        }
                    }
                }
            }
            playerNode.setTranslateY(playerNode.getTranslateY() + (movingDown ? 1 : -1));
        }
    }

    public void jump(){
        if(canJump){
            playerVelocity = playerVelocity.add(0, -30);
            canJump = false;
        }
    }

}
