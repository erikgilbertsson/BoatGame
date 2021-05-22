package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {

    private Music musicIntro, musicLoop;
    private Sound crashSound, cliffSound, privilegeSound, worstPirateSound;

    public Sounds() {
        // AssetManager  assetManager = new AssetManager();
        musicIntro = Gdx.audio.newMusic(Gdx.files.internal("sounds/boatgame_intro.wav"));
        musicLoop = Gdx.audio.newMusic(Gdx.files.internal("sounds/boatgame_loop.wav"));
        crashSound = Gdx.audio.newSound(Gdx.files.internal("sounds/BoatCrash.mp3"));
        cliffSound = Gdx.audio.newSound(Gdx.files.internal("sounds/roblox_death_sound_effect.mp3"));
        privilegeSound = Gdx.audio.newSound(Gdx.files.internal("sounds/itHasBeenAPrivilege.mp3"));
        worstPirateSound = Gdx.audio.newSound(Gdx.files.internal("sounds/worstPirate.mp3"));

    }

    public void playMusic() {
        musicIntro.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                musicLoop.setLooping(true);
                musicLoop.play();
            }
        });
        musicIntro.play();
    }

    public void playSound(String sound) {
        if (sound.equals("crashSound")) {
            crashSound.play(0.25f);
        } else if (sound.equals("cliffSound")) {
            cliffSound.play();
        } else if (sound.equals("privilegeSound")) {
            privilegeSound.play();
        } else if (sound.equals("worstPirateSound")) {
            worstPirateSound.play(1.25f);
        }

    }

    public void disposeSound(String sound) {
        if (sound.equals("crashSound")) {
            crashSound.dispose();
        } else if (sound.equals("cliffSound")) {
            cliffSound.dispose();
        } else if (sound.equals("privilegeSound")) {
            privilegeSound.dispose();
        } else if (sound.equals("worstPirateSound")) {
            worstPirateSound.dispose();
        }

    }

    public void dispose() {
        crashSound.dispose();
        cliffSound.dispose();
        privilegeSound.dispose();
        worstPirateSound.dispose();
        musicIntro.dispose();
        musicLoop.dispose();
    }
}
