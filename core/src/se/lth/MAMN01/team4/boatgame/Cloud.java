package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;


public class Cloud implements GameObject {

    private Texture cloud;
    private Texture tip;
    private SpriteBatch batch;
    private Random random;
    private GameDirector gameDirector;
    private CommandRecorder commandRecorder;
    private boolean removingClouds;
    private float cloudHeight, cloudWidth, screenWidth, screenHeight, tipHeight, tipWidth;
    private float[] xPositions, yPositions;

    public Cloud(float screenWidth, float screenHeight, SpriteBatch batch, GameDirector gameDirector) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.batch = batch;
        this.gameDirector = gameDirector;
        cloud = new Texture("cloudsprite.png");
        tip = new Texture("tip.png");
        cloudHeight = cloud.getHeight() * (screenWidth / 1000);
        cloudWidth = cloud.getWidth() * (screenWidth / 1000);
        tipWidth = tip.getWidth() * (screenWidth / 1200);
        tipHeight = tip.getHeight() * (screenWidth / 1200);
        random = new Random();
        xPositions = new float[10];
        yPositions = new float[10];
        generatePos();
        removingClouds = false;
        commandRecorder = new CommandRecorder(this);
        commandRecorder.start();
    }

    private void generatePos() {
        for (int i = 0; i < 10; i++) {
            xPositions[i] = screenWidth * random.nextFloat() + cloudWidth/2;
            yPositions[i] = screenHeight + random.nextFloat()*1000;
        }
    }

    //called from CommandRecorder
    public void removeClouds() {
        removingClouds = true;
    }

    //makes clouds move:)
    private void moveClouds() {
        int nbrOfCloudsOver = 0;
        int nbrOfCloudsUnder = 0;
        float xPrim = 0;
        float yPrim = 0;
        for (int i = 0; i < 10; i++) {
            if (yPositions[i] > screenHeight) { // cloud is on screen
                nbrOfCloudsOver++;
            } else if (yPositions[i] < -cloudHeight) {
                nbrOfCloudsUnder++;
            }
            if (!removingClouds) {
                float dice = random.nextFloat();
                if (dice < 0.8) {
                    xPrim = -(float) 2;
                    yPrim = -(float) 4;
                } else {
                    xPrim = 0;
                    yPrim = 0;
                }
            } else { // mic detected sound, removing clouds
                yPrim = (float) ((i + 1) * 1.75);
                if ((i) % 2 == 0) {
                    xPrim = (float) ((i + 1) * 1.25);
                } else {
                    xPrim = -(float) ((i + 1) * 1.25);
                }
            }
            xPositions[i] = xPositions[i] + xPrim;
            yPositions[i] = yPositions[i] + yPrim;
        }
            if(nbrOfCloudsOver >= 10 && yPrim > 0) {
                commandRecorder.terminate();
                gameDirector.removeCloud(this);
            } else if (nbrOfCloudsUnder >= 10 && yPrim < 0) {
                commandRecorder.terminate();
                gameDirector.removeCloud(this);
            }
    }

    private void displaytip(float x, float y) {
        if(!removingClouds && GameDirector.SHOW_TIPS && yPositions[5] < screenHeight*2/3) {
            batch.draw(tip, 10, screenHeight - tipHeight - 20, tipWidth, tipHeight);
        }
    }

    @Override
    //draws the clouds
    public void draw() {
        batch.begin();
        for (int i = 0; i < 10; i++) {
            batch.draw(cloud, xPositions[i], yPositions[i], cloudWidth, cloudHeight);
        }
        displaytip(0,0);
        batch.end();
        moveClouds();
    }

    @Override
    public void dispose() {
        cloud.dispose();
    }
}
