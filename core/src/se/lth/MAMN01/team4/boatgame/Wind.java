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
    private Random random;
    private float screenWidth, screenHeight;
    private Random r;
    private TextureRegion rock;
    private TextureRegion rock2;
    private SpriteBatch batch;
    private int rockWidth, rockHeight;
    Boat boat;
    private float[] xPositions;
    private float[] yPositions;


    private ShapeRenderer shapeRenderer;
    public Wind(float screenWidth, float screenHeight, Boat boat ) {

        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.boat=boat;
        random = new Random();
        xPositions = new float[50];
        yPositions = new float[30];
        batch = new SpriteBatch();

        //This image it's only an example.....
        rock = new TextureRegion(new Texture("wind5.png"));
        r = new Random();
        //resetPosition();
        rockHeight = this.rock.getTexture().getHeight();
        rockWidth = this.rock.getTexture().getWidth();


        this.shapeRenderer = new ShapeRenderer();
    }
    private void moveWind(){
        for(int i=0; i<50; i++){
            //float dice = random.nextFloat();

                xPositions[i]+=2.0;

            }
    }

   /* private void resetPosition() {
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
    }*/

    public void windEffect(){
        if (xPositions[0]<300){

            for (int i=0; i<10;i++){
                boat.xSpeed += 1;
            }


        }else {
            boat.xSpeed -= 1;
        }
    }


    @Override
    public void draw() {


        batch.begin();
        for(int i=0; i<50; i++){
            batch.draw(rock, xPositions[i] ,250);
        }

        //batch.draw(rock, 250 ,250);
        batch.end();
        moveWind();
        //move();
        windEffect();




    }

    @Override
    public void dispose() {

       // shapeRenderer.dispose();
        batch.dispose();
        rock.getTexture().dispose();

    }
}
