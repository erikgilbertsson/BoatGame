package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.LinkedList;
import java.util.Random;

public class Wind implements GameObject {

    private static float MAX_FORCE = 0;
    private static final float MAX_FORCE_PRIME = (float) 0.005;

    private long windChangeTime;
    private long timer;

    private float xForcePrime;
    private float xForce;
    private Random r;

    private float screenWidth, screenHeight;
    private ShapeRenderer shapeRenderer;
    private float[] xPositions;
    private float[] yPositions;

    public Wind(float screenWidth, float screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        r = new Random();
        xForce = 0;
        windChangeTime = 0;
        timer = 5000;
        shapeRenderer = new ShapeRenderer();
        xPositions = new float[50];
        yPositions = new float[50];
        setWindStreakPos();
    }

    private void setWindStreakPos() {
        for(int i =0; i<xPositions.length; i++){
            xPositions[i] =  screenWidth*r.nextFloat();
        }
        for(int i =0; i<xPositions.length; i++){
            yPositions[i] =  screenHeight*r.nextFloat();
        }
    }

    public void setMaxForce(float force) {
        MAX_FORCE = force;
    }

    private void setXForcePrime() {
        xForcePrime = -MAX_FORCE_PRIME + r.nextFloat() * MAX_FORCE_PRIME * 2;
    }

    private void updateXForce() {
        xForce += xForcePrime;
        if (xForce > MAX_FORCE) {
            xForce = MAX_FORCE;
        } else if (xForce < -MAX_FORCE) {
            xForce = -MAX_FORCE;
        }
    }

    public float getXForce() {
        if (TimeUtils.timeSinceMillis(windChangeTime) > timer) {
            setXForcePrime();
            windChangeTime = TimeUtils.millis();
        }
        updateXForce();
        return xForce;
    }

    @Override
    public void draw() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        drawLines();
        shapeRenderer.end();
    }

    public void drawLines() {
        for(int i = 0; i<50; i++){
            float xPos = xPositions[i];
            float yPos = yPositions[i];
            shapeRenderer.line(xPos, yPos, xPos+100+xForce*20, yPos+xForce*10);
        }
        moveLines();
    }

    public void moveLines() {
        for(int i = 0; i<xPositions.length; i++){
            float xPos = xPositions[i];
            if(xPos < 0) {
                xPositions[i] = screenWidth;
            } else if(xPos > screenWidth) {
                xPositions[i] = 0;
            }
            xPositions[i] += xForce*10;
        }
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
