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

    private BoatGame parent;
    private TextureRegion boat;
    private SpriteBatch batch;
    private int screenWidth;
    private int screenHeight;
    private int boatWidth;
    private int boatHeight;
    private float X;
    private float Y;
    private float Z;
    private boolean peripheralAvailable;

    ShapeRenderer shapeRenderer;

    float circleX;
    float circleY;

    float xSpeed = 10;
    float ySpeed = 10;

    public Navigation(BoatGame parent) {

        this.parent = parent;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        //cam.setToOrtho(true);

        peripheralAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);


    }

    @Override
    public void show() {
        //this.shapeRenderer = new ShapeRenderer();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        boat = new TextureRegion(new Texture("first_boat.png"));
        boatHeight = this.boat.getTexture().getHeight();
        boatWidth = this.boat.getTexture().getWidth();
        circleY = screenHeight/2;
        circleX =  screenWidth/2;
    }

    @Override
    public void render(float delta) {

        xSpeed = Gdx.input.getAccelerometerX();
        ySpeed = Gdx.input.getAccelerometerY();

        if (circleX + xSpeed < 0 && xSpeed > 0) {
            xSpeed = 0;
        } else if (circleX + xSpeed > screenWidth - boatWidth && xSpeed < 0) {
            xSpeed = 0;
        }

        if (circleY + ySpeed < 0 && ySpeed > 0) {
            ySpeed = 0;
        } else if (circleY + ySpeed > screenHeight- - boatHeight && ySpeed < 0) {
            ySpeed = 0;
        }
        circleX -= xSpeed*2;
        circleY -= ySpeed*2;

        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(boat, circleX, circleY, boatWidth/2, boatWidth/2, boat.getTexture().getWidth(), boat.getTexture().getHeight(), 0.8f, 2.5f, xSpeed*2, true);
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
