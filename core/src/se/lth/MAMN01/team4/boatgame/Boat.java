package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private int boatWidth, boatHeight;
    private Wind wind;


    public Boat(float screenWidth, float screenHeight, Wind wind) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.wind = wind;
        batch = new SpriteBatch();
        textureRegion = new TextureRegion(new Texture("first_boat.png"));
        boatHeight = textureRegion.getTexture().getHeight();
        boatWidth = textureRegion.getTexture().getWidth();
        xPos =  screenWidth/2 - boatWidth/2;
        xInput = 0;
        hitBox = new Rectangle(xPos+40, 250, boatWidth, boatHeight-70);
        collisionTime = 0;
    }

    public void increaseDifficulty(){
            SENSITIVITY++;
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

    public void detectCollision(Rectangle r) {
        if(TimeUtils.timeSinceMillis(collisionTime) > COLLISION_COOLDOWN && hitBox.overlaps(r)) {
            Gdx.input.vibrate(100);
            collisionTime = TimeUtils.millis();
            if((xPos+boatWidth/2) < (r.getX() + r.getWidth()/2)) {
                xInput = 10;
            } else {
                xInput = -10;
            }
        }
    }

    public void draw() {
        move();
        batch.begin();
        batch.draw(textureRegion, xPos, 250,
                boatHeight/2, boatWidth/2, boatWidth, boatHeight,
                0.8f, 2.5f, xInput*2, true);
        batch.end();
        hitBox.setPosition(xPos, 250);
    }

    public void dispose() {
        batch.dispose();
        textureRegion.getTexture().dispose();
    }
}