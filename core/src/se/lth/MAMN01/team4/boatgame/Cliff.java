package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Cliff implements GameObject {

    private float xPos, yPos, size;
    private float screenWidth, screenHeight;
    private float MAX_SIZE, MIN_SIZE;
    private Random r;
    private Rectangle hitBox;
    private float hitBoxCorrection;
    private Texture rock, iceberg, shipwreck, chosenObstacle;
    private SpriteBatch batch;

    //private ShapeRenderer shapeRenderer;

    public Cliff(float screenWidth, float screenHeight, SpriteBatch batch) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.batch = batch;
        MAX_SIZE = screenWidth*1/3;
        MIN_SIZE = screenWidth*1/4;
        rock = new Texture("rock_2.png");
        iceberg = new Texture("iceberg.png");
        shipwreck = new Texture("shipwreck.png");
        r = new Random();
        hitBox = new Rectangle();
        resetPosition();
        //this.shapeRenderer = new ShapeRenderer();
    }

    private void resetPosition() {
        size = MIN_SIZE + r.nextFloat() * (MAX_SIZE-MIN_SIZE);
        xPos = -size/2 + r.nextFloat() * (screenWidth);
        yPos = screenHeight + r.nextFloat() * 350;
        hitBoxCorrection = size/3;
        hitBox.set(xPos, yPos, size-hitBoxCorrection, size-hitBoxCorrection);
        float rand = r.nextFloat();
        if(rand > 0.95) {
            chosenObstacle = shipwreck;
        } else if(rand > 0.7) {
            chosenObstacle = iceberg;
        } else {
            chosenObstacle = rock;
        }
    }

    private void move() {
        if(yPos < -size) {
            resetPosition();
        } else {
            yPos -= GameDirector.Y_SPEED;
        }
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void draw() {
        /*
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(xPos+hitBoxCorrection/2, yPos+hitBoxCorrection/2, size-hitBoxCorrection, size-hitBoxCorrection);
        shapeRenderer.end();
         */
        batch.begin();
        batch.draw(chosenObstacle, xPos, yPos, size, size);
        batch.end();
        hitBox.setPosition(xPos+hitBoxCorrection/2, yPos+hitBoxCorrection/2);
        move();
    }

    public void dispose() {
        //shapeRenderer.dispose();
        rock.dispose();
    }


}
