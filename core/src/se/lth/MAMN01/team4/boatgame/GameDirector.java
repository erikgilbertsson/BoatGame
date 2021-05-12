package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private LinkedList<Life> lifeBoats;
    private int lifes = 3;

    private SpriteBatch batch;
    private BitmapFont font;
    public float point;


    public GameDirector(float screenWidth, float screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        gameObjects = new LinkedList<>();
        lifeBoats = new LinkedList<>();


        for (int i = 0;i<lifes; i++){
            Life temp = new Life(screenWidth,screenHeight);
            //lifeBoats.add(temp);
            gameObjects.add(temp);
        }

        cliffs = new LinkedList<>();
        wind = new Wind();
        playerBoat = new Boat(screenWidth, screenHeight, wind);
        gameObjects.add(playerBoat);
        point = 0;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.TEAL);
        font.getData().setScale(5);
    }

    public void render() {
        point += Y_SPEED/100;
        int points = Math.round(point);

        batch.begin();

        font.draw(batch, "Din score: "+ points, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()*6/7);

        if (TimeUtils.timeSinceMillis(lastDifficultyTime) > difficultyTimer) {
            increaseDifficulty();
        }

        for (Cliff cliff : cliffs) {
           if(playerBoat.detectCollision(cliff.getHitBox())){
               point -= 50;
           };
        }

        for (GameObject obj : gameObjects) {
            obj.draw();
        }
        batch.end();
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
