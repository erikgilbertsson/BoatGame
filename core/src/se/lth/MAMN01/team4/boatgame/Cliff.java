package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Cliff implements GameObject {

    private static final float MAX_SIZE = 500;
    private static final float MIN_SIZE = 200;
    private static final float SPEED = 10;

    private float xPos, yPos, size;
    private float screenWidth, screenHeight;
    private Random r;
    private Rectangle hitBox;
    private Texture rock;
    private SpriteBatch batch;

    //private ShapeRenderer shapeRenderer;

    public Cliff(float screenWidth, float screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        batch = new SpriteBatch();
        rock = new Texture("rock_2.png");
        r = new Random();
        hitBox = new Rectangle();
        resetPosition();
        //this.shapeRenderer = new ShapeRenderer();
    }

    private void resetPosition() {
        xPos = r.nextFloat() * screenWidth;
        yPos = screenHeight;
        size = MIN_SIZE + r.nextFloat() * (MAX_SIZE-MIN_SIZE);
        float hitBoxCorrection = 50;
        hitBox.set(xPos + hitBoxCorrection, yPos + hitBoxCorrection, size - hitBoxCorrection, size - hitBoxCorrection);
    }

    private void move() {
        if(yPos < -200) {
            resetPosition();
        } else {
            yPos -= SPEED;
        }
    }

    public void draw() {

        /* shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.circle(xPos, yPos, radius);
        hitBox.setCenter(xPos, yPos); // ska ändras till setPosition sen för Texture
        shapeRenderer.end(); */

        batch.begin();
        batch.draw(rock, xPos, yPos, size, size);
        batch.end();
        hitBox.setPosition(xPos, yPos);
        move();
    }

    public void dispose() {
        //shapeRenderer.dispose();
        batch.dispose();
        rock.dispose();
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}
