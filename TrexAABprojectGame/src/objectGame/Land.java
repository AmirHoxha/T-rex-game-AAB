package objectGame;

import UI.GameScreen;
import util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static UI.GameScreen.GROUNDY;

public class Land {
    private List<ImageLand> listImage;
    private BufferedImage imageLand1, imageLand2, imageLand3;
    private Random random;

    public Land(GameScreen game){
        random = new Random();
        imageLand1 = Resource.getResourceImage("images/land1.png");
        imageLand2 = Resource.getResourceImage("images/land2.png");
        imageLand3 = Resource.getResourceImage("images/land3.png");
        listImage = new ArrayList<ImageLand>();
        int numberOfLandTitle = 600 / imageLand1.getWidth() + 3;
        for(int i = 0; i < numberOfLandTitle; i++){
            ImageLand imageLand = new ImageLand();
            imageLand.posX = (int) (i * imageLand1.getWidth());
            imageLand.image = getImageLand();
            listImage.add(imageLand);
        }
    }

    public void update(){
        for(ImageLand imageLand : listImage){
            imageLand.posX -= 2;
            // this sets the speed of the land
        }
        ImageLand firstElement = listImage.get(0);
        if(listImage.get(0).posX + imageLand1.getWidth() < 0){
            firstElement.posX = listImage.get(listImage.size() - 1).posX + imageLand1.getWidth();
            listImage.add(firstElement);
            listImage.remove(0);
        }
    }

    public void draw(Graphics g){
        for(ImageLand imageLand:listImage){
            g.drawImage(imageLand.image, imageLand.posX, (int) GROUNDY - 20, null);
        }

    }

    private BufferedImage getImageLand(){
        // we do this to show image land 2 for the majority of the time
        int i = random.nextInt(9);
        switch(i){
            case 0: return  imageLand1;
            case 1: return  imageLand3;
            default: return imageLand2;
        }
    }

    private class ImageLand{
        int posX;
        BufferedImage image;
    }
}
