package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {

    private BoatGame parent;
    private Stage stage;
    private int screenWidth;
    private int screenHeight;

    public MainMenuScreen(BoatGame parent) {
        this.parent = parent;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }


    @Override
    public void show() {
        MenuButton newGame = new MenuButton("newgame_up.png", "newgame_down.png");
        MenuButton settings = new MenuButton("settings_up.png", "settings_down.png");
        MenuButton help = new MenuButton("help_up.png", "help_down.png");
        newGame.setPosition(screenWidth/2-300, screenHeight/2+300);
        help.setPosition(screenWidth/2-300, screenHeight/2+100);
        settings.setPosition(screenWidth/2-300, screenHeight/2-100);

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(1);
            }
        });
        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(2);
            }
        });
        stage.addActor(newGame);
        stage.addActor(help);
        stage.addActor(settings);
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
