package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuButton extends Button {
    public MenuButton(String buttonUpFilePath, String buttonDownFilePath) {
        super(new TextureRegionDrawable(new TextureRegion(new Texture(buttonUpFilePath))),
                new TextureRegionDrawable(new TextureRegion(new Texture(buttonDownFilePath))));

    }
}
