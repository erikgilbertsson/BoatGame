package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InstructionScreen implements Screen {
    private BoatGame parent;
    private int screenWidth;
    private int screenHeight;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture img;

    public InstructionScreen(BoatGame parent){
        this.parent = parent;
        font = new BitmapFont();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    @Override
    public void show() {
        img = new Texture("steamroll_splash.jpg");
        batch = new SpriteBatch();

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(img,200,500);
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       // font.draw(batch ,"Instructions", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
       // font.draw( batch,"Tilt the phone", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .5f);
        //font.draw( batch, "blow the mic", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            parent.changeScreen(BoatGame.MAIN_MENU);
        }
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
