package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Boat implements GameObject {

    // SETTINGS
    private static float SENSITIVITY = 4;
    private static final float SPEED_LIMIT = 10;
    private static final float DEADZONE = 1/4;
    private static final float COLLISION_COOLDOWN = 2000;

    // ATTRIBUTES
    private long collisionTime;
    private Rectangle hitBox;

    private TextureRegion textureRegion;
    private SpriteBatch batch;
    public float xPos, xInput;
    private float screenWidth, screenHeight;
    private float boatWidth, boatHeight;
    private float hitBoxCorrection;
    private Wind wind;

    //private ShapeRenderer shapeRenderer;

    public Boat(float screenWidth, float screenHeight, SpriteBatch batch, Wind wind) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.wind = wind;
        this.batch = batch;
        textureRegion = new TextureRegion(new Texture("first_boat.png"));
        boatHeight = screenWidth*2/10;
        boatWidth = screenWidth*2/15;
        xPos =  screenWidth/2 - boatWidth/2;
        xInput = 0;
        hitBoxCorrection = boatWidth/6;
        hitBox = new Rectangle(xPos+hitBoxCorrection, 250, boatWidth-hitBoxCorrection, boatHeight-hitBoxCorrection);
        collisionTime = 0;

        //this.shapeRenderer = new ShapeRenderer();
    }

    private void move() {
        xInput = Util.lowPass(Gdx.input.getAccelerometerX(), xInput);
        float xSpeed = xInput - wind.getXForce();

        if (xPos + xSpeed < 0 && xSpeed > 0) {
            xSpeed = 0;
        } else if (xPos + xSpeed > screenWidth - boatWidth && xSpeed < 0) {
            xSpeed = 0;
        }

        if(Math.abs(xSpeed) < DEADZONE) {
            xSpeed = 0;
        }

        if (Math.abs(xSpeed) > SPEED_LIMIT) {
            if(xSpeed > 0) {
                xSpeed = SPEED_LIMIT;
            } else {
                xSpeed = -SPEED_LIMIT;
            }

        }

        xPos -= xSpeed * SENSITIVITY;
    }

    public Boolean detectCollision(Rectangle r) {
        if(TimeUtils.timeSinceMillis(collisionTime) > COLLISION_COOLDOWN && hitBox.overlaps(r)) {
            Gdx.input.vibrate(100);
            collisionTime = TimeUtils.millis();
            if((xPos+boatWidth/2) < (r.getX() + r.getWidth()/2)) {
                xInput = 10;
            } else {
                xInput = -10;
            }
            return true;
        }
        return false;
    }

    public void draw() {
        move();

        /*
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(xPos+hitBoxCorrection, 250, boatWidth-hitBoxCorrection*2, boatHeight-hitBoxCorrection*2);

        shapeRenderer.end();
        */

        batch.begin();
        batch.draw(textureRegion, xPos, 250,
                boatHeight/2, boatWidth/2, boatWidth, boatHeight,
                1, 1, xInput*2, true);
        batch.end();

        hitBox.setPosition(xPos + hitBoxCorrection, 250);
    }

    public void dispose() {
        textureRegion.getTexture().dispose();
    }
}
