package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class Wind implements GameObject{


    private static final float MAX_RADIUS = 150;
    private static final float MIN_RADIUS = 80;


    private float xPos, yPos, radius;
    private float screenWidth, screenHeight;
    private Random r;
    private TextureRegion rock;
    private TextureRegion rock2;
    private SpriteBatch batch;
    private int rockWidth, rockHeight;
    Boat boat;

    private ShapeRenderer shapeRenderer;
    public Wind(float screenWidth, float screenHeight, Boat boat ) {

        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.boat=boat;
        batch = new SpriteBatch();

        //This image it's only an example.....
        rock = new TextureRegion(new Texture("wind5.png"));
        r = new Random();
        resetPosition();
        rockHeight = this.rock.getTexture().getHeight();
        rockWidth = this.rock.getTexture().getWidth();


        this.shapeRenderer = new ShapeRenderer();
    }

    private void resetPosition() {
        xPos = r.nextFloat() * screenHeight;
        yPos = screenWidth;
        radius = MIN_RADIUS + r.nextFloat() * (MAX_RADIUS-MIN_RADIUS);
    }
    private void move() {
        if(yPos < -radius) {
            resetPosition();
            yPos = screenHeight;
        } else {
            yPos -= 10;
        }
    }

    public void wind(){
        if (yPos==boat.xPos){

            for (int i=0; i<10;i++){
                boat.xPos -= 1;
            }
            System.out.println( "it's work ");

        }
    }


    @Override
    public void draw() {


        batch.begin();
        batch.draw(rock,yPos,300);
        batch.end();
        move();
        wind();



    }

    @Override
    public void dispose() {

        shapeRenderer.dispose();

    }
}
