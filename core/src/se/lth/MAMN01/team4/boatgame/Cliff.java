package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class Cliff implements GameObject {

    private static final float MAX_RADIUS = 150;
    private static final float MIN_RADIUS = 80;

    private float xPos, yPos, radius;
    private float screenWidth, screenHeight;
    private Random r;
    private TextureRegion rock;
    private TextureRegion rock2;
    private SpriteBatch batch;
    private int rockWidth, rockHeight;

    private ShapeRenderer shapeRenderer;

    public Cliff(float screenWidth, float screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        batch = new SpriteBatch();
        rock = new TextureRegion(new Texture("rock_2.png"));
        r = new Random();
        resetPosition();
        rockHeight = this.rock.getTexture().getHeight();
        rockWidth = this.rock.getTexture().getWidth();

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
        batch.begin();
        //shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
       // shapeRenderer.setColor(Color.DARK_GRAY);
        batch.draw(rock,xPos,yPos,rockWidth/2,rockWidth/2,rock.getTexture().getWidth(),rock.getTexture().getHeight(),0.8f,2.5f,xPos,true);
       // shapeRenderer.circle(xPos, yPos, radius);
       // shapeRenderer.end();
        batch.end();
        move();
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
