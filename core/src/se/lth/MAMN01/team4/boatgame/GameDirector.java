package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.utils.TimeUtils;

import java.util.LinkedList;

public class GameDirector {
    public static float Y_SPEED = 10;

    private long difficultyTimer = 10000;
    private Difficulty difficulty = Difficulty.D0;
    private long lastDifficultyTime = 0;

    private float screenWidth, screenHeight;
    private LinkedList<GameObject> gameObjects;
    private Boat playerBoat;
    private Wind wind;
    private LinkedList<Cliff> cliffs;

    public GameDirector(float screenWidth, float screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        gameObjects = new LinkedList<>();
        cliffs = new LinkedList<>();
        wind = new Wind();
        playerBoat = new Boat(screenWidth, screenHeight, wind);
        gameObjects.add(playerBoat);
    }

    public void render() {
        if (TimeUtils.timeSinceMillis(lastDifficultyTime) > difficultyTimer) {
            increaseDifficulty();
        }

        for (Cliff cliff : cliffs) {
            playerBoat.detectCollision(cliff.getHitBox());
        }

        for (GameObject obj : gameObjects) {
            obj.draw();
        }
    }

    public void increaseDifficulty() {
        difficulty = difficulty.getNext();
        Y_SPEED = difficulty.getYSpeed();
        wind.setMaxForce(difficulty.getWindStrength());
        spawnGameObjects();
        lastDifficultyTime = TimeUtils.millis();
        System.out.println("Dif: " + difficulty.name());
    }

    public void spawnGameObjects() {
        if (cliffs.size() < difficulty.getNbrOfCliffs()) {
            Cliff newCliff = new Cliff(screenWidth, screenHeight);
            cliffs.add(newCliff);
            gameObjects.addFirst(newCliff);
        }
        if(difficulty.isClouds()) {
            gameObjects.add(new Cloud(screenWidth, screenHeight));
        }

    }
}
