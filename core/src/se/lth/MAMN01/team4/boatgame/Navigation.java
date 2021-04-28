package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import org.omg.PortableServer.POAManagerPackage.State;


public class Navigation extends ApplicationAdapter implements Screen  {

    private BoatGame parent;
    private Texture boat;
    private Stage stage;
    private int screenWidth;
    private int screenHeight;
    private SpriteBatch batch;
    private float X;
    private float Y;
    private float Z;
    private boolean peripheralAvailable;
    BitmapFont font = new BitmapFont();

    ShapeRenderer shapeRenderer;

    float circleX=1 ;
    float circleY=1 ;

    float xSpeed = 10;
    float ySpeed = 10;

    public Navigation(BoatGame parent){

        this.shapeRenderer = new ShapeRenderer();;
        this.parent = parent;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        boat= new Texture("ball.png");
        //cam.setToOrtho(true);

        peripheralAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);



    }

    @Override
    public void create () {
       //this. shapeRenderer = new ShapeRenderer();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        /*Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch = new SpriteBatch();
        batch.begin();
        X = Gdx.input.getAccelerometerX();
        Y = Gdx.input.getAccelerometerY();
        Z = Gdx.input.getAccelerometerZ();

        // tell our stage to do actions and draw itself
        //stage.act();
        //stage.draw();
        font.draw(batch, boat,  Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);

       // batch.draw(boat,0,0);
        batch.end();*/
        X = Gdx.input.getAccelerometerX();
        Y = Gdx.input.getAccelerometerY();


        circleX += xSpeed;
        circleY += ySpeed;

        System.out.println("X:"+circleX);
        if(circleX < 0 || circleX > Gdx.graphics.getWidth()){
            xSpeed *= xSpeed;
        }

        if(circleY < 0 || circleY > Gdx.graphics.getHeight()){
            ySpeed *= ySpeed;
        }

        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.circle(circleX, circleY, 75);
        shapeRenderer.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        shapeRenderer.dispose();
    }
}
