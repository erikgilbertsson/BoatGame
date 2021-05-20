package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;


public class Cloud implements GameObject {

    private Texture cloud;
    private SpriteBatch batch;
    private Random random;
    private GameDirector gameDirector;
    private CommandRecorder commandRecorder;
    private boolean removingClouds;
    private float cloudHeight, cloudWidth, screenWidth, screenHeight;
    private float[] xPositions, yPositions;

    public Cloud(float screenWidth, float screenHeight, SpriteBatch batch, GameDirector gameDirector) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.batch = batch;
        this.gameDirector = gameDirector;
        cloud = new Texture("cloudsprite.png");
        cloudHeight = cloud.getHeight() * (screenWidth / 1000);
        cloudWidth = cloud.getWidth() * (screenWidth / 1000);
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
        int nbrOfCloudsPresent = 0;
        for (int i = 0; i < 10; i++) {
            if (!removingClouds) {
                float dice = random.nextFloat();
                if (dice < 0.8) {
                    xPositions[i] = xPositions[i] - (float) 2;
                    yPositions[i] = yPositions[i] - (float) 4;
                }
            } else { // mic detected sound, removing clouds
                if (yPositions[i] <= screenHeight && yPositions[i] >= -cloudHeight) { // cloud is on screen
                    nbrOfCloudsPresent++;
                }
                if ((i) % 2 == 0) {
                    xPositions[i] = (float) (xPositions[i] + ((i + 1) * 1.25));
                } else {
                    xPositions[i] = (float) (xPositions[i] - ((i + 1) * 1.25));
                }
                yPositions[i] = (float) (yPositions[i] + ((i + 1) * 1.75));
            }
        }
        if (removingClouds && nbrOfCloudsPresent == 0) {
            System.out.println("Removing");
            gameDirector.removeCloud(this);
        }
    }

    @Override
    //draws the clouds
    public void draw() {
        batch.begin();
        for (int i = 0; i < 10; i++) {
            batch.draw(cloud, xPositions[i], yPositions[i], cloudWidth, cloudHeight);
        }
        batch.end();
        moveClouds();
    }

    @Override
    public void dispose() {
        cloud.dispose();
    }
}
