package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import java.util.LinkedList;

public class GameScreen implements Screen {

    private BoatGame parent;

    private int screenWidth;
    private int screenHeight;

    private LinkedList<GameObject> gameObjects = new LinkedList<>();

    public GameScreen(BoatGame parent) {
        this.parent = parent;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        gameObjects.add(new Cliff(screenWidth, screenHeight));
        gameObjects.add(new Boat(screenWidth, screenHeight));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.01f, 0.1f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (GameObject obj : gameObjects) {
            obj.draw();
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
    }
}
