package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;


public class Cloud implements GameObject{

    private Texture cloud;
    private SpriteBatch batch;
    private float screenWidth, screenHeight;
    private Random random;
    private CommandRecorder commandRecorder;
    private GameDirector gameDirector;

    private float cloudHeight,cloudWidth;
    private float[] xPositions;
    private float[] yPositions;
    private boolean removingClouds;


    public Cloud(float screenWidth, float screenHeight, SpriteBatch batch, GameDirector gameDirector){
        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
        this.batch = batch;
        this.gameDirector = gameDirector;
        cloudHeight=screenHeight*4/10;
        cloudWidth=screenWidth*7/10;
        random = new Random();
        cloud = new Texture("cloud.png");
        xPositions = new float[10];
        yPositions = new float[10];
        generatePos();
        removingClouds = false;
        commandRecorder = new CommandRecorder(this);
        commandRecorder.start();
    }

    private void generatePos(){
        for(int i =0; i<10; i++){
            xPositions[i] =  screenWidth*random.nextFloat()+500;
            yPositions[i] = screenHeight/(1+random.nextFloat())+500;
        }
    }

    //called from CommandRecorder
    public void removeClouds(){
        removingClouds = true;
    }

    //makes clouds move:)
    private void moveClouds(){
        int nbrOfCloudsPresent = 0;
        for(int i=0; i<10; i++){
            if(!removingClouds){
                float dice = random.nextFloat();
                if(dice<0.8){
                    xPositions[i] =  xPositions[i]-(float)2.5;
                    yPositions[i] =  yPositions[i]-(float)4;
                }/*else{
                    xPositions[i] =  xPositions[i]+1;
                    yPositions[i] = yPositions[i]+1;
                }*/
            }else{
                if((i)%2 == 0){
                    if(yPositions[i] <= screenHeight) {
                        nbrOfCloudsPresent++;
                    }
                    if(nbrOfCloudsPresent == 0) {
                        gameDirector.removeCloud(this);
                    }
                    xPositions[i] = (float) (xPositions[i] + ((i+1)*1.25));
                    yPositions[i] = (float) (yPositions[i] + ((i+1)*1.75));
                }else
                    xPositions[i] = (float) (xPositions[i] - ((i+1)*1.25));
                    yPositions[i] = (float) (yPositions[i] + ((i+1)*1.75));
            }
        }
    }

    @Override
    //draws the clouds
    public void draw() {
        batch.begin();
        for(int i=0; i<10; i++){
            batch.draw(cloud, xPositions[i] ,yPositions[i], cloudWidth, cloudHeight);
        }
        batch.end();
        moveClouds();
    }

    @Override
    public void dispose() {
        cloud.dispose();
    }
}
