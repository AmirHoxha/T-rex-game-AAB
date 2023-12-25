package UI;

import javax.swing.*;

public class GameWindow extends JFrame {

    private GameScreen gameScreen;
    public GameWindow(){
        super("Java T-Rex game");
        setSize(600, 175);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen = new GameScreen();
        add(gameScreen);
        addKeyListener(gameScreen);
    }

    public void startGame(){
        gameScreen.startGame();
    }

    public static void main(String[] args) {
        GameWindow gw = new GameWindow();
        gw.setVisible(true);
        gw.startGame();
    }

}
