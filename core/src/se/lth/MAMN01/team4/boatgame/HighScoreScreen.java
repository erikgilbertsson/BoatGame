package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class HighScoreScreen implements Screen {

    private BoatGame parent;
    private int screenWidth, screenHeight, boatHeight, boatWidth;
    private SpriteBatch batch;
    private FileHandle fileHandle;
    private Texture rockTexture, cloudTexture;
    private TextureRegion boatTexture;


    public HighScoreScreen(BoatGame parent) {
        this.parent = parent;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        rockTexture = new Texture("rock_2.png");
        cloudTexture = new Texture("cloudsprite.png");
        boatTexture = new TextureRegion(new Texture("first_boat.png"));
        boatHeight = screenWidth*2/10;
        boatWidth = screenWidth*2/15;
        batch = new SpriteBatch();
        fileHandle = Gdx.files.local("high_scores.txt");
    }

    @Override
    public void show() {
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.01f, 0.1f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        Assets.titleFont.draw(batch, "High Scores", 0,screenHeight*6/9, screenWidth, 1, true);
        // high scores
        try {
            Assets.scoreFont.draw(batch, fileHandle.readString(), 0,screenHeight*5/9, screenWidth, 1, true);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // graphics...
        batch.draw(rockTexture, 500, 200);
        batch.draw(cloudTexture, screenWidth*2/5, screenHeight*6/8);
        batch.draw(cloudTexture, screenWidth*3/5, screenHeight*6/7);
        batch.draw(cloudTexture, screenWidth*1/10, screenHeight*6/7);
        batch.draw(boatTexture, 800, 250,
                boatHeight/2, boatWidth/2, boatWidth, boatHeight,
                1, 1, -15, true);

        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            Sounds.playSound("clickSound");
            parent.changeScreen(BoatGame.MAIN_MENU);
        }
    }


    public static boolean saveHighScore(int score) {
        FileHandle fileHandle = Gdx.files.local("high_scores.txt");
        boolean isNewHighScore = false;
        String newHighScoreInput = "";
        if (fileHandle.exists()) {
            ArrayList<Integer> highScores = new ArrayList();
            for (String sc : fileHandle.readString().split("\\r?\\n")) {
                highScores.add(Integer.parseInt(sc));
            }
            if(score > highScores.get(0)) {
                isNewHighScore = true;
            }
            highScores.add(score);
            Util.bubbleSort(highScores);
            if(highScores.size() > 5) {
                highScores.remove(5);
            }
            for (int sc : highScores) {
                newHighScoreInput += sc + "\n";
            }
        } else {
            newHighScoreInput += score + "\n";
            isNewHighScore = true;
        }
        fileHandle.writeString(newHighScoreInput,false);
        return isNewHighScore;
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
