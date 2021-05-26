package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {

    private static Music musicIntro, musicLoop, gameMusic, windLoop;
    private static Sound crashSound, cliffSound, privilegeSound, worstPirateSound, clickSound;

    public Sounds() {
        // AssetManager  assetManager = new AssetManager();
        musicIntro = Gdx.audio.newMusic(Gdx.files.internal("sounds/boatgame_intro.wav"));
        musicLoop = Gdx.audio.newMusic(Gdx.files.internal("sounds/boatgame_loop.wav"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/Drum_Perc_Mix.wav"));
        crashSound = Gdx.audio.newSound(Gdx.files.internal("sounds/BoatCrash.mp3"));
        cliffSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hit.wav"));
        privilegeSound = Gdx.audio.newSound(Gdx.files.internal("sounds/itHasBeenAPrivilege.mp3"));
        worstPirateSound = Gdx.audio.newSound(Gdx.files.internal("sounds/worstPirate.mp3"));
        windLoop = Gdx.audio.newMusic(Gdx.files.internal("sounds/wind_loop.ogg"));
        clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/click.wav"));

    }

    public static void playMenuMusic() {
        if (!musicLoop.isPlaying() && !musicIntro.isPlaying()) {
            musicIntro.setOnCompletionListener(new Music.OnCompletionListener() {
                @Override
                public void onCompletion(Music music) {
                    musicLoop.setLooping(true);
                    musicLoop.play();
                }
            });
            musicIntro.play();
        }
    }

    public static void playGameMusic() {
        if (!gameMusic.isPlaying()) {
            gameMusic.setLooping(true);
            gameMusic.play();
        }
    }

    public static void stopGameMusic() {
        gameMusic.stop();
        windLoop.stop();
        worstPirateSound.stop();
        privilegeSound.stop();
        crashSound.stop();
        cliffSound.stop();
    }

    public static void stopMenuMusic() {
        musicIntro.stop();
        musicLoop.stop();
    }

    public static void playWindLoop() {
        if (!windLoop.isPlaying()) {
            windLoop.setLooping(true);
            windLoop.play();
        }
    }

    public static void playSound(String sound) {
        if (sound.equals("crashSound")) {
            crashSound.play(0.25f);
        } else if (sound.equals("cliffSound")) {
            cliffSound.play();
        } else if (sound.equals("privilegeSound")) {
            privilegeSound.play();
        } else if (sound.equals("worstPirateSound")) {
            worstPirateSound.play(1.25f);
        } else if (sound.equals("clickSound")) {
            clickSound.play(0.2f);
        }

    }

    public static void disposeSound(String sound) {
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

    public static void dispose() {
        crashSound.dispose();
        cliffSound.dispose();
        privilegeSound.dispose();
        worstPirateSound.dispose();
        musicIntro.dispose();
        musicLoop.dispose();
    }
}
