package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen implements Screen {

    private static final float SENSITIVITY = 4;

    private BoatGame parent;
    private TextureRegion boat;
    private SpriteBatch batch;
    private int screenWidth;
    private int screenHeight;
    private int boatWidth;
    private int boatHeight;

    float xPos;
    float xSpeed;

    public GameScreen(BoatGame parent) {
        this.parent = parent;
        xSpeed = 0;
    }

    @Override
    public void show() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        boat = new TextureRegion(new Texture("first_boat.png"));
        boatHeight = this.boat.getTexture().getHeight();
        boatWidth = this.boat.getTexture().getWidth();
        xPos =  screenWidth/2 - boatWidth/2;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.01f, 0.1f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        xPos = getBoatPosition();

        batch.begin();
        batch.draw(boat, xPos, 250, boatWidth/2, boatWidth/2, boat.getTexture().getWidth(), boat.getTexture().getHeight(), 0.8f, 2.5f, xSpeed*2, true);
        batch.end();
    }

    private float getBoatPosition() {
        xSpeed = Util.lowPass(Gdx.input.getAccelerometerX(), xSpeed);
        if (xPos + xSpeed < 0 && xSpeed > 0) {
            xSpeed = 0;
        } else if (xPos + xSpeed > screenWidth - boatWidth && xSpeed < 0) {
            xSpeed = 0;
        }

        return xPos - xSpeed * SENSITIVITY;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        boat.getTexture().dispose();
    }
}
