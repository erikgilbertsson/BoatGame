package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

    BoatGame parent;
    private float getX;
    private float getY;
    private float getZ;
    private SpriteBatch batch;
    private Texture img;

    public GameScreen(BoatGame parent){
        this.parent=parent;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        batch.begin();
        batch.draw(img, Gdx.graphics.getWidth()/2 -img.getWidth()/2, Gdx.graphics.getHeight()/2 -img.getHeight()/2);
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

    }
}
