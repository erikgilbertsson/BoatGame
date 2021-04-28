package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Boat {
    private TextureRegion boat;

    public Boat(String filePath) {
        boat = new TextureRegion(new Texture(filePath));
    }
    public void setDifficulty(String mode){
        if(mode == "EASY"){
            //do something.. easy
        }
        if(mode == "MEDIUM") {
            //do something.. medium
        }
        if(mode == "HARD"){
            //do something.. hard
        }
    }
}
