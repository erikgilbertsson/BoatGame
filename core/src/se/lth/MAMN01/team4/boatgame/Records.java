package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Records implements Screen {
    private BoatGame game;
    private int screenWidth;
    private int screenHeight;
    private SpriteBatch batch;
    BitmapFont font;
    String record;
    String a;



    public Records(BoatGame game, String record){
        this.game=game;
        this.record=record;
        font = new BitmapFont();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    @Override
    public void show() {

        batch = new SpriteBatch();

    }

    @Override
    public void render(float delta) {
        batch.begin();
        font.draw( batch,"your best score is:" +record ,200,200);
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.end();
        svaeScore();
    }
    private void  svaeScore(){
        FileHandle file = Gdx.files.local("localStorage.txt");
        file.writeString(record+"", false);            //write to file
        System.out.println(a=file.readString());   //read file
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
