package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;
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

    private boolean isStartingAnimation;

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

    public void setMaxForce(float force) {
        MAX_FORCE = force;
    }

    private void updateXForcePrime() {
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
        if (TimeUtils.timeSinceMillis(windChangeTime) > timer && !isStartingAnimation) {
            updateXForcePrime();
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

    private void setWindStreakPos() {
        for(int i =0; i<xPositions.length; i++){
            xPositions[i] =  screenWidth + screenWidth*r.nextFloat();
        }
        for(int i =0; i<xPositions.length; i++){
            yPositions[i] =  screenHeight*r.nextFloat();
        }
        isStartingAnimation = true;
        xForcePrime = (float)-0.005;
    }

    private void drawLines() {
        for(int i = 0; i<50; i++){
            float xPos = xPositions[i];
            float yPos = yPositions[i];
            float x2 = Math.abs(xForce) > 0 ? 100+xForce*20 : 0;
            shapeRenderer.line(xPos, yPos, xPos+x2, yPos+xForce*10);
        }
        moveLines();
    }

    private void moveLines() {
        for(int i = 0; i<xPositions.length; i++){
            float xPos = xPositions[i];
            if(xPos < -120) {
                xPositions[i] = screenWidth;
                isStartingAnimation = false;
            } else if(xPos > screenWidth && !isStartingAnimation) {
                xPositions[i] = -120;
            }
            xPositions[i] += xForce*10;
        }
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
