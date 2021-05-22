package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class HighScoreScreen implements Screen {

    private BoatGame parent;
    private int screenWidth;
    private int screenHeight;
    private SpriteBatch batch;
    private FileHandle fileHandle;


    public HighScoreScreen(BoatGame parent){
        this.parent = parent;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        fileHandle = Gdx.files.local("high_scores.txt");
    }

    @Override
    public void render(float delta) {
        batch.begin();
        Assets.textFont.draw(batch,"your best score is: " + fileHandle.readString(),200,200);
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            parent.changeScreen(BoatGame.MAIN_MENU);
        }
    }

    public static void saveHighScore(int score) {
        Gdx.files.local("high_scores.txt").writeString(score + "\n", true);
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
    }
}
