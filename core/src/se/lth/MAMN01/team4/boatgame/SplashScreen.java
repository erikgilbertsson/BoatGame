package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class SplashScreen implements Screen {

    private BoatGame parent;

    AssetManager assetManager;
    long startTime;

    BitmapFont font;
    SpriteBatch batch;
    Texture img;

    public SplashScreen(BoatGame parent) {
        this.parent = parent;
        startTime = TimeUtils.millis();
        assetManager = new AssetManager();
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        batch.begin();
        batch.draw(img, Gdx.graphics.getWidth()/2 -img.getWidth()/2, Gdx.graphics.getHeight()/2 -img.getHeight()/2);
        batch.end();

        if(assetManager.update() && TimeUtils.timeSinceMillis(startTime) > 1500){
            parent.changeScreen(parent.MAIN_MENU);
        }
    }


    @Override
    public void show() {

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
