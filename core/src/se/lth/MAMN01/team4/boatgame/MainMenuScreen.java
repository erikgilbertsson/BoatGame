package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.LinkedList;

public class MainMenuScreen implements Screen {

    private BoatGame parent;
    private Stage stage;
    private float screenWidth, screenHeight, boatHeight, boatWidth;
    private BitmapFont font;
    private SpriteBatch batch;
    private Texture rockTexture, cloudTexture;
    private TextureRegion boatTexture;

    public MainMenuScreen(BoatGame parent) {
        this.parent = parent;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        font = new BitmapFont();
        rockTexture = new Texture("rock_2.png");
        cloudTexture = new Texture("cloudsprite.png");
        boatTexture = new TextureRegion(new Texture("first_boat.png"));
        boatHeight = screenWidth*2/10;
        boatWidth = screenWidth*2/15;
    }

    @Override
    public void show() {
        Sounds.stopGameMusic();
        Sounds.playMenuMusic();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        MenuButton newGame = new MenuButton("newgame_up.png", "newgame_down.png");
        MenuButton highScores = new MenuButton("highscore_up.png", "highscore_down.png");
        MenuButton help = new MenuButton("help_up.png", "help_down.png");
        newGame.setPosition(screenWidth/2-300, screenHeight/2);
        highScores.setPosition(screenWidth/2-300, screenHeight/2-200);
        help.setPosition(screenWidth/2-300, screenHeight/2-400);

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sounds.playSound("clickSound");
                parent.changeScreen(BoatGame.GAME);
            }
        });
        highScores.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sounds.playSound("clickSound");
                parent.changeScreen(BoatGame.HIGH_SCORES);
            }
        });
        help.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sounds.playSound("clickSound");
                parent.changeScreen(BoatGame.INSTRUCTION);
            }
        });


        stage.addActor(newGame);
        stage.addActor(highScores);
        stage.addActor(help);
    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0.01f, 0.1f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        Assets.titleFont.draw(batch, "Boat Game", 0,screenHeight*6/9, screenWidth, 1, true);
        batch.draw(rockTexture, 500, 200);
        batch.draw(cloudTexture, screenWidth*2/5, screenHeight*6/8);
        batch.draw(cloudTexture, screenWidth*3/5, screenHeight*6/7);
        batch.draw(cloudTexture, screenWidth*1/10, screenHeight*6/7);
        batch.draw(boatTexture, 800, 250,
                boatHeight/2, boatWidth/2, boatWidth, boatHeight,
                1, 1, -15, true);
        batch.end();
        // tell our stage to do actions and draw itself
        stage.act();
        stage.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
            //Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() {stage.dispose(); }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
