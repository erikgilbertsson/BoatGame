package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Navigation implements Screen {

    private static final double SENSITIVITY = 3.0;

    private BoatGame parent;
    private TextureRegion boat;
    private SpriteBatch batch;
    private int screenWidth;
    private int screenHeight;
    private int boatWidth;
    private int boatHeight;

    ShapeRenderer shapeRenderer;

    float circleX;
    float xValue;
    float xSpeed;

    public Navigation(BoatGame parent) {
        this.parent = parent;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    @Override
    public void show() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        boat = new TextureRegion(new Texture("first_boat.png"));
        boatHeight = this.boat.getTexture().getHeight();
        boatWidth = this.boat.getTexture().getWidth();
        circleX =  screenWidth/2;
        xSpeed = 0;
    }

    @Override
    public void render(float delta) {

        xValue = Gdx.input.getAccelerometerX();
        xSpeed = Util.lowPass(xValue, xSpeed);

        if (circleX + xSpeed < 0 && xSpeed > 0) {
            xSpeed = 0;
        } else if (circleX + xSpeed > screenWidth - boatWidth && xSpeed < 0) {
            xSpeed = 0;
        }

        circleX -= xSpeed * SENSITIVITY;

        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(boat, circleX, 250, boatWidth/2, boatWidth/2, boat.getTexture().getWidth(), boat.getTexture().getHeight(), 0.8f, 2.5f, xSpeed*2, true);
        batch.end();
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
        shapeRenderer.dispose();
        batch.dispose();
        boat.getTexture().dispose();
    }
}
