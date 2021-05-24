package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.LinkedList;

public class GameDirector {
    public static float Y_SPEED = 10;
    public static boolean SHOW_TIPS = true;

    private long difficultyTimer = 12500;
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
    private float score;
    private boolean isHighScore;


    public GameDirector(float screenWidth, float screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.CYAN);
        font.getData().setScale(5);
        gameObjects = new LinkedList<>();
        cliffs = new LinkedList<>();
        wind = new Wind(screenWidth, screenHeight);
        playerBoat = new Boat(screenWidth, screenHeight, batch, wind);
        life = new Life(screenWidth, screenHeight, batch);
        gameObjects.add(playerBoat);
        gameObjects.add(wind);
        score = 0;
        paused = false;
        isHighScore = false;
        Sounds.stopGameMusic();
        Sounds.playGameMusic();
    }

    public void render() {
        float ui_x;
        float ui_y;
        if (paused) {
            gameObjects.remove(playerBoat);
            ui_x = screenWidth/4;
            ui_y = screenHeight * 4 / 7;
        } else {
            score += Y_SPEED / 100;
            ui_x = screenWidth/2;
            ui_y = 150;
            if (TimeUtils.timeSinceMillis(lastDifficultyTime) > difficultyTimer) {
                increaseDifficulty();
            }
            for (Cliff cliff : cliffs) {
                if (playerBoat.detectCollision(cliff.getHitBox())) {
                    life.loseLife();
                    if (life.getNbrOfLives() >= 0) {
                        Sounds.playSound("cliffSound");

                    }
                    if(life.getNbrOfLives() == 0){
                        Sounds.playSound("crashSound");
                        gameOver();
                    }
                }
            }
        }

        for (GameObject obj : gameObjects) {
            obj.draw();
        }
        drawScore(ui_x, ui_y);
    }

    private void drawScore(float xPos, float yPos) {
        int points = (int) score;
        String scoreStr = points + " m";
        batch.begin();
        if(isHighScore) {
            Assets.titleFont.draw(batch, "New high score!", 0, screenHeight*6/9, screenWidth, 1, true);
        }
        Assets.scoreFont.draw(batch, scoreStr, xPos, yPos, screenWidth/2, 1, true);
        batch.end();
        life.draw();
    }

    private void increaseDifficulty() {
        difficulty = difficulty.getNext();
        if (difficulty.name().equals("D4")) {
            SHOW_TIPS = false;
        }
        Y_SPEED = difficulty.getYSpeed() == -1 ?
                Y_SPEED + 1 : difficulty.getYSpeed();
        wind.setMaxForce(
                difficulty.getWindStrength() == -1 ?
                        wind.getMaxForce() + (float) 0.2 : difficulty.getWindStrength());
        if(difficulty.getWindStrength() > 0) {
            Sounds.playWindLoop();
        }
        spawnGameObjects();
        lastDifficultyTime = TimeUtils.millis();
        System.out.println("Dif: " + difficulty.name());
    }

    private void spawnGameObjects() {
        if (cliffs.size() < difficulty.getNbrOfCliffs()) {
            Cliff newCliff = new Cliff(screenWidth, screenHeight, batch);
            cliffs.add(newCliff);
            gameObjects.addFirst(newCliff);
        }
        if (difficulty.isClouds()) {
            Cloud newCloud = new Cloud(screenWidth, screenHeight, batch, this);
            gameObjects.add(newCloud);
        }
    }

    public void removeCloud(Cloud cloud) {
        gameObjects.remove(cloud);
        cloud.dispose();
    }

    private void gameOver() {
        paused = true;
        isHighScore = HighScoreScreen.saveHighScore((int)score);
        if (this.score >= 500) {
            Sounds.playSound("privilegeSound");
        } else {
            Sounds.playSound("worstPirateSound");
        }
    }

    public boolean isGameOver() {
        return paused;
    }

    public void dispose() {
        for (GameObject obj : gameObjects) {
            obj.dispose();
        }
        batch.dispose();
        font.dispose();
        life.dispose();
    }
}
