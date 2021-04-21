package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {

    private BoatGame parent;
    private Stage stage;

    public MainMenuScreen(BoatGame parent) {
        this.parent = parent;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

    }


    @Override
    public void show() {
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        TextButton newGame = new TextButton("New Game", skin);
        TextButton settings = new TextButton("Settings", skin);
        TextButton help = new TextButton("Help", skin);
        newGame.setSize(500, 150);
        newGame.getLabel().setFontScale(2f);
        newGame.setPosition(Gdx.graphics.getWidth()/2-250, Gdx.graphics.getHeight()/2-75);
        stage.addActor(newGame);
    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act();
        stage.draw();
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
