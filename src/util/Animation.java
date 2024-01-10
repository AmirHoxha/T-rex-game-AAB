package util;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import static objectGame.Clouds.posXMultiplier;

public class Animation {
    private List<BufferedImage> frames;
    private int frameIndex = 0;
    private int deltaTime;
    private long previousTime;
    boolean test = true;
    int previousValue = 1;

    public Animation(int deltaTime){
        this.deltaTime = deltaTime;

        frames = new ArrayList<BufferedImage>();
    }

    public void update(){
        if(System.currentTimeMillis() - previousTime > deltaTime){
            frameIndex++;
            if(frameIndex >= frames.size()){
                frameIndex = 0;
            }
            previousTime = System.currentTimeMillis();
        }
        for (int i = 0; i < 9; i++) {
            // Check if the variable increments by 1
            if (posXMultiplier - 1 == previousValue) {
                deltaTime -= 50;
            }
            // Update the previous value for the next iteration
            previousValue = posXMultiplier;
        }
    }

    public void addFrame(BufferedImage frame){
        frames.add(frame);
    }

    public BufferedImage getFrame(){
        if(frames.size() > 0){
            return frames.get(frameIndex);
        }
        return null;
    }
}
