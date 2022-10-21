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
            playerNode.setTranslateX(playerNode.getTranslateX() + (movingRight ? 1 : -1));
            for (Node platform : level.platforms){
                if(playerNode.getBoundsInParent().intersects(platform.getBoundsInParent())){
                    if(movingRight){
                        if(playerNode.getTranslateX() + this.width>= platform.getTranslateX()){
                            playerNode.setTranslateX(playerNode.getTranslateX() + -1);
                            return;
                        }
                    }
                    else{
                        if(playerNode.getTranslateX() <= platform.getTranslateX() + level.platformWidth){
                            playerNode.setTranslateX(playerNode.getTranslateX() +  1);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void moveY(int value, Level level){
        boolean movingDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++){
            playerNode.setTranslateY(playerNode.getTranslateY() + (movingDown ? 1 : -1));
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
                        if(playerNode.getTranslateY()<= platform.getTranslateY() + level.platformHeight){
                            playerNode.setTranslateY(playerNode.getTranslateY() + 1);
                            playerVelocity = playerVelocity.add(0,- playerVelocity.getY());
                            canJump = false;
                            return;
                        }
                    }
                }
                //else{
                    //canJump = false;
                //}
            }
        }
    }

    public void jump(){
        if(canJump){
            if(playerVelocity.getY() > -30){
                if (playerVelocity.getY()>=-10){
                    playerVelocity = playerVelocity.add(0, -11);
                }
                else{
                    playerVelocity = playerVelocity.add(0, -4);
                }
            }

            else{
                canJump = false;
            }
        }
    }

}
