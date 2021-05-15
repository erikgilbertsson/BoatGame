package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;


public class Cloud implements GameObject{

    private static final int SAMPLING_RATE = 44100;

    private Texture cloud;
    private SpriteBatch batch;
    private float screenWidth, screenHeight;
    private Random random;
    CommandRecorder commandRecorder;

    private float cloudHeight,cloudWidth;
    private float[] xPositions;
    private float[] yPositions;
    private boolean cloudsNotPresent;
    private boolean moving = true;


    public Cloud(float screenWidth, float screenHeight, SpriteBatch batch){
        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
        random = new Random();
        cloud = new Texture("cloud.png");
        this.batch = batch;
        xPositions = new float[10];
        yPositions = new float[10];
        cloudsNotPresent = true;
        commandRecorder = new CommandRecorder(this);
        //kan göra en point med två kordinater.. men vafan
        xPos();
        yPos();
        commandRecorder.start();
    }

    //called every time the clouds are drawn, records audio
    public void removeClouds(){
        System.out.println("removing");
        cloudsNotPresent = false;
    }
    //makes clouds move:)
    private void moveClouds(){
        for(int i=0; i<10; i++){
            if(cloudsNotPresent){
                float dice = random.nextFloat();
                if(dice<0.3){
                    xPositions[i] = xPositions[i]+random.nextFloat();
                    yPositions[i] = yPositions[i]+random.nextFloat();
                }else{
                    xPositions[i] = xPositions[i]-random.nextFloat();
                    yPositions[i] = yPositions[i]-random.nextFloat();
                }
            }else{
                xPositions[i] = (float) (xPositions[i] + (i*1.25));
                yPositions[i] = (float) (yPositions[i] + (i*1.75));
            }

        }
    }

    //Xpos
    private void xPos(){
        for(int i =0; i<10; i++){
            xPositions[i] =  screenWidth*random.nextFloat();
        }
    }
    //Ypos
    private void yPos(){
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
    }

    @Override
    public void dispose() {
        batch.dispose();
        cloud.dispose();
    }
}
