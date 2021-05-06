package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.utils.TimeUtils;

import java.util.LinkedList;

public class GameDirector {

    public static float DIFFICULTY = 1;

    private LinkedList<GameObject> gameObjects;
    private Boat playerBoat;
    private Wind wind;
    private Cloud cloudGroup;
    private LinkedList<Cliff> cliffs;

    public GameDirector(float screenWidth, float screenHeight) {
        gameObjects = new LinkedList<>();
        cliffs = new LinkedList<>();
        cliffs.add(new Cliff(screenWidth, screenHeight));
        wind = new Wind();
        playerBoat = new Boat(screenWidth, screenHeight, wind);
        cloudGroup = new Cloud(screenWidth,screenHeight);
        gameObjects.addAll(cliffs);
        gameObjects.add(playerBoat);
        gameObjects.add(cloudGroup);
    }

    public void render() {
        for(Cliff cliff : cliffs) {
            playerBoat.detectCollision(cliff.getHitBox());
        }

        for (GameObject obj : gameObjects) {
            obj.draw();
        }
    }
}
