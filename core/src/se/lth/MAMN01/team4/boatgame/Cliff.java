package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Cliff implements GameObject {

    private static final float MAX_SIZE = 350;
    private static final float MIN_SIZE = 200;

    private float xPos, yPos, size;
    private float screenWidth, screenHeight;
    private Random r;
    private Rectangle hitBox;
    private float hitBoxCorrection;
    private Texture rock;
    private SpriteBatch batch;

    //private ShapeRenderer shapeRenderer;

    public Cliff(float screenWidth, float screenHeight, SpriteBatch batch) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.batch = batch;
        rock = new Texture("rock_2.png");
        r = new Random();
        hitBox = new Rectangle();
        resetPosition();
        //this.shapeRenderer = new ShapeRenderer();
    }

    private void resetPosition() {
        size = MIN_SIZE + r.nextFloat() * (MAX_SIZE-MIN_SIZE);
        xPos = r.nextFloat() * (screenWidth - size);
        yPos = screenHeight;
        hitBoxCorrection = size/3;
        hitBox.set(xPos, yPos, size-hitBoxCorrection, size-hitBoxCorrection);
    }

    private void move() {
        if(yPos < -200) {
            resetPosition();
        } else {
            yPos -= GameDirector.Y_SPEED;
        }
    }

    public void draw() {
        /*
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(xPos+hitBoxCorrection/2, yPos+hitBoxCorrection/2, size-hitBoxCorrection, size-hitBoxCorrection);
        shapeRenderer.end();
         */

        batch.begin();
        batch.draw(rock, xPos, yPos, size, size);
        batch.end();
        hitBox.setPosition(xPos+hitBoxCorrection/2, yPos+hitBoxCorrection/2);
        move();
    }

    public void dispose() {
        //shapeRenderer.dispose();
        rock.dispose();
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}
