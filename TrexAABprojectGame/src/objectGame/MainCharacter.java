package objectGame;


import util.Animation;
import util.Resource;

import java.awt.*;
import java.awt.event.KeyEvent;


import static UI.GameScreen.GROUNDY;
import static UI.GameScreen.GRAVITY;

public class MainCharacter {
    private float x = 0; // the x-axis of the dinosaur
    private float y = 0; // the y-axis of the dinosaur
    private float speedY = 0; // the speed of the dinosaur
    private Animation characterRun;
    private Rectangle rect;
    private boolean isAlive = true; // check if the dinosaur is alive or not
    public boolean allowToJump = true; // Dino can jump by default

    public MainCharacter(){
        characterRun = new Animation(500);
        characterRun.addFrame(Resource.getResourceImage("images/main-character1.png"));
        characterRun.addFrame(Resource.getResourceImage("images/main-character2.png"));
        // This changes the dino from img 1 to img 2 at speed of 500 milliseconds
        rect = new Rectangle();
    }

    public void update(){
        characterRun.update();
        // This if checks if the height of the character has the same y-axis as the ground
        if(y >= GROUNDY - characterRun.getFrame().getHeight()){
            speedY = 0;
            y = GROUNDY - characterRun.getFrame().getHeight();
            allowToJump = true;
            //If dino is on the ground allow to  jump
        }else{
            // This means the dino is in the air so jumping
            speedY += GRAVITY;
            y += speedY;
            System.out.println("In the else");
            // So when the dino is on the air we don't allow it to jump again it should only jump when it's on the ground
            allowToJump = false;
        }
        rect.x = (int) x;
        rect.y = (int) y;
        rect.width = characterRun.getFrame().getWidth();
        rect.height = characterRun.getFrame().getHeight();
    }
    public Rectangle getBound(){
        return rect;
    }

    public void draw(Graphics g){
        g.setColor(Color.black);
        g.drawImage(characterRun.getFrame(), (int) x, (int) y, null);
    }

    public void jump(KeyEvent e){
        if(allowToJump){
            speedY = -4;
            y += speedY;
            System.out.println(y);
        }
    }

    public float getX(){
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
    public boolean getAlive(){
        return isAlive;
    }
    public void setAlive(boolean alive){
        isAlive = alive;
    }
}
