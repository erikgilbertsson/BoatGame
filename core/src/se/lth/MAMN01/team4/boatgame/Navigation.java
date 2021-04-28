package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Navigation implements Screen {

    private BoatGame parent;
    private Texture boat;
    private SpriteBatch batch;
    private int screenWidth;
    private int screenHeight;
    private float X;
    private float Y;
    private float Z;
    private boolean peripheralAvailable;

    ShapeRenderer shapeRenderer;

    float circleX = 1;
    float circleY = 1;

    float xSpeed = 10;
    float ySpeed = 10;

    public Navigation(BoatGame parent) {

        this.parent = parent;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        boat = new Texture("ball.png");
        //cam.setToOrtho(true);

        peripheralAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);


    }

    @Override
    public void show() {
        //this.shapeRenderer = new ShapeRenderer();
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
        this.batch = new SpriteBatch();
        boat = new Texture("first_boat.png");
    }

    @Override
    public void render(float delta) {


        circleX += xSpeed;
        circleY += ySpeed;

        if (circleX < 0 || circleX > screenWidth) {
            xSpeed = -xSpeed;
        }

        if (circleY < 0 || circleY > screenHeight) {
            ySpeed = -ySpeed;
        }

        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(boat, circleX, circleY);
        batch.end();

        //shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.setColor(0, 1, 0, 1);
        //shapeRenderer.circle(circleX, circleY, 75);
        //shapeRenderer.end();


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
    }
}
