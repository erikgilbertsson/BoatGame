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
    private static final int SPLASH_TIME = 100;
    private AssetManager assetManager;
    private long startTime;

    private SpriteBatch batch;
    private Texture img;

    public SplashScreen(BoatGame parent) {
        this.parent = parent;
        startTime = TimeUtils.millis();
        assetManager = new AssetManager();
    }

    @Override
    public void render(float delta) {

        batch.begin();
        batch.draw(img, Gdx.graphics.getWidth()/2 -img.getWidth()/2, Gdx.graphics.getHeight()/2 -img.getHeight()/2);
        batch.end();

        if(assetManager.update() && TimeUtils.timeSinceMillis(startTime) > SPLASH_TIME){
            parent.changeScreen(parent.MAIN_MENU);
        }
    }


    @Override
    public void show() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
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
        img.dispose();
        assetManager.dispose();
    }
}
