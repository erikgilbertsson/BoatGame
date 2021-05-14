package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class Life implements GameObject {

    private int nbrOfLives = 3;

    private float yPos, size;
    private Texture lifeBoat;

    private SpriteBatch batch;

    private float xPos;
    private float xChange = 100;

    public Life(float screenWidth, float screenHeight, SpriteBatch batch){
        xPos = screenWidth/2-300;
        yPos = 100;
        this.batch = batch;
        lifeBoat = new Texture("first_boat.png");

        size = 50;

    }

    public void loseLife() {
        nbrOfLives--;
    }

    public int getNbrOfLives() {
        return nbrOfLives;
    }

    @Override
    public void draw() {
        batch.begin();
        for(int i = 0; i < nbrOfLives; i++) {
            batch.draw(lifeBoat, xPos+i*xChange, yPos, size,size);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        lifeBoat.dispose();
    }
}
