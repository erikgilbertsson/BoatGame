package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class Cliff implements GameObject {

    private static final float MAX_RADIUS = 150;
    private static final float MIN_RADIUS = 80;

    private float xPos, yPos, radius;
    private float screenWidth, screenHeight;
    private Random r;

    private ShapeRenderer shapeRenderer;

    public Cliff(float screenWidth, float screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        r = new Random();
        resetPosition();
        this.shapeRenderer = new ShapeRenderer();
    }

    private void move() {
        if(yPos < -radius) {
            resetPosition();
            yPos = screenHeight;
        } else {
            yPos -= 10;
        }
    }

    private void resetPosition() {
        xPos = r.nextFloat() * screenWidth;
        yPos = screenHeight;
        radius = MIN_RADIUS + r.nextFloat() * (MAX_RADIUS-MIN_RADIUS);
    }

    public void draw() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.circle(xPos, yPos, radius);
        shapeRenderer.end();
        move();
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
