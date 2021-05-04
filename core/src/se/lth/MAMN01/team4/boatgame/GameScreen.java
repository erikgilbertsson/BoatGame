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

    private Boat playerBoat;
    private LinkedList<GameObject> gameObjects;
    private LinkedList<Cliff> cliffs;

    public GameScreen(BoatGame parent) {
        this.parent = parent;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        gameObjects = new LinkedList<>();
        cliffs = new LinkedList<>();
        playerBoat = new Boat(screenWidth, screenHeight);
        gameObjects.add(playerBoat);
        cliffs.add(new Cliff(screenWidth, screenHeight));
        gameObjects.addAll(cliffs);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.01f, 0.1f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for(Cliff cliff : cliffs) {
            playerBoat.detectCollision(cliff.getHitBox());
        }

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
        for (GameObject obj : gameObjects) {
            obj.dispose();
        }
    }
}
