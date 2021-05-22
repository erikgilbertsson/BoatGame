package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

public class Sounds {

    private Sound crashSound;
    private Sound cliffSound;
    private Sound privilegeSound;
    private Sound worstPirateSound;

    public Sounds(){
    // AssetManager  assetManager = new AssetManager();
        crashSound = Gdx.audio.newSound(Gdx.files.internal("sounds/BoatCrash.mp3"));
        cliffSound = Gdx.audio.newSound(Gdx.files.internal("sounds/roblox_death_sound_effect.mp3"));
        privilegeSound = Gdx.audio.newSound(Gdx.files.internal("sounds/itHasBeenAPrivilege.mp3"));
        worstPirateSound = Gdx.audio.newSound(Gdx.files.internal("sounds/worstPirate.mp3"));

    }

    public void playSound(String sound){
        if(sound.equals("crashSound")) {
            crashSound.play(0.25f);
        }else if(sound.equals("cliffSound")){
            cliffSound.play();
        }else if(sound.equals("privilegeSound")){
            privilegeSound.play();
        }else if(sound.equals("worstPirateSound")){
            worstPirateSound.play(1.25f);
        }

    }

    public void disposeSound(String sound){
        if(sound.equals("crashSound")) {
            crashSound.dispose();
        }else if(sound.equals("cliffSound")){
            cliffSound.dispose();
        }else if(sound.equals("privilegeSound")){
            privilegeSound.dispose();
        }else if(sound.equals("worstPirateSound")){
            worstPirateSound.dispose();
        }

    }

    public void dispose() {
        crashSound.dispose();
        cliffSound.dispose();
        privilegeSound.dispose();
        worstPirateSound.dispose();
    }
}
