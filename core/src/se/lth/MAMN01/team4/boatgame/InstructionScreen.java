package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.LinkedList;

public class InstructionScreen implements Screen {
    private float xPos, yPos;

    private BoatGame parent;
    private int screenWidth, screenHeight, imageWidth, imageHeight;
    private SpriteBatch batch;
    private LinkedList<Texture> instructionImages;
    private int instructionIndex;
    private LinkedList<String> instructionStrings;
    private ShapeRenderer shapeRenderer;

    public InstructionScreen(BoatGame parent){
        this.parent = parent;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        instructionImages = new LinkedList<>();
        instructionStrings = new LinkedList<>();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        instructionImages.add(new Texture("steering_instruction.jpg"));
        instructionImages.add(new Texture("wind_instruction.jpg"));
        instructionImages.add(new Texture("clouds_instruction.jpg"));

        imageHeight = instructionImages.get(0).getHeight() * (screenWidth / 1000) *5/6;
        imageWidth = instructionImages.get(0).getWidth() * (screenWidth / 1000) *5/6;

        xPos = screenWidth/2-imageWidth/2;
        yPos = screenHeight*1/6;

        instructionStrings.add("Avoid hitting the cliffs by tilting your\nphone left or right...");
        instructionStrings.add("Compensate and steer your boat against\nthe wind when the weather gets worse...");
        instructionStrings.add("Blow away the clouds obscuring your view\nby blowing onto the bottom of your screen!");
    }

    @Override
    public void show() {
        instructionIndex = 0;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.justTouched()) {
            instructionIndex = (instructionIndex >= instructionImages.size()-1 ? 0 : instructionIndex+1);
        }

        batch.begin();
        batch.draw(instructionImages.get(instructionIndex), xPos, yPos, imageWidth, imageHeight);

        Assets.textFont.draw(batch, instructionStrings.get(instructionIndex),0,screenHeight*1/7, screenWidth, 1, true);
        batch.end();

        for(int i = 0; i < instructionStrings.size(); i++) {
            drawNavMarker(i);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            parent.changeScreen(BoatGame.MAIN_MENU);
        }
    }

    private void drawNavMarker(int i) {
        if(i == instructionIndex) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        } else {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        }
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(screenWidth/2+(i-1)*50, screenHeight*1/15, 15);
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
        batch.dispose();
        for(Texture t : instructionImages) {
            t.dispose();
        }
    }
}
