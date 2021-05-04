package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Boat implements GameObject {

    private static float SENSITIVITY = 4;
    private static final float DEADZONE = 1/4;
    private static final float COLLISION_COOLDOWN = 2000;

    private long collisionTime;

    private TextureRegion boat;
    private SpriteBatch batch;
    public float xPos, xSpeed;
    private float screenWidth, screenHeight;
    private int boatWidth, boatHeight;
    private Rectangle hitBox;


    public Boat(float screenWidth, float screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        batch = new SpriteBatch();
        boat = new TextureRegion(new Texture("first_boat.png"));
        boatHeight = this.boat.getTexture().getHeight();
        boatWidth = this.boat.getTexture().getWidth();
        xPos =  screenWidth/2 - boatWidth/2;
        xSpeed = 0;
        hitBox = new Rectangle(xPos, 250, boatWidth, boatHeight);
        collisionTime = 0;
    }

    public void increaseDifficulty(){
            SENSITIVITY++;
        }


    private void move() {
        xSpeed = Util.lowPass(Gdx.input.getAccelerometerX(), xSpeed);

        if (xPos + xSpeed < 0 && xSpeed > 0) {
            xSpeed = 0;
        } else if (xPos + xSpeed > screenWidth - boatWidth && xSpeed < 0) {
            xSpeed = 0;
        }

        if(xSpeed < DEADZONE && xSpeed > -DEADZONE) {
            xSpeed = 0;
        }

        xPos -= xSpeed * SENSITIVITY;
    }

    public void detectCollision(Rectangle r) {
        if(TimeUtils.timeSinceMillis(collisionTime) > COLLISION_COOLDOWN && hitBox.overlaps(r)) {
            Gdx.input.vibrate(100);
            collisionTime = TimeUtils.millis();
        }
    }

    public void draw() {
        move();
        batch.begin();
        batch.draw(boat, xPos, 250, boatWidth/2, boatWidth/2, boat.getTexture().getWidth(), boat.getTexture().getHeight(), 0.8f, 2.5f, xSpeed*2, true);
        batch.end();
        hitBox.setPosition(xPos, 250);
    }

    public void dispose() {
        batch.dispose();
        boat.getTexture().dispose();
    }
}