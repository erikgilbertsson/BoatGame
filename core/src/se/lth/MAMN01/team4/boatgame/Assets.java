package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Assets {

    public static BitmapFont textFont;
    public static BitmapFont titleFont;
    public static BitmapFont scoreFont;

    public Assets() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Aller/Aller_Lt.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = 50;
        textFont = generator.generateFont(parameters);
        parameters.size = 130;
        titleFont = generator.generateFont(parameters);
        parameters.size = 80;
        scoreFont = generator.generateFont(parameters);
    }
}
