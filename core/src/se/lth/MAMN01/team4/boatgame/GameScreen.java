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
    private Cloud cloudGroup;
    private LinkedList<GameObject> gameObjects;
    private LinkedList<Cliff> cliffs;
    private LinkedList<Cloud> clouds;

    public GameScreen(BoatGame parent) {
        this.parent = parent;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        gameObjects = new LinkedList<>();
        cliffs = new LinkedList<>();
        clouds = new LinkedList<>();
        cliffs.add(new Cliff(screenWidth, screenHeight));
        playerBoat = new Boat(screenWidth, screenHeight);
        cloudGroup = new Cloud(screenWidth,screenHeight);
        gameObjects.addAll(cliffs);
        gameObjects.add(playerBoat);
        clouds.add(cloudGroup);
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

        for(Cloud cloud: clouds){
            cloud.draw();
            cloudGroup.removeClouds();
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
