package objectGame;
import util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

import static objectGame.Clouds.posXMultiplier;

public class Cactus extends Enemy {
    private BufferedImage image;
    private int posX, posY;
    private Rectangle rect;
    private MainCharacter mainCharacter;
    private boolean isScoreGot = false;

    public Cactus(MainCharacter mainCharacter){
        this.mainCharacter = mainCharacter;
        image = Resource.getResourceImage("images/cactus1.png");
        posX = 200;
        posY = 65;
        rect = new Rectangle();
    }

    public void update(){
        posX -= posXMultiplier + 1;
        rect.x = posX;
        rect.y = posY;
        rect.width = image.getWidth();
        rect.height = image.getHeight();
    }

    @Override
    public Boolean isOutOfScreen() {
        return (posX + image.getWidth() < 0);
    }

    @Override
    public boolean isOver() {
        return (mainCharacter.getX() > posX);
    }

    @Override
    public boolean isScoreGot() {
        return isScoreGot;
    }

    @Override
    public void setIsScoreGot(boolean isScoreGot) {
        this.isScoreGot = isScoreGot;
    }

    @Override
    public Rectangle getBound(){
        return rect;
    }
    @Override
    public void draw(Graphics g){
        g.drawImage(image, posX, posY, null);
    }
    public void setX(int x){
        posX = x;
    }
    public void setY(int y){
        posY = y;
    }

    public void setImage(BufferedImage image){
        this.image = image;
    }
}
