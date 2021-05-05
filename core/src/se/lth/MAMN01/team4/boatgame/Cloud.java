package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Random;


public class Cloud implements GameObject{

    private Texture cloud;
    private SpriteBatch batch;
    private float screenWidth, screenHeight;
    private Random random;
    private float cloudHeight,cloudWidth;
    private float[] xPositions;
    private float[] yPositions;
    private boolean cloudsPresent;


    public Cloud(float screenWidth, float screenHeight){
        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
        random = new Random();
        cloud = new Texture("cloud.png");
        batch = new SpriteBatch();
        cloudHeight = this.cloud.getHeight();
        cloudWidth = this.cloud.getWidth();
        xPositions = new float[10];
        yPositions = new float[10];
        cloudsPresent = true;
        //kan göra en point med två kordinater.. men vafan
        XPos();
        YPos();

    }

    //called every time the clouds are drawn, records audio
    private void removeClouds(){
        if(cloudsPresent) {
            CloudThread cloudThread = new CloudThread();
            cloudThread.start();
        }
    }


    //makes clouds move:)
    private void moveClouds(){
        for(int i=0; i<10; i++){
            float dice = random.nextFloat();
                    if(dice<0.3){
                        xPositions[i] = xPositions[i]+random.nextFloat();
                        yPositions[i] = yPositions[i]+random.nextFloat();
                    }else{
                        xPositions[i] = xPositions[i]-random.nextFloat();
                        yPositions[i] = yPositions[i]-random.nextFloat();
                    }
        }
    }
    //Xpos
    public void XPos(){
        for(int i =0; i<10; i++){
            xPositions[i] =  screenWidth*random.nextFloat();
        }
    }
    //Ypos
    public void YPos(){
        for(int i =0; i<10; i++){
            yPositions[i] = screenHeight/(1+random.nextFloat());
        }
    }

    @Override
    //draws the clouds
    public void draw() {
        batch.begin();
        for(int i=0; i<10; i++){
            batch.draw(cloud, xPositions[i] ,yPositions[i]);
        }
        batch.end();
        moveClouds();
        cloudsPresent = true;
    }

    @Override
    public void dispose() {
        batch.dispose();
        cloud.dispose();
    }
}
