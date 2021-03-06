package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {

    private BoatGame parent;
    public GameDirector gameDirector;

    private int screenWidth;
    private int screenHeight;
    private Stage stage;
    private MenuButton newGame;

    public GameScreen(BoatGame parent) {
        this.parent = parent;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        stage = new Stage(new ScreenViewport());
    }


    @Override
    public void show() {
        Sounds.stopMenuMusic();
        gameDirector = new GameDirector(screenWidth, screenHeight);
        Gdx.input.setInputProcessor(stage);
        newGame = new MenuButton("newgame_up.png", "newgame_down.png");
        newGame.setPosition(screenWidth/(float)2-300, screenHeight*(float)3/7);
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameDirector.dispose();
                gameDirector = new GameDirector(screenWidth, screenHeight);
                stage.clear();
            }
        });
        Gdx.input.setCatchKey(Input.Keys.BACK, true);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.01f, 0.1f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameDirector.render();
        if (gameDirector.isGameOver()) {
            stage.addActor(newGame);
            stage.act();
            stage.draw();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            Sounds.playSound("clickSound");
            gameDirector.dispose();
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
        stage.dispose();
        gameDirector.dispose();
    }
}
