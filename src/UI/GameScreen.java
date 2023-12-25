package UI;

import objectGame.*;
import util.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;



public class GameScreen extends JPanel implements Runnable, KeyListener {

    public static final int GAME_FIRST_STATE = 0;
    public static final int GAME_PLAY_STATE = 1;
    public static final int GAME_OVER_STATE = 2;
    public static final float GRAVITY = 0.1f;
    public static final float GROUNDY = 110; // BY PIXEL
    private MainCharacter mainCharacter;
    private Thread thread;
    private Land land;
    private Clouds clouds;
    private EnemyManager enemyManager;

    private int gameState = GAME_FIRST_STATE;
    private BufferedImage imageGameOverText;
    private BufferedImage playAgainBtn;
    private int score;
    private boolean isJumping = false;
    private Cactus cactus;
    public GameScreen(){
        thread = new Thread(this);
        mainCharacter = new MainCharacter();
        clouds = new Clouds();
        mainCharacter.setX(45);
        mainCharacter.setY(50);
        land = new Land(this);
        enemyManager = new EnemyManager(mainCharacter, this);
        imageGameOverText = Resource.getResourceImage("images/gameover_text.png");
        playAgainBtn = Resource.getResourceImage("images/replay_button.png");
        cactus = new Cactus(mainCharacter);
    }



    public void startGame(){
        thread.start();
    }

    @Override
    public void run() {
         while(true){
             try{
                 update();
                 repaint();
                 Thread.sleep(20);
             }catch (InterruptedException e){
                 e.printStackTrace();
             }
         }
    }
    public void update(){
        switch (gameState){
            case GAME_PLAY_STATE:
                mainCharacter.update();
                land.update();
                clouds.update();
                enemyManager.update();
                if(!mainCharacter.getAlive()){
                    gameState = GAME_OVER_STATE;
                }
                break;
        }

    }

    public void plusScore(int score){
        this.score += score;
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.decode("#f7f7f7"));
        g.fillRect(0, 0, getWidth(), getHeight());

        switch (gameState){
            case GAME_FIRST_STATE:
                mainCharacter.draw(g);
                break;
            case GAME_PLAY_STATE:
                clouds.draw(g);
                land.draw(g);
                mainCharacter.draw(g);
                enemyManager.draw(g);
                g.drawString("HI Score " + String.valueOf(score), 500, 25);
                break;
            case GAME_OVER_STATE:
                clouds.draw(g);
                land.draw(g);
                mainCharacter.draw(g);
                enemyManager.draw(g);
                g.drawImage(imageGameOverText, 210, 35, null);
                g.drawImage(playAgainBtn, 285, 60, null);
                break;
        }
    }

    private void resetGame(){
        mainCharacter.setAlive(true);
        mainCharacter.setX(45);
        mainCharacter.setX(50);
        enemyManager.reset();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    public void setJumping(boolean jumping) {
        this.isJumping = jumping;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(cactus.isOver());
        switch(e.getKeyCode()){
            case KeyEvent.VK_SPACE:
                if(gameState == GAME_FIRST_STATE){
                    gameState = GAME_PLAY_STATE;
                }else if(gameState == GAME_PLAY_STATE){
                    mainCharacter.jump(e);
                    System.out.println(mainCharacter.getY());
                    System.out.println(cactus.isOver());
                    isJumping = true; // Set jumping to true when the character starts jumping
                }else if(gameState == GAME_OVER_STATE){
                    resetGame();
                    gameState = GAME_PLAY_STATE;
                }
                break;
        }
    }
}