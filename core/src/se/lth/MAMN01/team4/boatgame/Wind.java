package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.Random;

public class Wind implements GameObject {

    private static float MAX_FORCE = 0;
    private static float MAX_FORCE_PRIME = 0;
    private static final long TIMER = 10000;
    private long windChangeTime;


    private float xForcePrime, xForce;
    private Random r;

    private float screenWidth, screenHeight;
    private ShapeRenderer shapeRenderer;
    private float[] xPositions, yPositions;

    private boolean isStartingAnimation;

    public Wind(float screenWidth, float screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        r = new Random();
        xForce = 0;
        windChangeTime = 0;
        shapeRenderer = new ShapeRenderer();
        xPositions = new float[50];
        yPositions = new float[50];
        setWindStreakPos();
    }

    public void setMaxForce(float force) {
        MAX_FORCE = force;
    }

    public float getMaxForce() {
        return MAX_FORCE;
    }

    private void updateXForcePrime() {
        MAX_FORCE_PRIME = MAX_FORCE*(float)0.01;
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
        if (TimeUtils.timeSinceMillis(windChangeTime) > TIMER && !isStartingAnimation) {
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
            float x2 = Math.abs(xForce) > 0 ? 80+Math.abs(xForce)*10 : 0;
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
