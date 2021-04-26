package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

    BoatGame parent;
    private float X;
    private float Y;
    private float Z;
    private SpriteBatch batch;
    BitmapFont font;

    public GameScreen(BoatGame parent){
        this.parent=parent;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        X = Gdx.input.getAccelerometerX();
        Y = Gdx.input.getAccelerometerY();
        Z = Gdx.input.getAccelerometerZ();

        font.draw(batch, "X: " + X + "\nY: " + Y + "\nZ: " + Z,  Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);

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
        batch.dispose();
        font.dispose();
    }
}
