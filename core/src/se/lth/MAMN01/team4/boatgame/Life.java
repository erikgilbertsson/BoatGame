package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Life implements GameObject {

    private float yPos, size;
    private Texture lifeBoat;

    private SpriteBatch batch;

    float xPos;
    static float xChange= 20;

    public Life(float screenWidth, float screenHeight){
        xPos +=  xChange;
        xChange += screenWidth/6;
        yPos = screenHeight *6/7;
        batch = new SpriteBatch();
        lifeBoat = new Texture("first_boat.png");

        size = 50;

    }

    @Override
    public void draw() {
        batch.begin();
        batch.draw(lifeBoat, xPos, yPos, size,size);
        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
        lifeBoat.dispose();

    }

    public static void reStart(){
        xChange = 20;
    }
}
