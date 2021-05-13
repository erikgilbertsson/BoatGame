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

    private boolean paused;

    private float screenWidth, screenHeight;
    private LinkedList<GameObject> gameObjects;
    private Boat playerBoat;
    private Wind wind;
    private LinkedList<Cliff> cliffs;
    private LinkedList<Life> lives;

    private SpriteBatch batch;
    private BitmapFont font;
    public float score;


    public GameDirector(float screenWidth, float screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        gameObjects = new LinkedList<>();
        cliffs = new LinkedList<>();
        lives = new LinkedList<Life>();
        for(int i=0;i<3;i++){
            lives.add(new Life(screenWidth, screenHeight));
        }
        wind = new Wind();
        playerBoat = new Boat(screenWidth, screenHeight, wind);
        gameObjects.add(playerBoat);
        score = 0;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.TEAL);
        font.getData().setScale(5);
        paused = false;
    }

    public void render() {
        if(paused) {
            gameObjects.remove(playerBoat);
            cliffs.removeAll(cliffs);
        } else {
            score += Y_SPEED/100;
        }

        if (TimeUtils.timeSinceMillis(lastDifficultyTime) > difficultyTimer) {
            increaseDifficulty();
        }

        batch.begin();

        int points = Math.round(score);
        font.draw(batch, "Score: " + points, Gdx.graphics.getWidth()/2-150, Gdx.graphics.getHeight()*6/7);

        for (Cliff cliff : cliffs) {
           if(playerBoat.detectCollision(cliff.getHitBox())){
               score -= 50;
               if(lives.size()>0) {
                   lives.remove(lives.size() - 1);
               }
           };
        }

        for (GameObject obj : gameObjects) {
            obj.draw();
        }
        for (Life life: lives){
            life.draw();
        }
        batch.end();
    }

    public boolean isGameOver() {
        if (lives.size() <= 0) {
            pauseGame();
            Life.reStart();
            return true;
        } else {
            return false;
        }
    }

    private void pauseGame() {
        paused = true;
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
