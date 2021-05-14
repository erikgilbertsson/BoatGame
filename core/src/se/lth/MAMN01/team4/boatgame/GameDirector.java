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
    private Life life;

    private SpriteBatch batch;
    private BitmapFont font;
    public float score;


    public GameDirector(float screenWidth, float screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.TEAL);
        font.getData().setScale(5);

        gameObjects = new LinkedList<>();
        cliffs = new LinkedList<>();
        wind = new Wind();
        playerBoat = new Boat(screenWidth, screenHeight, batch, wind);
        life = new Life(screenWidth, screenHeight, batch);
        gameObjects.add(playerBoat);

        score = 0;
        paused = false;
    }

    public void render() {
        float ui_x;
        float ui_y;
        if(paused) {
            gameObjects.remove(playerBoat);
            ui_x = screenWidth/2-150;
            ui_y = screenHeight*5/8;
        } else {
            score += Y_SPEED/100;
            ui_x = screenWidth/2;
            ui_y = 150;
            if (TimeUtils.timeSinceMillis(lastDifficultyTime) > difficultyTimer) {
                increaseDifficulty();
            }
            for (Cliff cliff : cliffs) {
                if(playerBoat.detectCollision(cliff.getHitBox())){
                    score -= 50;
                    life.loseLife();
                };
            }
        }

        for (GameObject obj : gameObjects) {
            obj.draw();
        }
        drawScore(ui_x, ui_y);
    }

    public boolean isGameOver() {
        if (life.getNbrOfLives() <= 0) {
            pauseGame();
            return true;
        } else {
            return false;
        }
    }

    private void drawScore(float xPos, float yPos) {
        int points = Math.round(score);
        batch.begin();
        font.draw(batch, "Score: " + points, xPos, yPos);
        batch.end();
        life.draw();
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
            Cliff newCliff = new Cliff(screenWidth, screenHeight, batch);
            cliffs.add(newCliff);
            gameObjects.addFirst(newCliff);
        }
        if(difficulty.isClouds()) {
            gameObjects.add(new Cloud(screenWidth, screenHeight, batch));
        }

    }
}
